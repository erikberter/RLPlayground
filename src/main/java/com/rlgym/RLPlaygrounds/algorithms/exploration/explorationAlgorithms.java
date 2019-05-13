package com.rlgym.RLPlaygrounds.algorithms.exploration;

public class explorationAlgorithms {

	public static double getExplorationRate(explorationFunction expFunction, double rate, int t){
		switch(expFunction){
		case CONSTANT:
			return rate;
		case CONSTANT_DECAY:
			return rate*(1/(Math.log(t)));
		case EXPONENTIAL_DECAY: 
			return Math.pow(rate,t);
		default:
			return rate;
		}	
	}
}
