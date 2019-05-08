package com.rlgym.RLPlaygrounds.enviroment;

import java.util.ArrayList;

public interface Enviroment {
	
	//Comunication functions
	public int[] fromIntStateToRealState(int[] state);
	
	//Enviroment Data Functions
	public int getStateNumber();
	public int getStateNumberSize();
	public int getActionNumber();
	
	
	// Enviroment Information Functions
	public int[] getResetState();
	
	public double getRewardFromState(int[] state);
	public boolean isEndState(int[] state);
	
	//Simulative Enviroment Functions
	public void resetWorld();
	public void doAction(int action);
	
	//Visual Functions
	public void printMap(int[] state);
	
	
	//Static Enviroment Functions
	public int[] getStateFromStateAction(int[] currentState, int newAction);
	
	
}
