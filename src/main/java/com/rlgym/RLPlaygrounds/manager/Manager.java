package com.rlgym.RLPlaygrounds.manager;


import java.util.Map;

import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationFunction;
import com.rlgym.RLPlaygrounds.algorithms.miscelanea.helpers;
import com.rlgym.RLPlaygrounds.algorithms.optimization.*;
import com.rlgym.RLPlaygrounds.algorithms.optimization.categories.*;
import com.rlgym.RLPlaygrounds.configuration.config;
import com.rlgym.RLPlaygrounds.enviroment.*;
import com.rlgym.RLPlaygrounds.enviroment.games.*;
import com.rlgym.RLPlaygrounds.enviroment.types.StateBasedEnviroment;

public class Manager {

	Enviroment enviroment;
	GenericOptimizator optimizer;
	
	
	public Manager() {
		
	}
	
	public Manager setEnviroment(GameName envName){
		switch(envName){
			case APPLEFALL:
				this.enviroment = new AppleFall(helpers.getIFMap(config.hiperParameters,"input_width"),
						helpers.getIFMap(config.hiperParameters,"input_height"));
				break;
			case GRIDWORLD:
				this.enviroment = new GridWorld();
				break;
			default:
				System.err.println("No se ha encontrado la clase dentro del enum");// TODO cambiar el error
		}
		return this;
		
	}
	
	public Manager setOptimization(OptimizationNames optimizatorName){
		switch(optimizatorName){
			case QLearning:
				this.optimizer = new QLearning().setExplorationFunction(explorationFunction.CONSTANT)
				.setEnviroment(this.enviroment, EnviromentTypes.STATE_BASED);
				break;
			case DQN:
				this.optimizer = new DQN().setEnviroment(this.enviroment, EnviromentTypes.SCREEN_BASED);
				break;
			default:
				System.err.println("No se ha encontrado la clase dentro del enum"); //Cambiar el error
		}
		return this;
	}
	
	public void initOptimizator(){
		this.optimizer.init();
	}
	
	public void OptimizeAgent() {
		//Check if is valid the combination
		this.optimizer.start();
		
		System.out.println("Minimizada");
	}
	
	public void printResult() {
		this.optimizer.printResult();
	}
	
}
