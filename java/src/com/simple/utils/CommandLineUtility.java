package com.simple.utils; 
import java.util.Arrays; 
import java.util.HashSet;
import java.util.Set;
import java.util.Properties;

public class CommandLineUtility{

	public static void showArgs(String args[]){
		for (String arg: Arrays.asList(args))
		{
			System.out.println(arg);
		}
	}
	
	public static Set getArgs(String args){
		Set<String> argSet = new HashSet<String>();
		for (String arg: Arrays.asList(args))
		{
			argSet.add(arg);
		}
		return argSet; 
	}
	
	public static void showSystemProperties(){
		Properties props = System.getProperties(); 
		props.list(System.out);
	}
	
	public static String getProperty(String propName){
		Properties props = System.getProperties(); 
		return props.getProperty(propName);
	} 
}