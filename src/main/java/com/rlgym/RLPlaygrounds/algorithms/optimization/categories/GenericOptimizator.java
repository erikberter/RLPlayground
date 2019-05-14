package com.rlgym.RLPlaygrounds.algorithms.optimization.categories;

import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationFunction;

public class GenericOptimizator {
	protected explorationFunction expFunction;
	
	public GenericOptimizator setExplorationFunction(explorationFunction expf){
		this.expFunction = expf;
		return this; //Comprobar que realmente funcione
	}
}
