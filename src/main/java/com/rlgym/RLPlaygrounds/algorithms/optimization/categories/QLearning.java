package com.rlgym.RLPlaygrounds.algorithms.optimization.categories;

import java.util.ArrayList;
import java.util.Map;

import com.rlgym.RLPlaygrounds.enviroment.*;
import com.rlgym.RLPlaygrounds.enviroment.types.StateBasedEnviroment;
import com.rlgym.RLPlaygrounds.algorithms.miscelanea.helpers;
import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationAlgorithms;
import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationFunction;
import com.rlgym.RLPlaygrounds.algorithms.optimization.Optimization;
import com.rlgym.RLPlaygrounds.algorithms.optimization.StateOptimization;
import com.rlgym.RLPlaygrounds.configuration.config;

public class QLearning extends GenericOptimizator implements StateOptimization{
	
	// TODO inicializar las variables
	
	double[][] QsaMatrix;
	private int stateSize, actSize, epochs;
	private double explorationRate, learningRate, rewardOnStep, discountFactor;
	
	private StateBasedEnviroment checkValidity(Enviroment env) throws Exception{
		if(env instanceof StateBasedEnviroment)
			return (StateBasedEnviroment) env;
		else {
			
			throw new Exception("The enviroment type sent to minimizeEpochs is not Static, it is ");
		}
	}
	
	public QLearning(int stateSize, int actSize){
		this.stateSize = stateSize;
		this.actSize = actSize;
		this.QsaMatrix = new double[this.stateSize][this.actSize];
		initializeVariable();
	}
	
	public QLearning(Enviroment env) {
		StateBasedEnviroment sEnv = null;
		try{
			sEnv = checkValidity(env);
			
		}catch(Exception e){
			System.err.println(e.getMessage());
			return;
		}
		this.stateSize = sEnv.getStateNumber();
		this.actSize = sEnv.getActionNumber();
		this.QsaMatrix = new double[stateSize][actSize];
		initializeVariable();
	}
	
	private void initializeVariable(){
		this.epochs = helpers.getIFMap(config.parameters,"epochs");
		this.learningRate = helpers.getDFMap(config.parameters,"learning_rate");
		this.explorationRate = helpers.getDFMap(config.parameters,"exploration_rate");
		this.rewardOnStep = helpers.getDFMap(config.parameters,"reward_on_step");
		this.discountFactor = helpers.getDFMap(config.parameters,"discount_factor");
		
		
		
	}
	
	public void minimizeEpochs(Enviroment env) {
		
		StateBasedEnviroment sEnv;
		try {
			sEnv = checkValidity(env);
		} catch (Exception e) {
			System.err.println("The enviroment type in minimizeEpochs is not Static");
			return;
		}
		
		for(int epochi = 0; epochi < this.epochs; epochi++) {
			sEnv.resetWorld();
			int newAction, nextState;
			for(int statei = 0; !sEnv.isEndState(); statei++) {
				if(Math.random() < explorationAlgorithms.getExplorationRate(this.expFunction, this.explorationRate,statei,0)) 
					newAction = getRandomAction(this.actSize);
				else 
					newAction = getGreedyAction(sEnv.getCurrentState(), this.actSize);
				nextState = sEnv.doActionS(newAction);
				double reward = sEnv.getRewardFromState(nextState) + this.rewardOnStep;
				double maxQsaValue = this.QsaMatrix[nextState][getGreedyAction(nextState, this.actSize)];
				
				this.QsaMatrix[sEnv.getCurrentState()][newAction] = (1-this.learningRate)*this.QsaMatrix[sEnv.getCurrentState()][newAction];
				this.QsaMatrix[sEnv.getCurrentState()][newAction] += this.learningRate*(reward + (this.discountFactor*maxQsaValue));
				sEnv.doAction(newAction);
			}
		}
		
	}

	public int getGreedyAction(Object currentState, int actSize) {
		int currentStateT = (Integer) currentState;
		
		
		double maxActVal = this.QsaMatrix[currentStateT][0];
		ArrayList<Integer> posibleAct = new ArrayList<Integer>();
		posibleAct.add(0);
		for(int i = 1; i < actSize; i++) {
			if(maxActVal <this.QsaMatrix[currentStateT][i]) {
				maxActVal = this.QsaMatrix[currentStateT][i];
				posibleAct = new ArrayList<Integer>();
				posibleAct.add(i);
			}else if(maxActVal == this.QsaMatrix[currentStateT][i]){
				posibleAct.add(i);
			}
		}
		return posibleAct.get((int)(Math.random()*posibleAct.size()));
	}

	public int getRandomAction(int actSize) {
		return (int)(Math.random()*(actSize));
	}

	public void minimizeLoss(Enviroment env) {
		// TODO Hacer la minimize Loss
		
	}


	public void printResult(Enviroment env) {
		
		StateBasedEnviroment sEnv;
		try {
			sEnv = checkValidity(env);
		} catch (Exception e) {
			System.err.println("The enviroment type in minimizeEpochs is not Static");
			return;
		}
		
		for(int i = 0; i < this.stateSize ; i++ ) {
			for(int j = 0; j < this.actSize; j++) {
				System.out.print(this.QsaMatrix[i][j] + "  :  ");
			}
			System.out.println();
		}
		
		sEnv.resetWorld();
		System.out.println("State 0 : " + sEnv.getCurrentState());
		System.out.println(sEnv.getRewardFromState(sEnv.getCurrentState()));
		int[] q = sEnv.fromIntStateToRealState(sEnv.getCurrentState());
		System.out.println(q[0] + " : " + q[1]);
		int i = 0;
		while(!sEnv.isEndState() && i<30) {
			int newAction;
			newAction = getGreedyAction(sEnv.getCurrentState(), this.actSize);
			System.out.println("Moving " + newAction);
			sEnv.doAction(newAction);
			System.out.println("State " + i + " : " + sEnv.getCurrentState());
			System.out.println(sEnv.getRewardFromState(sEnv.getCurrentState()));
			q = sEnv.fromIntStateToRealState(sEnv.getCurrentState());
			System.out.println(q[0] + " : " + q[1]);
			i++;
		}
	
	}

	public double getGreedyValue(Object currentState, int actSize) {
		// Unused
		return 0;
	}


}
