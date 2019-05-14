package com.rlgym.RLPlaygrounds.enviroment.types;

import com.rlgym.RLPlaygrounds.enviroment.Enviroment;

public interface StateBasedEnviroment extends Enviroment {
	
	//Comunication functions
	public int[] fromIntStateToRealState(int state);
	public int fromRealStateToIntState(int[] state);
	
	//Enviroment Data Functions
	public int getStateNumber();
	
	
	// Enviroment Information Functions
	public double getRewardFromState();
	public double getRewardFromState(int state);
	public boolean isEndState();
	
	//Simulative Enviroment Functions
	
	public int doActionS(int action);
	public int getCurrentState();
	
	
	//Visual Functions
	public void printMap(int state);
	
	
	
	
}
