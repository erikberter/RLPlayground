package com.rlgym.RLPlaygrounds.algorithms.miscelanea;

import java.util.Map;

public class helpers {

	public static double getDFMap(Map<String, Object> map, String name) {
		try{
			double d = (Double) map.get(name);
			return d;
		}catch(Exception e){
			System.err.println("Ha solicitado algo que no existe:" + name);
		}
		return 0;
		 
	}//Añadir a todas el try/catch
	
	public static int getIFMap(Map<String, Object> map, String name) {
		return (Integer) map.get(name);
	}
	
	public static String getSFMap(Map<String, Object> map, String name) {
		return (String) map.get(name);
	}
	public static Boolean getBFMap(Map<String, Object> map, String name) {
		return (Boolean) map.get(name);
	}
}
