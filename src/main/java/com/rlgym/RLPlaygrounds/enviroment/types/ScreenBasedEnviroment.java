package com.rlgym.RLPlaygrounds.enviroment.types;

import java.util.ArrayList;

import com.rlgym.RLPlaygrounds.enviroment.Enviroment;

public interface ScreenBasedEnviroment extends Enviroment {
	//Every Game should hold its own information
	
	//Enviroment Data Functions
	public int[] getStateDimension();
	
	
	// Enviroment Information Functions
	public double getRewardFromState();
	public boolean isEndState();
	public boolean isEndState(Object... data);
	
	//Simulative Enviroment Functions
	public double[][] doActionS(int action) throws Exception;//Simulation
	
	//Visual Functions
	public void printMap();
	
	//Return
	public double[][] getStateMap();
	public double[][] getStateMap(Object... data);
	
	
}
