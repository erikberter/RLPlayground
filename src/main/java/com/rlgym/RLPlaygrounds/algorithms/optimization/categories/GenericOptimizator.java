package com.rlgym.RLPlaygrounds.algorithms.optimization.categories;

import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationFunction;
import com.rlgym.RLPlaygrounds.algorithms.optimization.Optimization;
import com.rlgym.RLPlaygrounds.enviroment.*;
import com.rlgym.RLPlaygrounds.enviroment.types.*;

public abstract class GenericOptimizator implements Optimization, Runnable{
	
	protected EnviromentTypes envType;
	protected explorationFunction expFunction;
	Enviroment sEnv;
	
	public GenericOptimizator setExplorationFunction(explorationFunction expf){
		this.expFunction = expf;
		return this;
	}
	public GenericOptimizator setEnviroment(Enviroment env,  EnviromentTypes envTp){
		try {
			this.sEnv = checkValidity(env, envTp);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return this;
		}
		return this;
	}
	//Gi
	
	private Enviroment checkValidity(Enviroment env, EnviromentTypes envTp) throws Exception{
		switch(envTp){
		case SCREEN_BASED:
			if(env instanceof ScreenBasedEnviroment)
				return (ScreenBasedEnviroment) env;
			else
				throw new Exception("The enviroment type sent to minimizeEpochs is not Static");
		case STATE_BASED:
			if(env instanceof StateBasedEnviroment)
				return (StateBasedEnviroment) env;
			else
				throw new Exception("The enviroment type sent to minimizeEpochs is not Static");
		default:
			throw new Exception("The enviroment type sent to minimizeEpochs is not Static");
		}// TODO cambiar texto de error
		
	}
}
