package com.rlgym.RLPlaygrounds.algorithms.optimization;


import java.util.ArrayList;
import java.util.Map;

import org.deeplearning4j.nn.api.*;
import org.deeplearning4j.nn.conf.*;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;


import com.rlgym.RLPlaygrounds.model.Model;
import com.rlgym.RLPlaygrounds.algorithms.miscelanea.dataExchange;
import com.rlgym.RLPlaygrounds.enviroment.Enviroment;


public class DRL implements Optimization{

	
	MultiLayerNetwork model;
	
	public DRL(Map<String, Object> hiperParameters){
		
		int seed = dataExchange.getIFMap(hiperParameters,"seed");
		double learningRate= dataExchange.getDFMap(hiperParameters,"learning_rate");
		int numInputs= dataExchange.getIFMap(hiperParameters,"numInputs");
		int numHiddenNodes= dataExchange.getIFMap(hiperParameters,"numHiddenNodes");
		int numOutputs= dataExchange.getIFMap(hiperParameters,"numOutputs");
		MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder().seed(seed)
			    .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
			    .list()
			    .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes)
			        .weightInit(WeightInit.XAVIER)
			        .activation(Activation.RELU)
			        .build())
			    .layer(1, new OutputLayer.Builder(LossFunction.SQUARED_LOSS)
			        .weightInit(WeightInit.XAVIER)
			        .activation(Activation.RELU)
			        .nIn(numHiddenNodes).nOut(numOutputs).build())
			    .build();
		this.model =  new MultiLayerNetwork(conf);
		this.model.init();
	}
	
	public void minimizeEpochs(Enviroment env, Model model, Map<String, Object> parameters) {
		ArrayList<ArrayList<int[]>> episodes = new ArrayList<ArrayList<int[]>>();
		
		for(int i  = 0; i < dataExchange.getIFMap(parameters,"epochs");i++){
			//State & Action
			int newAction;
			int[] currentState = env.getResetState();
			int[] newState;
			while(!env.isEndState(currentState)){
				//Look for action
				if(Math.random() < dataExchange.getDFMap(parameters,"exploration_rate"))
						newAction = getRandomAction(currentState, env.getActionNumber()) ;
				else newAction = getGreedyAction(currentState, env.getActionNumber());
				newState = env.getStateFromStateAction(currentState, newAction);
				
				//Create episode
				ArrayList<int[]> tempEpisode = new ArrayList<int[]>();
				tempEpisode.add(currentState);
				tempEpisode.add(newState);
				tempEpisode.add(new int[] {newAction});
				int reward = (int)(env.getRewardFromState(newState));
				tempEpisode.add(new int[] {reward});
				//Add episode
				episodes.add(tempEpisode);
				
				//Get y val
				int epPoint = (int)(Math.random()*episodes.size());
				
				int[] stateTemp = episodes.get(epPoint).get(1);
				int[] stateTempP = episodes.get(epPoint).get(0);
				double yJ = episodes.get(epPoint).get(3)[0];
				if(!env.isEndState(stateTemp))
					yJ+= dataExchange.getDFMap(parameters,"discount_factor")*getGreedyValue(stateTemp, env.getActionNumber());
				
				//fit the neural network
				INDArray inputTemp = Nd4j.create(arrayListToArray(tempEpisode));
				INDArray yJT = Nd4j.create(new double[]{yJ}, new int[]{4});
				DataSet ds = new DataSet(inputTemp, yJT);

				this.model.fit(ds);
			}
		}
		
	}
	
	private double[] arrayListToArray(ArrayList<int[]> episode){
		//episode = (s_t, s_{t+1}, rwd, a)
		
		double[] temp = new double[episode.get(0).length+1];
		
		int posT = 0;
		//El menos 1 es por la reward
		//Recordamos que la yupla es (s_t,s_{t+1},a,r)
		for(int j  = 0; j < episode.get(0).length; j++)
			temp[j] = (double)episode.get(0)[j];
		temp[episode.get(0).length] = episode.get(3)[0];
		return temp;
	}

	
	private double[] stateActiontoArray(int[] stateTemp,int action){
		double[] temp = new double[stateTemp.length+1];
		
		for(int i = 0; i < stateTemp.length; i++)
			temp[i] = stateTemp[i];
		temp[stateTemp.length] = action;
			
		return temp;
	}
	
	private double getGreedyValue(int[] stateTemp, int actSize) {
		
		//Action 0
		INDArray inputTemp = Nd4j.create(stateActiontoArray(stateTemp,0));
		
		INDArray output = model.output(inputTemp);
		double rOutput = output.getDouble(0);
		double maxActVal = rOutput;
		ArrayList<Integer> posibleAct = new ArrayList<Integer>();
		posibleAct.add(0);
		for(int i = 1; i < actSize; i++) {
			
			output = model.output(inputTemp);
			rOutput = output.getDouble(0);
			if(maxActVal < rOutput) {
				maxActVal = rOutput;
				posibleAct = new ArrayList<Integer>();
				posibleAct.add(i);
			}else if(maxActVal == rOutput){
				posibleAct.add(i);
			}
		}
		return maxActVal;
	}

	public void minimizeLoss(Enviroment env, Model model, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	public int getGreedyAction(int[] currentState, int actSize) {
		//Action 0
		INDArray inputTemp = Nd4j.create(stateActiontoArray(currentState,0), new int[]{1,4});
		
		INDArray output = model.output(inputTemp);
		double rOutput = output.getDouble(0);
		double maxActVal = rOutput;
		ArrayList<Integer> posibleAct = new ArrayList<Integer>();
		posibleAct.add(0);
		for(int i = 1; i < actSize; i++) {
			
			output = model.output(inputTemp);
			rOutput = output.getDouble(0);
			if(maxActVal < rOutput) {
				maxActVal = rOutput;
				posibleAct = new ArrayList<Integer>();
				posibleAct.add(i);
			}else if(maxActVal == rOutput){
				posibleAct.add(i);
			}
		}
		return posibleAct.get((int)(Math.random()*posibleAct.size()));
	}

	public int getRandomAction(int[] currentState, int actSize) {
		return (int)(Math.random()*actSize);
	}

	public void printResult(Enviroment env, Model model, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	

}
