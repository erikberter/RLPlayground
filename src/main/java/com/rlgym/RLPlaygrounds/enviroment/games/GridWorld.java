package com.rlgym.RLPlaygrounds.enviroment.games;

import com.rlgym.RLPlaygrounds.enviroment.types.StaticEnviroment;

public class GridWorld implements StaticEnviroment{

	double[][] gridWorldRewards;
	double reward_OOB;
	int size_X, size_Y;
	
	int state;
	
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

	public double getRewardFromState(int stateT) {
		int[] realState = fromIntStateToRealState(stateT);
		if(realState[0]==0 || realState[0]==this.size_X-1 || realState[1]==0 || realState[1]==this.size_Y-1)
			return -1;
		return this.gridWorldRewards[realState[0]-1][realState[1]-1];
	}

	public boolean isEndState() {
		if(getRewardFromState(this.state)==0)
			return false;
		return true;
	}
	
	public boolean isEndState(int state) {
		if(getRewardFromState(state)==0)
			return false;
		return true;
	}

	public int getStateNumber() {
		return this.size_X*this.size_Y;
	}

	public int getActionNumber() {
		return 4;
	}

	public void doAction(int action) {
		switch(action) {
			case 0:
				this.state--;
				break;
			case 1:
				this.state++;
				break;
			case 2:
				this.state-=this.size_X;
				break;
			case 3:
				this.state+=this.size_X;
				break;
			default:
				//TODO Add error over action
		}
		
	}
	
	public int doActionS(int action) {
		switch(action) {
		case 0:
			return this.state-1;
		case 1:
			return this.state+1;
		case 2:
			return this.state-this.size_X;
		case 3:
			return this.state+this.size_X;
		default:
			//TODO Add error over action
		}
		return -1;
	}

	public void printMap(int state) {
		// TODO Auto-generated method stub
		
	}

	public void resetWorld() {
		this.state = this.size_X+1;
	}


	public int[] fromIntStateToRealState(int state) {
		return new int[] {state%this.size_X,(state-(state%this.size_X))/this.size_X};
	}

	public int fromRealStateToIntState(int[] state) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getRewardFromState() {
		int[] realState = fromIntStateToRealState(this.state);
		if(realState[0]==0 || realState[0]==this.size_X-1 || realState[1]==0 || realState[1]==this.size_Y-1)
			return -1;
		return this.gridWorldRewards[realState[0]-1][realState[1]-1];
	}



	

	public int getCurrentState() {
		return this.state;
	}


}
