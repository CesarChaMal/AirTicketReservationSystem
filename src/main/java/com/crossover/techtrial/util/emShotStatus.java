package com.crossover.techtrial.util;

public enum emShotStatus {
	HIT("Hit", 0), 
	KILL("Kill", 1),
	MISS("Miss", 2), 
	NOT_FIRE("Not Fire", 3); 
	
	private final String EnumName;
	private final int EnumValue;
	
	private emShotStatus(String n, int v) {
		this.EnumName = n;
		this.EnumValue = v;
	}

	public String getName(){
		return EnumName;
	}
	public int getValue(){
		return EnumValue;
	}
}
