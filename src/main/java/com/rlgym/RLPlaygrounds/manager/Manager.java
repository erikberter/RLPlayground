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
		try {
			this.enviroment = envName.getClase();
		}catch(Exception ex) {
			System.err.println("No se ha podido cargar el nombre dentro del enum de GAMENAME");
		}
		return this;
		
	}
	// TODO eliminar todos los SYSO
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
		
		System.out.println("Minimizada");
	}
	
	public void printResult() {
		this.optimizer.printResult();
	}
	
}
