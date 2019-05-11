package com.rlgym.RLPlaygrounds.enviroment.types;

import java.util.ArrayList;

import com.rlgym.RLPlaygrounds.enviroment.Enviroment;

public interface StaticEnviroment extends Enviroment {
	
	//Comunication functions
	public int[] fromIntStateToRealState(int[] state);
	
	//Enviroment Data Functions
	public int getStateNumber();
	public int getStateNumberSize();
	
	
	// Enviroment Information Functions
	public int[] getResetState();
	
	public double getRewardFromState(int[] state);
	public boolean isEndState(int[] state);
	
	//Simulative Enviroment Functions
	public void doAction(int action);
	
	//Visual Functions
	public void printMap(int[] state);
	
	
	//Static Enviroment Functions
	public int[] getStateFromStateAction(int[] currentState, int newAction);
	
	
}
