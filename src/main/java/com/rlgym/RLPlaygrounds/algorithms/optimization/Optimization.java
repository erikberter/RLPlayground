package com.rlgym.RLPlaygrounds.algorithms.optimization;


import java.util.Map;

import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationFunction;
import com.rlgym.RLPlaygrounds.enviroment.*;

public interface Optimization {

	public Optimization setExplorationFunction(explorationFunction expFunction);
	
	public void minimizeEpochs(Enviroment env, Map<String, Object> parameters);
	public void minimizeLoss(Enviroment env, Map<String, Object> parameters);
	
	int getRandomAction(int actSize);
	
	public void printResult(Enviroment env,  Map<String, Object> parameters);
	
}
