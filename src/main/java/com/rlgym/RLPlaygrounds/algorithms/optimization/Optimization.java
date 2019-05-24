package com.rlgym.RLPlaygrounds.algorithms.optimization;



public interface Optimization{

	public void init();
	public void minimizeEpochs();
	public void minimizeLoss();
	
	public int getRandomAction(int actSize); 
	
	public String printResult();
	
}
