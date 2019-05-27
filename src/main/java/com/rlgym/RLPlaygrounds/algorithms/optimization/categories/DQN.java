package com.rlgym.RLPlaygrounds.algorithms.optimization.categories;

import java.util.ArrayList;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.RmsProp;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

import com.rlgym.RLPlaygrounds.algorithms.miscelanea.helpers;
import com.rlgym.RLPlaygrounds.algorithms.optimization.EnviromentOptimization;
import com.rlgym.RLPlaygrounds.enviroment.EnviromentTypes;
import com.rlgym.RLPlaygrounds.enviroment.types.ScreenBasedEnviroment;


import com.rlgym.RLPlaygrounds.configuration.config;

public class DQN  extends GenericOptimizator implements EnviromentOptimization, Runnable{
	
	ScreenBasedEnviroment sEnvT;
	
	//Neural Network Model
	private MultiLayerNetwork model;
	
	//Neural Network input info
	private int inputHeight, inputWidth, inputChannels, printResult;
	private boolean printResultB;
	//Neural network hiperparameters
	private int seed, numOutputs, minibatch, memoryClean, epochs;
	private double updaterRate, discountFactor;
	
	//Information for global computing
	private double allVal, kPoint;
	
	// parameters 
	private double explorationRate;
	
	private ArrayList<ArrayList<double[][]>> episodes;
	
