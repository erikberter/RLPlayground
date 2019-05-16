package com.rlgym.RLPlaygrounds.configuration;

import java.util.HashMap;
import java.util.Map;


public class config extends Throwable{

	public static Map<String, Object> parameters;
	public static Map<String, Object> hiperParameters;
	
	public static void initConfig(){
		//Construct
		parameters = new HashMap<String, Object>();
		hiperParameters = new HashMap<String, Object>();
		
		resetParameters();
		resetHiperParameters();
	}
	//Deberia cambiar todo a la misma estatica
	/*
	 * O bien palabra_siguiente_palabra
	 * O bien palabraSiguientePalabra
	 */
	public static void resetParameters(){
		parameters.put("learning_rate",0.1);
		parameters.put("discount_rate",0.98);
		parameters.put("epochs", 100);
		parameters.put("exploration_rate",0.05);
		parameters.put("reward_on_step", 0.05);
	}
	
	public static void resetHiperParameters(){
		hiperParameters.put("seed", 142);
		hiperParameters.put("updater_rate", 0.001);
		hiperParameters.put("n_input", 6);
		hiperParameters.put("n_hidden_nodes", 5);
		hiperParameters.put("n_outputs", 3);
		hiperParameters.put("minibatch", 22);
		
		hiperParameters.put("input_height", 5);
		hiperParameters.put("input_width", 6);
		hiperParameters.put("input_channels", 1);
	}
	
	
	public static void changeParameter(String field, Object val) throws Exception{
		try{
			parameters.put(field, val);
		}catch(Exception e){
			if(!parameters.containsKey(field))
				throw new Exception("No se contiene la key: " + field );
			//If dataType
		}
	}
	
	public static void changeHiperParameter(String field, Object val) throws Exception{
		try{
			hiperParameters.put(field, val);
		}catch(Exception e){
			if(!hiperParameters.containsKey(field))
				throw new Exception("No se contiene la key: " + field );
			//If dataType
		}
	}
	
	
	
}
