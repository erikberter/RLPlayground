package com.rlgym.RLPlaygrounds.algorithms.optimization;


import java.util.Map;

import com.rlgym.RLPlaygrounds.enviroment.*;
import com.rlgym.RLPlaygrounds.model.Model;

public interface Optimization {

	public void minimizeEpochs(Enviroment env, Model model, Map<String, Object> parameters);
	public void minimizeLoss(Enviroment env, Model model, Map<String, Object> parameters);
	
	int getRandomAction(int actSize);
	
	public void printResult(Enviroment env, Model model, Map<String, Object> parameters);
	
}
