package com.rlgym.RLPlaygrounds.algorithms.optimization;

import com.rlgym.RLPlaygrounds.enviroment.Enviroment;

public interface EnviromentOptimization  extends Optimization{

	int getGreedyAction(double[][] env, int actSize);
	double getGreedyValue(double[][] env, int actSize);
	
}
