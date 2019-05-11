package com.rlgym.RLPlaygrounds.agent;


import java.util.Map;


import com.rlgym.RLPlaygrounds.algorithms.optimization.*;
import com.rlgym.RLPlaygrounds.algorithms.optimization.categories.*;
import com.rlgym.RLPlaygrounds.configuration.config;
import com.rlgym.RLPlaygrounds.enviroment.*;
import com.rlgym.RLPlaygrounds.enviroment.games.*;
import com.rlgym.RLPlaygrounds.enviroment.types.StaticEnviroment;
import com.rlgym.RLPlaygrounds.model.Model;
import com.rlgym.RLPlaygrounds.model.enviromentalModels.*;

public class Agent {

	Enviroment enviroment;
	Model model;
	Optimization optimizer;
	
	
	Map<String, Object> parameters, hiperParameters;
	
	public Agent(String envName, String modelName, String optimizatorName, Map<String, Object> parameters, Map<String, Object> hiperParameters) {
		
		this.parameters = parameters;
		this.hiperParameters = hiperParameters;
		setEnviroment(envName);
		setModel(modelName);
		setOptimization(optimizatorName);
	}
	
	public void setEnviroment(String envName){
		if(envName.equals("GridWorld"))
			this.enviroment = new GridWorld();
		else if(envName.equals("AppleFall"))
			this.enviroment = new AppleFall(12,10);
	}
	
	
	public void setModel(String modelName){
		if(modelName.equals("MDP"))
			this.model = new MDP();
	}
	public void setOptimization(String optimizatorName){
		if(optimizatorName.equals("Qsa Optimization"))
			this.optimizer = new QLearning((StaticEnviroment) this.enviroment);
		else if(optimizatorName.equals("DQN"))
			this.optimizer = new DQN(config.hiperParameters);
	}
	
	
	
	public void OptimizeAgent() {
		//Check if is valid the combination
		this.optimizer.minimizeEpochs(this.enviroment, this.model, this.parameters);
		System.out.println("Minimizada");
	}
	
	public void printResult() {
		this.optimizer.printResult(this.enviroment, this.model, this.parameters);
	}
	
}
