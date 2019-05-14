package com.rlgym.RLPlaygrounds.algorithms.miscelanea;

import java.util.Map;

public class helpers {

	public static double getDFMap(Map<String, Object> map, String name) {
		return (Double) map.get(name);
	}
	
	public static int getIFMap(Map<String, Object> map, String name) {
		return (Integer) map.get(name);
	}
	
	public static String getSFMap(Map<String, Object> map, String name) {
		return (String) map.get(name);
	}
}
