package com.rlgym.RLPlaygrounds.algorithms.optimization.categories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

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
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.learning.config.RmsProp;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

import com.rlgym.RLPlaygrounds.algorithms.miscelanea.dataExchange;
import com.rlgym.RLPlaygrounds.algorithms.optimization.EnviromentOptimization;
import com.rlgym.RLPlaygrounds.algorithms.optimization.Optimization;
import com.rlgym.RLPlaygrounds.enviroment.Enviroment;
import com.rlgym.RLPlaygrounds.enviroment.types.ScreenBasedEnviroment;
import com.rlgym.RLPlaygrounds.enviroment.types.StaticEnviroment;
import com.rlgym.RLPlaygrounds.model.Model;

import com.rlgym.RLPlaygrounds.configuration.config;

public class DQN implements EnviromentOptimization{

	MultiLayerNetwork model;
	
	public DQN(Map<String, Object> hiperParameters) {
		int seed = dataExchange.getIFMap(hiperParameters,"seed");
		double learningRate= dataExchange.getDFMap(hiperParameters,"learning_rate");
		int numInputs= dataExchange.getIFMap(hiperParameters,"numInputs");
		int numHiddenNodes= dataExchange.getIFMap(hiperParameters,"numHiddenNodes");
		int numOutputs= dataExchange.getIFMap(hiperParameters,"numOutputs");
		
		int inputHeight = dataExchange.getIFMap(hiperParameters,"inputHeight");
		int inputWidth = dataExchange.getIFMap(hiperParameters,"inputWidth");
		int inputChannels = dataExchange.getIFMap(hiperParameters,"inputChannels");
	
		MultiLayerConfiguration conf  = new NeuralNetConfiguration.Builder()
				.seed(seed)
				.weightInit(WeightInit.XAVIER)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT).updater(new RmsProp(0.001))
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
                        .nOut(20).name("layer4").build())//Dense
                .layer(5, new DenseLayer.Builder().activation(Activation.RELU)
                        .nOut(20).name("layer4").build())//Dense
                .layer(6, new OutputLayer.Builder(LossFunction.MSE)
                        .nOut(numOutputs)
                        .activation(Activation.IDENTITY).name("layer5")
                        .build())//OUT
                .setInputType(InputType.convolutional(inputHeight, inputWidth, inputChannels))
                .build();
		
		
		this.model =  new MultiLayerNetwork(conf);
		this.model.init();
	
	}
	
	private ScreenBasedEnviroment checkValidity(Enviroment env) throws Exception{
		StaticEnviroment sEnv;
		if(env instanceof ScreenBasedEnviroment)
			return (ScreenBasedEnviroment) env;
		else
			throw new Exception("The enviroment type sent to minimizeEpochs is not Static");
	}

	public void minimizeEpochs(Enviroment env, Model model, Map<String, Object> parameters) {
		
		ScreenBasedEnviroment sEnv;
		try {
			sEnv = checkValidity(env);
		} catch (Exception e) {
			System.err.println("The enviroment type in minimizeEpochs is not Static");
			return;
		}
		
		ArrayList<ArrayList<double[][]>> episodes = new ArrayList<ArrayList<double[][]>>();
		int minibatch = dataExchange.getIFMap(config.hiperParameters,"minibatch");
		
		//for(int i  = 1; i < dataExchange.getIFMap(parameters,"epochs");i++){
		for(int i = 1; i < 10000; i++) {
			int newAction;
			sEnv.resetWorld();
			
			if(i%10==0) printResult(sEnv, model, parameters);
			if(i%10==0) System.out.println("Estamos en el momento: " + i);
			if(i%500==0) episodes = new ArrayList<ArrayList<double[][]>>();//Clean Memory
			if(i%20==0) System.out.println("Estamos en el " + i + " : " + getResult(sEnv, model, parameters));
			while(!sEnv.isEndState()) {
				
				
				if(Math.random() < dataExchange.getDFMap(parameters,"exploration_rate"))
					newAction = getRandomAction(sEnv.getActionNumber()) ;
				else newAction = getGreedyAction(sEnv.getStateMap(), sEnv.getActionNumber());
				//Creating episode and doing action in game
				
				//Each episode is in the form (s_t,a,rwd,s_{t+1},endState?)
				ArrayList<double[][]> tempEpisode = new ArrayList<double[][]>(5);
				tempEpisode.add(sEnv.getStateMap());
				tempEpisode.add(new double[][] {{newAction}});
				sEnv.doAction(newAction);
				tempEpisode.add(new double[][] {{sEnv.getRewardFromState()}});
				tempEpisode.add(sEnv.getStateMap());
				tempEpisode.add(new double[][] {{(sEnv.isEndState())? 1.0: 0.0}});
				episodes.add(tempEpisode);
				
				
				if(episodes.size() > minibatch) {
					int epPoint = (int)(Math.random()*(episodes.size()-minibatch));
					for(int j = 0; j < minibatch; j++) {
						double[][] screenTP = episodes.get(epPoint+j).get(0);//s_t
						int actionT = (int) episodes.get(epPoint+j).get(1)[0][0];//action
						double yJ = episodes.get(epPoint+j).get(2)[0][0];//reward
						double[][] screenT = episodes.get(epPoint+j).get(3);//s_{t+1}
						double isEndStateT = episodes.get(epPoint+j).get(4)[0][0];//endstate?
						
						
						if(isEndStateT==1.0)
							yJ+=dataExchange.getDFMap(parameters,"discount_factor")*getGreedyValue(screenT, env.getActionNumber());
						
						
						INDArray inputTemp = Nd4j.create(screenTP);
						inputTemp = inputTemp.reshape(new int[] {1,1,10,12});
						INDArray yJT = this.model.output(inputTemp);//Q(s;0)
						double tempCoord = yJT.getDouble(actionT);//Q(s,a;0)
						//yJT.putScalar(new int[] {actionT},tempCoord+yJ);//Q(s,a;0)+y_j
						yJT.putScalar(new int[] {actionT},yJ);//Q(s,a;0)+y_j
						DataSet ds = new DataSet(inputTemp, yJT);
						this.model.fit(ds);
					
					}
				
				}
			}
			
		}
		
	}

	public void minimizeLoss(Enviroment env, Model model, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	public int getGreedyAction(double[][] screen, int actSize) {
		INDArray inputTemp = Nd4j.create(screen);
		inputTemp = inputTemp.reshape(new int[] {1,1,10,12});
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
		inputTemp = inputTemp.reshape(new int[] {1,1,10,12});
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
		inputTemp = inputTemp.reshape(new int[] {1,1,10,12});
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
	
	public void printResult(Enviroment env, Model model, Map<String, Object> parameters) {
		ScreenBasedEnviroment sEnv;
		try {
			sEnv = checkValidity(env);
		} catch (Exception e) {
			System.err.println("The enviroment type in minimizeEpochs is not Static");
			return;
		}
		
		int newAction;
		sEnv.resetWorld();
		//int[] currentState = new int[] {5,7,5};
		while(!sEnv.isEndState()){
			//Look for action
			newAction = getGreedyActionP(sEnv.getStateMap(), sEnv.getActionNumber());
			sEnv.doAction(newAction);
			sEnv.printMap();
			System.out.println("\n-----------------------------\n");
		}
		if(sEnv.getRewardFromState()>0) System.out.println("WIN!");
		else System.out.println("LOSE :_C");
		
	}
	
	public int getResult(Enviroment env, Model model, Map<String, Object> parameters) {
		ScreenBasedEnviroment sEnv;
		int total=0;
		try {
			sEnv = checkValidity(env);
		} catch (Exception e) {
			System.err.println("The enviroment type in minimizeEpochs is not Static");
			return 0;
		}
		
		for(int i = 0; i < 100; i++) {
			int newAction;
			sEnv.resetWorld();
			//int[] currentState = new int[] {5,7,5};
			while(!sEnv.isEndState()){
				//Look for action
				newAction = getGreedyAction(sEnv.getStateMap(), sEnv.getActionNumber());
				sEnv.doAction(newAction);
			}
			total += sEnv.getRewardFromState();
		}
		return total;
		
	}

}
