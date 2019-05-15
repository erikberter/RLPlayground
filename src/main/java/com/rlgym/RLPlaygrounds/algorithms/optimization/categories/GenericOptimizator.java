package com.rlgym.RLPlaygrounds.algorithms.optimization.categories;

import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationFunction;
import com.rlgym.RLPlaygrounds.algorithms.optimization.Optimization;

public abstract class GenericOptimizator implements Optimization{
	
	protected explorationFunction expFunction;

	public GenericOptimizator setExplorationFunction(explorationFunction expf){
		this.expFunction = expf;
		return this;
	}
	
}
