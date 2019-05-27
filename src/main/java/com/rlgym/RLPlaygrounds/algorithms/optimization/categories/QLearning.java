package com.rlgym.RLPlaygrounds.algorithms.optimization.categories;

import java.util.ArrayList;

import com.rlgym.RLPlaygrounds.enviroment.*;
import com.rlgym.RLPlaygrounds.enviroment.types.StateBasedEnviroment;
import com.rlgym.RLPlaygrounds.algorithms.miscelanea.helpers;
import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationAlgorithms;
import com.rlgym.RLPlaygrounds.algorithms.optimization.StateOptimization;
import com.rlgym.RLPlaygrounds.configuration.config;

public class QLearning extends GenericOptimizator implements StateOptimization, Runnable{
	
	private StateBasedEnviroment sEnvT;
	double[][] QsaMatrix;
	private int stateSize, actSize, epochs;
	private double explorationRate, learningRate, rewardOnStep, discountFactor;
	
	@SuppressWarnings("unused")
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
		for(int epochi = 0; epochi < this.epochs; epochi++) {
			this.sEnvT.resetWorld();
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
		//TODO Cambiar el True por actLoss y expectedLoss
		while(true){
			this.sEnvT.resetWorld();
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


	public String printResult() {
		this.sEnvT.resetWorld();
		for(int i = 0; !this.sEnvT.isEndState() && i<30; i++)
			this.sEnvT.doAction(getGreedyAction(this.sEnvT.getCurrentState(), this.actSize));
		
		return "Se ha obtenido un reward de "+this.sEnvT.getRewardFromState(this.sEnvT.getCurrentState());
	
	}

	public double getGreedyValue(Object currentState, int actSize) {
		// Unused
		return 0;
	}

	public void run() {
		minimizeEpochs();
	}


}
