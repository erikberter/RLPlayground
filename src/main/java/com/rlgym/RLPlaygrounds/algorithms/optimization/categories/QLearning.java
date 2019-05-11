package com.rlgym.RLPlaygrounds.algorithms.optimization.categories;

import java.util.ArrayList;
import java.util.Map;

import com.rlgym.RLPlaygrounds.agent.Agent;
import com.rlgym.RLPlaygrounds.model.*;
import com.rlgym.RLPlaygrounds.enviroment.*;
import com.rlgym.RLPlaygrounds.enviroment.types.StaticEnviroment;
import com.rlgym.RLPlaygrounds.algorithms.miscelanea.dataExchange;
import com.rlgym.RLPlaygrounds.algorithms.optimization.Optimization;
import com.rlgym.RLPlaygrounds.algorithms.optimization.StateOptimization;

public class QLearning implements StateOptimization{
	
	double[][] QsaMatrix;
	private int stateSize, actSize, explorationFuncType;
	
	
	
	public QLearning(StaticEnviroment env) {
		this.QsaMatrix = new double[stateSize][actSize];
		this.stateSize = env.getStateNumber();
		this.actSize = env.getActionNumber();
		this.explorationFuncType = 0;
	}
	
	public void setExplorationFuncType(int explorationFuncType){
		this.explorationFuncType = explorationFuncType;
	}
	
	private StaticEnviroment checkValidity(Enviroment env) throws Exception{
		StaticEnviroment sEnv;
		
		if(env instanceof StaticEnviroment)
			return (StaticEnviroment) env;
		else
			throw new Exception("The enviroment type sent to minimizeEpochs is not Static");
	}
	
	public void minimizeEpochs(Enviroment env, Model model, Map<String, Object> parameters) {
		
		StaticEnviroment sEnv;
		try {
			sEnv = checkValidity(env);
		} catch (Exception e) {
			System.err.println("The enviroment type in minimizeEpochs is not Static");
			return;
		}
		
		for(int epochi = 0; epochi < dataExchange.getIFMap(parameters,"epochs"); epochi++) {
			model.setState((sEnv.getResetState()));
			for(int statei = 0; !sEnv.isEndState(model.getCurrentState()); statei++) {
				int newAction, nextState;
				if(Math.random() < getExplorationRate( dataExchange.getDFMap(parameters,"exploring_rate"),statei)) 
					newAction = getRandomAction(this.actSize);
				else 
					newAction = getGreedyAction(model.getCurrentState(), this.actSize);
				nextState = sEnv.getStateFromStateAction(model.getCurrentState(), newAction)[0];
				
				double reward = sEnv.getRewardFromState(new int[]{nextState}) + dataExchange.getDFMap(parameters,"reward_on_step");
				double maxQsaValue = this.QsaMatrix[nextState][getGreedyAction(new int[]{nextState}, this.actSize)];
				
				this.QsaMatrix[model.getCurrentState()[0]][newAction] = (1-dataExchange.getDFMap(parameters,"learning_rate"))*this.QsaMatrix[model.getCurrentState()[0]][newAction];
				this.QsaMatrix[model.getCurrentState()[0]][newAction] += dataExchange.getDFMap(parameters,"learning_rate")*(reward + (dataExchange.getDFMap(parameters,"discount_factor")*maxQsaValue));
				model.setState(new int[]{nextState});
			}
		}
		
	}

	public int getGreedyAction(Object currentState, int actSize) {
		int[] currentStateT = (int[]) currentState;
		
		
		double maxActVal = this.QsaMatrix[currentStateT[0]][0];
		ArrayList<Integer> posibleAct = new ArrayList<Integer>();
		posibleAct.add(0);
		for(int i = 1; i < actSize; i++) {
			if(maxActVal <this.QsaMatrix[currentStateT[0]][i]) {
				maxActVal = this.QsaMatrix[currentStateT[0]][i];
				posibleAct = new ArrayList<Integer>();
				posibleAct.add(i);
			}else if(maxActVal == this.QsaMatrix[currentStateT[0]][i]){
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
	
	public int getRandomAction(int actSize) {
		return (int)(Math.random()*(actSize));
	}

	public void minimizeLoss(Enviroment env, Model model, Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		
	}


	public void printResult(Enviroment env, Model model, Map<String, Object> parameters) {
		
		StaticEnviroment sEnv;
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
		
		model.setState(sEnv.getResetState());
		System.out.println("State 0 : " + model.getCurrentState());
		System.out.println(sEnv.getRewardFromState(model.getCurrentState()));
		int[] q = sEnv.fromIntStateToRealState(model.getCurrentState());
		System.out.println(q[0] + " : " + q[1]);
		int i = 0;
		while(!sEnv.isEndState(model.getCurrentState()) && i<30) {
			int newAction, nextState;
			newAction = getGreedyAction(model.getCurrentState(), this.actSize);
			System.out.println("Moving " + newAction);
			nextState = sEnv.getStateFromStateAction(model.getCurrentState(), newAction)[0];
			model.setState(new int[]{nextState});
			System.out.println("State " + i + " : " + model.getCurrentState()[0]);
			System.out.println(sEnv.getRewardFromState(model.getCurrentState()));
			q = sEnv.fromIntStateToRealState(model.getCurrentState());
			System.out.println(q[0] + " : " + q[1]);
			i++;
		}
	
	}

	public double getGreedyValue(Object currentState, int actSize) {
		// Unused
		return 0;
	}


}
