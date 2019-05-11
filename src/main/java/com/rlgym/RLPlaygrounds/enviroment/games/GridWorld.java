package com.rlgym.RLPlaygrounds.enviroment.games;

import com.rlgym.RLPlaygrounds.enviroment.types.StaticEnviroment;

public class GridWorld implements StaticEnviroment{

	double[][] gridWorldRewards;
	double reward_OOB;
	int size_X, size_Y;
	public GridWorld() {
		this.gridWorldRewards = new double[][]{
		                         {0,-1,0,0,0},
		                         {0,-1,-1,-1,0},
		                         {0,-1,0,0,0},
		                         {0,-1,0,-1,0},
		                         {0,0,0,-1,10}};
		this.reward_OOB = -1;
		this.size_X = this.gridWorldRewards.length+2;
		this.size_Y = this.gridWorldRewards[0].length+2;
	}
	
	public GridWorld(double[][] gridWorldRewards) {
		this.gridWorldRewards = gridWorldRewards;
		this.reward_OOB = -1;
	}
	
	public GridWorld(double[][] gridWorldRewards, int reward_OOB) {
		this.gridWorldRewards = gridWorldRewards;
		this.reward_OOB = reward_OOB;
	}
	
	public void resetWorld() {
		// TODO Auto-generated method stub
		
	}
	public int getStateNumberSize(){
		return 1;
	}

	public double getRewardFromState(int[] state) {
		int[] realState = fromIntStateToRealState(state);
		if(realState[0]==0 || realState[0]==this.size_X-1 || realState[1]==0 || realState[1]==this.size_Y-1)
			return -1;
		return this.gridWorldRewards[realState[0]-1][realState[1]-1];
	}




	public int[] fromIntStateToRealState(int[] state) {
		int[] realState = new int[2];
		
		realState[0] = state[0]%this.size_X;
		realState[1] = (state[0]-realState[0])/this.size_X;
		return realState;
	}

	public int[] getResetState() {
		return new int[]{1+this.size_X}; //default reset State
	}

	public boolean isEndState(int[] state) {
		if(getRewardFromState(state)==0)
			return false;
		return true;
	}

	public int[] getStateFromStateAction(int[] currentState, int newAction) {
		switch(newAction) {
			case 0:
				return new int[]{currentState[0]-1};
			case 1:
				return new int[]{currentState[0]+1};
			case 2:
				return new int[]{currentState[0]-this.size_X};
			case 3:
				return new int[]{currentState[0]+this.size_X};
			default:
				return new int[]{-1};
		}
	}

	public int getStateNumber() {
		return this.size_X*this.size_Y;
	}

	public int getActionNumber() {
		return 4;
	}

	public void doAction(int action) {
		// TODO Auto-generated method stub
		
	}

	public void printMap(int[] state) {
		// TODO Auto-generated method stub
		
	}

}