	public DQN() {
		this.envType = EnviromentTypes.SCREEN_BASED;
		
		
		// Estas dos variables sirven para observar el comportamiento de la media del programa según el epoch
		this.allVal  = 0;
		this.kPoint = 0;
		initializeInternalVariablesFromConfig();
		
		// TODO Deberia hacer una funcion para networks personalizadas
		MultiLayerConfiguration conf  = new NeuralNetConfiguration.Builder()
				.seed(this.seed)
				.weightInit(WeightInit.XAVIER)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT).updater(new RmsProp(this.updaterRate))
                .list()
                .layer(0, new ConvolutionLayer.Builder(3,3).stride(1,1).padding(2,2).nIn(inputChannels).nOut(3)
                		.activation(Activation.IDENTITY).name("layer0").build())//ConvINIT
                .layer(1, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX).kernelSize(3,3)
                        .stride(2,2).name("layer1").build())//MAXPool
                .layer(2, new ConvolutionLayer.Builder(3,3).stride(1,1).padding(2,2).nOut(6)
                		.activation(Activation.IDENTITY).name("layer2").build())//ConvINIT
                .layer(3, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX).kernelSize(3,3)
                        .stride(2,2).name("layer3").build())//MAXPool
                .layer(4, new DenseLayer.Builder().activation(Activation.RELU)
                        .nOut(13).name("layer4").build())//Dense
                .layer(5, new DenseLayer.Builder().activation(Activation.RELU)
                        .nOut(8).name("layer4").build())//Dense
                .layer(6, new OutputLayer.Builder(LossFunction.MSE)
                        .nOut(numOutputs)
                        .activation(Activation.IDENTITY).name("layer5")
                        .build())//OUT
                .setInputType(InputType.convolutional(this.inputHeight, this.inputWidth, this.inputChannels))
                .build();
		
		
		this.model =  new MultiLayerNetwork(conf);
		this.model.init();
		
	}
	
	public void init(){
		this.sEnvT = (ScreenBasedEnviroment) this.sEnv;
	}
	
	public void initializeInternalVariablesFromConfig() {
		
		// parameters
		this.explorationRate = helpers.getDFMap(config.parameters,"exploration_rate");
		
		this.printResult = helpers.getIFMap(config.parameters,"print_after_steps");
		this.printResultB = helpers.getBFMap(config.parameters,"is_print_after_steps");
		
		this.epochs = helpers.getIFMap(config.parameters,"epochs");
		
		// hiperparameters
		this.seed = helpers.getIFMap(config.hiperParameters,"seed");
		this.numOutputs= helpers.getIFMap(config.hiperParameters,"n_outputs");
		
		this.inputHeight = helpers.getIFMap(config.hiperParameters,"input_height");
		this.inputWidth = helpers.getIFMap(config.hiperParameters,"input_width");
		this.inputChannels = helpers.getIFMap(config.hiperParameters,"input_channels");
		
		this.updaterRate = helpers.getDFMap(config.hiperParameters,"updater_rate");
		this.discountFactor = helpers.getDFMap(config.parameters,"discount_factor");
		
		this.minibatch = helpers.getIFMap(config.hiperParameters,"minibatch");
		
		this.memoryClean = helpers.getIFMap(config.hiperParameters,"memory_clean");
		
		
		
	}
	
	

	public void minimizeEpochs() {
		
		this.episodes = new ArrayList<ArrayList<double[][]>>();
		int newAction;
		for(int i  = 1; i < this.epochs;i++){
			System.out.println("Estamos en el "+i+ " con un size de " + this.episodes.size());
			this.sEnvT.resetWorld();
			
			// TODO añadir a el JDialog estas dos opciones
			if(this.printResultB & i%this.printResult==0) printResult();
			
			if(i%this.memoryClean==0) this.episodes = new ArrayList<ArrayList<double[][]>>();//Clean Memory
			
			while(!this.sEnvT.isEndState()) {
				if(Math.random() < this.explorationRate)
					newAction = getRandomAction(this.sEnvT.getActionNumber()) ;
				else newAction = getGreedyAction(this.sEnvT.getStateMap(), this.sEnvT.getActionNumber());
				//Creating episode and doing action in game
				//Each episode is in the form (s_t,a,rwd,s_{t+1},endState?)
				addEpisode(this.sEnvT, newAction);
				
				if(this.episodes.size() > this.minibatch) {
					int epPoint = (int)(Math.random()*(this.episodes.size()-this.minibatch));
					for(int j = 0; j < this.minibatch; j++) {
						int actionT = (int) this.episodes.get(epPoint+j).get(1)[0][0];//action
						double yJ = this.episodes.get(epPoint+j).get(2)[0][0];
						
						if(this.episodes.get(epPoint+j).get(4)[0][0]==1.0)
							yJ+=this.discountFactor*getGreedyValue(this.episodes.get(epPoint+j).get(3), this.sEnvT.getActionNumber());
						
						
						INDArray inputTemp = Nd4j.create(this.episodes.get(epPoint+j).get(0));
						inputTemp = inputTemp.reshape(new int[] {1,this.inputChannels,this.inputHeight,this.inputWidth});
						INDArray yJT = this.model.output(inputTemp);//Q(s;0)
						yJT.putScalar(new int[] {actionT},yJ);//Q(s,a;0)y_j
						DataSet ds = new DataSet(inputTemp, yJT);
						this.model.fit(ds);
					
					}
				}
			}
		}
	}

	public void minimizeLoss() {
		// TODO Hacer la minimizeLoss
		
	}

	public void addEpisode(ScreenBasedEnviroment sEnvT, int newAction){
		ArrayList<double[][]> tempEpisode = new ArrayList<double[][]>();
		tempEpisode.add(sEnvT.getStateMap());
		tempEpisode.add(new double[][] {{newAction}});
		sEnvT.doAction(newAction);
		tempEpisode.add(new double[][] {{sEnvT.getRewardFromState()}});
		tempEpisode.add(sEnvT.getStateMap());
		tempEpisode.add(new double[][] {{(sEnvT.isEndState())? 1.0: 0.0}});
		this.episodes.add(tempEpisode);
	}
	
	
	public int getGreedyAction(double[][] screen, int actSize) {
		INDArray inputTemp = Nd4j.create(screen);
		inputTemp = inputTemp.reshape(new int[] {1,this.inputChannels,this.inputHeight,this.inputWidth});
		INDArray output = model.output(inputTemp);
		double maxVal = output.getDouble(0);
		int actionT = 0;
		for(int i = 1; i < actSize; i++) {
			if(maxVal < output.getDouble(i)){
				maxVal = output.getDouble(i);
				actionT = i;
			}
		}
		return actionT;
	}
	
	public double getGreedyValue(double[][] screen, int actSize) {
		INDArray inputTemp = Nd4j.create(screen);
		inputTemp = inputTemp.reshape(new int[] {1,this.inputChannels,this.inputHeight,this.inputWidth});
		INDArray output = model.output(inputTemp);
		double maxVal = output.getDouble(0);
		
		for(int i = 1; i < actSize; i++) 
			if(maxVal < output.getDouble(i))
				maxVal = output.getDouble(i);
			
		
		return maxVal;
	}

	public int getRandomAction(int actSize) {
		return (int)(Math.random()*actSize);
	}

	public int getGreedyActionP(double[][] screen, int actSize) {
		System.out.println("Size : " + screen.length +  "  :: "  + screen[0].length );
		INDArray inputTemp = Nd4j.create(screen);
		inputTemp = inputTemp.reshape(new int[] {1,this.inputChannels,this.inputHeight,this.inputWidth});
		INDArray output = model.output(inputTemp);
		double maxVal = output.getDouble(0);
		System.out.println("La acción 0 : " + maxVal);
		int actionT = 0;
		for(int i = 1; i < actSize; i++) {
			System.out.println("La acción " + i + " : " +output.getDouble(i));
			if(maxVal < output.getDouble(i)){
				maxVal = output.getDouble(i);
				actionT = i;
			}
		}
		return actionT;
	}
	
	public String printResult() {
		int newAction;
		this.sEnvT.resetWorld();
		//int[] currentState = new int[] {5,7,5};
		while(!this.sEnvT.isEndState()){
			//Look for action
			newAction = getGreedyActionP(this.sEnvT.getStateMap(), this.sEnvT.getActionNumber());
			this.sEnvT.doAction(newAction);
			this.sEnvT.printMap();
			System.out.println("\n-----------------------------\n");
		}
		if(this.sEnvT.getRewardFromState()>0) System.out.println("WIN!");
		else System.out.println("LOSE :_C");
		return "El resultado ha sido de " + (( this.sEnvT.getRewardFromState()>0 ) ? "Win" : "Lose");
	}
	
	public int getResult() {
		int total=0;
		
		for(int i = 0; i < 100; i++) {
			int newAction;
			this.sEnvT.resetWorld();
			//int[] currentState = new int[] {5,7,5};
			while(!sEnvT.isEndState()){
				//Look for action
				newAction = getGreedyAction(this.sEnvT.getStateMap(), this.sEnvT.getActionNumber());
				this.sEnvT.doAction(newAction);
			}
			total += this.sEnvT.getRewardFromState();
		}
		this.allVal+=total;
		this.kPoint++;
		System.out.println("El valor medio es : " + allVal/kPoint);
		return total;
		
	}

	public void run() {
		minimizeEpochs();
		
	}


}
