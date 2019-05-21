package com.rlgym.RLPlaygrounds.algorithms.optimization.categories;

import com.rlgym.RLPlaygrounds.enviroment.EnviromentTypes;

public enum OptimizationNames {
	DQN(new DQN(), EnviromentTypes.SCREEN_BASED),
	QLearning(new QLearning(),EnviromentTypes.STATE_BASED);
	
	private final GenericOptimizator optimizationAlgorithm;
	private final EnviromentTypes enviromentType;
	private OptimizationNames(GenericOptimizator optAlgorithmi, EnviromentTypes envType) {
		this.optimizationAlgorithm = optAlgorithmi;
		this.enviromentType = envType;
	}
	public GenericOptimizator getClase() {
		return this.optimizationAlgorithm;
	}
	public EnviromentTypes getEnviromentType(){
		return this.enviromentType;
	}
}
