package com.rlgym.RLPlaygrounds.algorithms.miscelanea;

import java.util.Map;

public class helpers {

	public static double getDFMap(Map<String, Object> map, String name) {
		try{
			double d = (Double) map.get(name);
			return d;
		}catch(ClassCastException e){
			System.err.println("Ha solicitado un tipo de variable incorrecto en:" + name);
		}catch(Exception e){
			System.err.println("Ha solicitado algo que no existe:" + name);
		}
		return 0;
		 
	}//Añadir a todas el try/catch
	
	public static int getIFMap(Map<String, Object> map, String name) {
		try{
			int i = (Integer) map.get(name);
			return i;
		}catch(ClassCastException e){
			System.err.println("Ha solicitado un tipo de variable incorrecto en:" + name);
		}catch(Exception e){
			System.err.println("Ha solicitado algo que no existe:" + name);
		}
		return 0;
	}
	
	
	public static String getSFMap(Map<String, Object> map, String name) {
		try{
			String s = (String) map.get(name);
			return s;
		}catch(ClassCastException e){
			System.err.println("Ha solicitado un tipo de variable incorrecto en:" + name);
		}catch(Exception e){
			System.err.println("Ha solicitado algo que no existe:" + name);
		}
		return "0";
	}
	public static Boolean getBFMap(Map<String, Object> map, String name) {
		try{
			boolean b = (Boolean) map.get(name);
			return b;
		}catch(ClassCastException e){
			System.err.println("Ha solicitado un tipo de variable incorrecto en:" + name);
		}catch(Exception e){
			System.err.println("Ha solicitado algo que no existe:" + name);
		}
		return false;
	}
}
