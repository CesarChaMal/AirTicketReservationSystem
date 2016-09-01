package com.crossover.techtrial.util;

public enum emPlayerStatus {
	WON("Won", 0), 
	LOSER("Loser", 1),
	PLAYING("Playing", 2), 
	AUTOPILOT("Autopilot", 3); 
	
	private final String EnumName;
	private final int EnumValue;
	
	private emPlayerStatus(String n, int v) {
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
