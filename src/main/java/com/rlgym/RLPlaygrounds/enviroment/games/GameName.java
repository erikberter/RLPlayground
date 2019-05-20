package com.rlgym.RLPlaygrounds.enviroment.games;

import com.rlgym.RLPlaygrounds.enviroment.Enviroment;

public enum GameName {
	APPLEFALL(new AppleFall()),
	GRIDWORLD(new GridWorld());
	
	private final Enviroment gameClase;
	
	private GameName(Enviroment clase) {
		this.gameClase = clase;
	}
	public Enviroment getClase() {
		return this.gameClase;
	}
	
}	
