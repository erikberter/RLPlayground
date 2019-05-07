package com.rlgym.RLPlaygrounds.model;

public interface Model {
	
	
	public int[] getCurrentState();
	public void setState(int[] statei);
	
	public void runTest(Object testState);


}
