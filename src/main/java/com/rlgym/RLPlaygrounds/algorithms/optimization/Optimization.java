package com.rlgym.RLPlaygrounds.algorithms.optimization;


import java.util.Map;

import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationFunction;
import com.rlgym.RLPlaygrounds.enviroment.*;

public interface Optimization {

	
	public void minimizeEpochs(Enviroment env);
	public void minimizeLoss(Enviroment env);
	
	int getRandomAction(int actSize); // TODO añadir public o private o algo
	
	public void printResult(Enviroment env);
	
}
