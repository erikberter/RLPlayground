package com.rlgym.RLPlaygrounds.enviroment.games;

import com.rlgym.RLPlaygrounds.enviroment.types.ScreenBasedEnviroment;

public class AppleFall implements ScreenBasedEnviroment{

	
	
	//Game Information
	private int width, height;
	
	private int actionN;
	//Extra Information
	private int applex, appley, boardx;
	
	public AppleFall() {
		this.width = 5;
		this.height = 6;
		this.actionN = 3;// 0. Left ::  1. Stay :: 2. Right
	}
	public AppleFall(int width, int height) {
		this.width = width;
		this.height = height;
		this.actionN = 3;// 0. Left ::  1. Stay :: 2. Right
	}

	public int[] getStateDimension() {
		return new int[] {this.height,this.width};
	}

	public int getActionNumber() {
		return this.actionN;
	}

	public double getRewardFromState() {
		if(this.appley==0) 
			if(this.applex==this.boardx) return 1;
			else return -1;
		return 0;
	}

	public boolean isEndState() {
		if(this.appley==0) return true;
		else return false;
	}
	
	public boolean isEndState(Object... data) {
		int i  = 0;
		for(Object c : data) 
			if(++i==1) return (Integer.valueOf(String.valueOf(c))==0)? true : false;
		
		return false;
	}

	public void resetWorld() {
		this.boardx = (int)(this.width/2);
		this.applex = (int)(Math.random()*this.width);
		this.appley = this.height;
	}

	public void doAction(int action) {
		this.appley--;
		if(action==0 && this.boardx > 0) this.boardx--;
		else if(action==2 && this.boardx < this.width-1) this.boardx++;
		
	}

	public double[][] doActionS(int action) throws Exception {
		
		switch(action) {
		case 0:
			if(this.boardx > 0) getStateMap(this.boardx-1,this.applex,this.appley-1);
			else getStateMap(this.boardx,this.applex,this.appley-1);
			break;
		case 1:
			getStateMap(this.boardx,this.applex,this.appley-1);
			break;
		case 2:
			if(this.boardx < this.width-1) getStateMap(this.boardx+1,this.applex,this.appley-1);
			else getStateMap(this.boardx,this.applex,this.appley-1);
			break;
		default:
			throw new Exception("Sent an action which is unavialable");
		}
		
		return null;
	}

	public void printMap() {
		double[][] screen = getStateMap();
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) 
				System.out.print((int)(screen[i][j]));
			System.out.println();
		}
				
	}

	public double[][] getStateMap() {
		double[][] screen = new double[this.height][this.width];
		for(int i = 0; i < this.width; i++) 
			if(i==this.boardx) screen[0][i] = 1;
			else screen[this.height-1][i] = 0;
		for(int i = 1; i < this.height; i++) 
			for(int j = 0; j < this.width; j++) 
				if(i==this.appley && j==this.applex) screen[i][j] = 1;
				else screen[i][j] = 0;
		if(this.appley==0) screen[0][this.applex]=1;
		
		return screen;
	}

	public double[][] getStateMap(Object... data) {
		
		int[] gameData = new int[3];
		int iT=0;
		for(Object c : data)
			data[++iT] = Integer.valueOf(String.valueOf(c));
		
		double[][] screen = new double[this.height][this.width];
		for(int i = 0; i < this.width; i++) 
			if(i==gameData[0]) screen[0][i] = 1;
			else screen[0][i] = 0;
		for(int i = 1; i < this.height; i++) 
			for(int j = 0; j < this.width; j++) 
				if(i==gameData[2] && j==gameData[1]) screen[i][j] = 1;
				else screen[i][j] = 0;
		if(gameData[2]==0) screen[0][gameData[1]]=1;
		return screen;
	}

}
