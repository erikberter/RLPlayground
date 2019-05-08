package com.rlgym.RLPlaygrounds.enviroment.games;

import com.rlgym.RLPlaygrounds.enviroment.Enviroment;

public class AppleFall implements Enviroment{

	
	private int applex, appley;
	private int boardx;
	
	private void nextStep(int moveP1){
		
		//Move board
		if(moveP1 == 1 && this.boardx>1)
			this.boardx--;
		else if(moveP1 == -1 && this.boardx< 10)
			this.boardx++;
		//Move apple
		this.appley--;
		
	}
	
	public int getStateNumberSize() {
		return 3;
	}

	public int getStateNumber() {
		return -1;
	}

	public int[] getResetState() {
		return new int[]{0, (int)(Math.random()*10),7};
	}

	public int getActionNumber() {
		return 2;
	}

	public void resetWorld() {
		// TODO Auto-generated method stub
		
	}

	public double getRewardFromState(int[] state) {
		if(appley==0){
			if(boardx+10>applex && boardx-10<applex)
				return 1;
			else return -1;
		}
		return 0;
	}

	public boolean isEndState(int[] state) {
		return (state[2]==0) ? true : false;
	}

	public int[] fromIntStateToRealState(int[] state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int compileAction(){
		return 0;
	}

	public int[] getStateFromStateAction(int[] currentState, int newAction) {
		int[] result = new int[3];
		int moveP1 = compileAction();
		//Move board
		if(moveP1 == 1 && this.boardx>1)
			this.boardx--;
		else if(moveP1 == 0 && this.boardx< 10)
			this.boardx++;
		//Move apple
		result[0] = this.boardx;
		result[1] = this.applex;
		result[2] = --this.appley;
		
		return result;
	}

	public void doAction(int action) {
		// TODO Auto-generated method stub
		
	}

	public void printMap(int[] state) {
		System.out.println("El estado es [" + Integer.toString(state[0]) + ", " + Integer.toString(state[1]) + ", " + Integer.toString(state[2])+  "]");
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 10; j++){
				if(i==state[1] && j==state[2]) System.out.print("#");
				else System.out.print("_");
			}
			System.out.println();
		}
		for(int j = 0; j < 10; j++){
			if(j==state[0]) System.out.print("o");
			else System.out.print("-");
		}
	}
	
	

}
