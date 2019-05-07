package com.rlgym.RLPlaygrounds.algorithms.optimization;

import java.util.ArrayList;
import java.util.Map;

import com.rlgym.RLPlaygrounds.agent.Agent;
import com.rlgym.RLPlaygrounds.model.*;
import com.rlgym.RLPlaygrounds.enviroment.*;

import com.rlgym.RLPlaygrounds.algorithms.miscelanea.dataExchange;

public class QLearning implements Optimization{
	
	double[][] QsaMatrix;
	private int stateSize, actSize, explorationFuncType;
	
	
	
	public QLearning(int stateSize, int actSize) {
		this.QsaMatrix = new double[stateSize][actSize];
		this.stateSize = stateSize;
		this.actSize = actSize;
		this.explorationFuncType = 0;
	}
	
	public void setExplorationFuncType(int explorationFuncType){
		this.explorationFuncType = explorationFuncType;
	}
	
	public void minimizeEpochs(Enviroment env, Model model, Map<String, Object> parameters) {
		for(int epochi = 0; epochi < dataExchange.getIFMap(parameters,"epochs"); epochi++) {
			model.setState(env.getResetState());
			for(int statei = 0; !env.isEndState(model.getCurrentState()); statei++) {
				int newAction, nextState;
				if(Math.random() < getExplorationRate( dataExchange.getDFMap(parameters,"exploring_rate"),statei)) 
					newAction = getRandomAction(model.getCurrentState(), this.actSize);
				else 
					newAction = getGreedyAction(model.getCurrentState(), this.actSize);
				nextState = env.getStateFromStateAction(model.getCurrentState(), newAction)[0];
				
				double reward = env.getRewardFromState(new int[]{nextState}) + dataExchange.getDFMap(parameters,"reward_on_step");
				double maxQsaValue = this.QsaMatrix[nextState][getGreedyAction(new int[]{nextState}, this.actSize)];
				
				this.QsaMatrix[model.getCurrentState()[0]][newAction] = (1-dataExchange.getDFMap(parameters,"learning_rate"))*this.QsaMatrix[model.getCurrentState()[0]][newAction];
				this.QsaMatrix[model.getCurrentState()[0]][newAction] += dataExchange.getDFMap(parameters,"learning_rate")*(reward + (dataExchange.getDFMap(parameters,"discount_factor")*maxQsaValue));
				model.setState(new int[]{nextState});
			}
		}
		
	}

	public int getGreedyAction(int[] currentState, int actSize) {
		double maxActVal = this.QsaMatrix[currentState[0]][0];
		ArrayList<Integer> posibleAct = new ArrayList<Integer>();
		posibleAct.add(0);
		for(int i = 1; i < actSize; i++) {
			if(maxActVal <this.QsaMatrix[currentState[0]][i]) {
				maxActVal = this.QsaMatrix[currentState[0]][i];
				posibleAct = new ArrayList<Integer>();
				posibleAct.add(i);
			}else if(maxActVal == this.QsaMatrix[currentState[0]][i]){
				posibleAct.add(i);
			}
		}
		return posibleAct.get((int)(Math.random()*posibleAct.size()));
	}

	public double getExplorationRate(double expRate, int t){
		switch(this.explorationFuncType){
		case 0:
			return expRate;
		case 1:
			return expRate*(1/(Math.log(t)));
		case 2: 
			return Math.pow(expRate,t);
		default:
			return expRate;
		}
	}
	
	public int getRandomAction(int[] currentState, int actSize) {
		return (int)(Math.random()*(actSize));
	}

	public void minimizeLoss(Enviroment env, Model model, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		
	}


	public void printResult(Enviroment env, Model model, Map<String, Object> parameters) {
		
		for(int i = 0; i < this.stateSize ; i++ ) {
			for(int j = 0; j < this.actSize; j++) {
				System.out.print(this.QsaMatrix[i][j] + "  :  ");
			}
			System.out.println();
		}
		
		model.setState(env.getResetState());
		System.out.println("State 0 : " + model.getCurrentState());
		System.out.println(env.getRewardFromState(model.getCurrentState()));
		int[] q = env.fromIntStateToRealState(model.getCurrentState());
		System.out.println(q[0] + " : " + q[1]);
		int i = 0;
		while(!env.isEndState(model.getCurrentState()) && i<30) {
			int newAction, nextState;
			newAction = getGreedyAction(model.getCurrentState(), this.actSize);
			System.out.println("Moving " + newAction);
			nextState = env.getStateFromStateAction(model.getCurrentState(), newAction)[0];
			model.setState(new int[]{nextState});
			System.out.println("State " + i + " : " + model.getCurrentState()[0]);
			System.out.println(env.getRewardFromState(model.getCurrentState()));
			q = env.fromIntStateToRealState(model.getCurrentState());
			System.out.println(q[0] + " : " + q[1]);
			i++;
		}
	
	}


}
