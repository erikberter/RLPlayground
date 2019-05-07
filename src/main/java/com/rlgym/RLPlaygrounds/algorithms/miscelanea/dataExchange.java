package com.rlgym.RLPlaygrounds.algorithms.miscelanea;

import java.util.Map;

public class dataExchange {

	public static double getDFMap(Map<String, Object> map, String name) {
		return Double.valueOf(String.valueOf(map.get(name)));
	}
	
	public static int getIFMap(Map<String, Object> map, String name) {
		return Integer.valueOf(String.valueOf(map.get(name)));
	}
	
	public static String getSFMap(Map<String, Object> map, String name) {
		return String.valueOf(map.get(name));
	}
}
