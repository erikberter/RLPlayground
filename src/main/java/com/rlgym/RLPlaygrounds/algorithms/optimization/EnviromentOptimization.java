package com.rlgym.RLPlaygrounds.algorithms.optimization;

public interface EnviromentOptimization  extends Optimization{

	int getGreedyAction(double[][] env, int actSize);
	double getGreedyValue(double[][] env, int actSize);
	
}
