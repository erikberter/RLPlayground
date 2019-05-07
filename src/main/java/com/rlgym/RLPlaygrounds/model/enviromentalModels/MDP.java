package com.rlgym.RLPlaygrounds.model.enviromentalModels;

import com.rlgym.RLPlaygrounds.model.Model;

public class MDP implements Model{

	int actualState;
	
	public MDP() {
		this.actualState = 0;
	}
	
	public MDP(int[] actualState) {
		this.actualState = actualState[0];
	}
	
	public int[] getCurrentState() {
		return new int[]{this.actualState};
	}
	public void setState(int[] statei) {
		this.actualState = statei[0];
		
	}

	public void runTest(Object testState) {
		// TODO Auto-generated method stub
		
	}

	

}
