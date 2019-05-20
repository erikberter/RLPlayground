package com.rlgym.RLPlaygrounds.enviroment;

public interface Enviroment {
	
	//Enviroment
	public void resetWorld();
	public void doAction(int action);
	
	//Action Based
	public int getActionNumber();

}
