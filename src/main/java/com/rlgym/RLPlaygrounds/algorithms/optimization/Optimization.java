package com.rlgym.RLPlaygrounds.algorithms.optimization;


import java.util.Map;

import com.rlgym.RLPlaygrounds.algorithms.exploration.explorationFunction;
import com.rlgym.RLPlaygrounds.enviroment.*;
import com.rlgym.RLPlaygrounds.enviroment.types.ScreenBasedEnviroment;

public interface Optimization{

	public void init();
	public void minimizeEpochs();
	public void minimizeLoss();
	
	int getRandomAction(int actSize); // TODO añadir public o private o algo
	
	public void printResult();
	
}
