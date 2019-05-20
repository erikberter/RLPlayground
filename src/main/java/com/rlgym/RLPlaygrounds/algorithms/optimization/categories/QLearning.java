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

public class QLearning extends GenericOptimizator implements StateOptimization, Runnable{
	
	
	private StateBasedEnviroment sEnvT;
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
	
	public QLearning() {
		
	}
	
	private void initializeVariable(){
		this.epochs = helpers.getIFMap(config.parameters,"epochs");
		this.learningRate = helpers.getDFMap(config.parameters,"learning_rate");
		this.explorationRate = helpers.getDFMap(config.parameters,"exploration_rate");
		this.rewardOnStep = helpers.getDFMap(config.parameters,"reward_on_step");
		this.discountFactor = helpers.getDFMap(config.parameters,"discount_factor");
		
	}
	public void init(){
		this.sEnvT =  (StateBasedEnviroment) this.sEnv;
		this.stateSize = this.sEnvT.getStateNumber();
		this.actSize = this.sEnvT.getActionNumber();
		this.QsaMatrix = new double[stateSize][actSize];
		initializeVariable();
	}
	
	public void minimizeEpochs() {
		System.out.println(this.epochs);
		for(int epochi = 0; epochi < this.epochs; epochi++) {
			this.sEnvT.resetWorld();
			System.out.println("ola");
			int newAction, nextState;
			for(int statei = 0; !this.sEnvT.isEndState(); statei++) {
				if(Math.random() < explorationAlgorithms.getExplorationRate(this.expFunction, this.explorationRate,statei,0)) 
					newAction = getRandomAction(this.actSize);
				else 
					newAction = getGreedyAction(this.sEnvT.getCurrentState(), this.actSize);
				nextState = this.sEnvT.doActionS(newAction);
				double reward = this.sEnvT.getRewardFromState(nextState) + this.rewardOnStep;
				double maxQsaValue = this.QsaMatrix[nextState][getGreedyAction(nextState, this.actSize)];
				
				this.QsaMatrix[this.sEnvT.getCurrentState()][newAction] = (1-this.learningRate)*this.QsaMatrix[this.sEnvT.getCurrentState()][newAction];
				this.QsaMatrix[this.sEnvT.getCurrentState()][newAction] += this.learningRate*(reward + (this.discountFactor*maxQsaValue));
				this.sEnvT.doAction(newAction);
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

	public void minimizeLoss() {
		// TODO Hacer la minimize Loss
		
	}


	public void printResult() {
		for(int i = 0; i < this.stateSize ; i++ ) {
			for(int j = 0; j < this.actSize; j++) {
				System.out.print(this.QsaMatrix[i][j] + "  :  ");
			}
			System.out.println();
		}
		
		this.sEnvT.resetWorld();
		System.out.println("State 0 : " + this.sEnvT.getCurrentState());
		System.out.println(this.sEnvT.getRewardFromState(this.sEnvT.getCurrentState()));
		int[] q = this.sEnvT.fromIntStateToRealState(this.sEnvT.getCurrentState());
		System.out.println(q[0] + " : " + q[1]);
		int i = 0;
		while(!this.sEnvT.isEndState() && i<30) {
			int newAction;
			newAction = getGreedyAction(this.sEnvT.getCurrentState(), this.actSize);
			System.out.println("Moving " + newAction);
			this.sEnvT.doAction(newAction);
			System.out.println("State " + i + " : " + this.sEnvT.getCurrentState());
			System.out.println(this.sEnvT.getRewardFromState(this.sEnvT.getCurrentState()));
			q = this.sEnvT.fromIntStateToRealState(this.sEnvT.getCurrentState());
			System.out.println(q[0] + " : " + q[1]);
			i++;
		}
	
	}

	public double getGreedyValue(Object currentState, int actSize) {
		// Unused
		return 0;
	}

	public void run() {
		minimizeEpochs();
		
	}


}