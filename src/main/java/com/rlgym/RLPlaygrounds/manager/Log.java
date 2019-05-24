package com.rlgym.RLPlaygrounds.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Log {
	public static String logName, logDirection;
	public static File logF;
	public static void initLog() throws IOException {
		//TODO if file exist hostearlo, si no, crearlo
		logName = "log.txt";
		logDirection = "src/main/resources";
		FileWriter log =  new FileWriter(logDirection+"/"+logName);
		log.append("Here we are again");
		log.flush();
		log.close();
		logF=new File(logDirection+"/"+logName);
	}
	
	public static LinkedList<String> getLog() throws IOException {
		LinkedList<String> logVal = new LinkedList<String>();
		BufferedReader br = new BufferedReader(new FileReader(logF)); 
		String tempLine = "";
	  	while ((tempLine = br.readLine()) != null) 
		  logVal.add(tempLine);
	  	br.close();
		return logVal;
	}
	
	public static void addLogLine(String line) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(logF, true));
        bw.append(line);
        bw.close();
	}
	public static void addLog(LinkedList<String> logVals) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(logF, true));
        for(String i : logVals)
        	bw.append(i);
        bw.close();
	}
	
}
