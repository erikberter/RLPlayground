package com.rlgym.RLPlaygrounds.algorithms.optimization;

public interface StateOptimization extends Optimization {

	int getGreedyAction(Object currentState, int actSize);
	double getGreedyValue(Object currentState, int actSize);
}
