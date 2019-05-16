package com.rlgym.RLPlaygrounds.algorithms.exploration;

public class explorationAlgorithms {

	public static double getExplorationRate(explorationFunction expFunction, double rate, int t, int maxT){
		switch(expFunction){
		case CONSTANT:
			return rate;
		case CONSTANT_DECAY:
			return rate*(1/(Math.log(t)));
		case EXPONENTIAL_DECAY: 
			return Math.pow(rate,t);
		case LINEAR_DECAY_WITH_MINIMUM:
			if(t>maxT) return 0.1;
			else return 1-t*((double)(1-0.1)/(maxT)); 
			//TODO cambiar la funcion linear decay with minimum
		default:
			return rate;
		}	
	}
}
