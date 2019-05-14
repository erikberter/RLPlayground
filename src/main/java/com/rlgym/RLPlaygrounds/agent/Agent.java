package com.rlgym.RLPlaygrounds.agent;


import java.util.Map;

import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationFunction;
import com.rlgym.RLPlaygrounds.algorithms.miscelanea.helpers;
import com.rlgym.RLPlaygrounds.algorithms.optimization.*;
import com.rlgym.RLPlaygrounds.algorithms.optimization.categories.*;
import com.rlgym.RLPlaygrounds.configuration.config;
import com.rlgym.RLPlaygrounds.enviroment.*;
import com.rlgym.RLPlaygrounds.enviroment.games.*;
import com.rlgym.RLPlaygrounds.enviroment.types.StateBasedEnviroment;

public class Agent {

	Enviroment enviroment;
	Optimization optimizer;
	
	
	public Agent() {
		
	}
	
	public Agent setEnviroment(GameName envName){
		switch(envName){
			case APPLEFALL:
				this.enviroment = new AppleFall(helpers.getIFMap(config.hiperParameters,"inputWidth"),
						helpers.getIFMap(config.hiperParameters,"inputHeight"));
				break;
			case GRIDWORLD:
				this.enviroment = new GridWorld();
				break;
			default:
				System.err.println("No se ha encontrado la clase dentro del enum");// TODO cambiar el error
		}
		return this;
		
	}
	
	public Agent setOptimization(OptimizationNames optimizatorName){
		switch(optimizatorName){
		case DQN:
			this.optimizer = new QLearning((StateBasedEnviroment) this.enviroment).setExplorationFunction(explorationFunction.CONSTANT);
			break;
		case QLearning:
			this.optimizer = new DQN();
			break;
		default:
			System.err.println("No se ha encontrado la clase dentro del enum"); //Cambiar el error
		}
		return this;
	}
	
	
	
	public void OptimizeAgent() {
		//Check if is valid the combination
		this.optimizer.minimizeEpochs(this.enviroment);
		System.out.println("Minimizada");
	}
	
	public void printResult() {
		this.optimizer.printResult(this.enviroment);
	}
	
}
