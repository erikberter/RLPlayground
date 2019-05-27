package com.rlgym.RLPlaygrounds.manager;


import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationFunction;
import com.rlgym.RLPlaygrounds.algorithms.optimization.categories.*;
import com.rlgym.RLPlaygrounds.enviroment.*;
import com.rlgym.RLPlaygrounds.enviroment.games.*;
public class Manager {

	Enviroment enviroment;
	GenericOptimizator optimizer;
	
	
	public Manager() {
		
	}
	
	
	
	public Manager setEnviroment(GameName envName){
		try {
			this.enviroment = envName.getClase();
		}catch(Exception ex) {
			System.err.println("No se ha podido cargar el nombre dentro del enum de GAMENAME");
		}
		return this;
		
	}
	public Manager setOptimization(OptimizationNames optimizatorName){
		//Set the optimization algorithm
		try {
			this.optimizer = optimizatorName.getClase();
		}catch(Exception ex) {
			System.err.println("No se ha podido cargar el nombre dentro del enum de GAMENAME");
		}
		
		//Set the generic parameters
		this.optimizer = this.optimizer
				.setExplorationFunction(explorationFunction.CONSTANT)
				.setEnviroment(this.enviroment, optimizatorName.getEnviromentType());
		
		return this;
	}
	
	public void initOptimizator(){
		this.optimizer.init();
	}
	
	public void OptimizeAgent() {
		//Check if is valid the combination
		Thread td = new Thread(this.optimizer);
		td.start();
	}
	
	public String printResult() {
		return this.optimizer.printResult();
		
	}
	
}
