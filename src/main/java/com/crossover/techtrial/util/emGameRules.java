package com.crossover.techtrial.util;

public enum emGameRules {
	STANDARD("standard", 0, 0), 
	X_SHOT("x-shot", 1, 0),
	SUPER_CHARGE("super-charge", 2, 0), 
	DESPERATION("desperation", 3, 0); 
	
	private final String EnumName;
	private final int EnumId;
	private int EnumValue;
	
	private emGameRules(String n, int id, int v) {
		this.EnumName = n;
		this.EnumId = id;
		this.EnumValue = v;
	}

	public String getEnumName() {
		return EnumName;
	}

	public int getEnumId() {
		return EnumId;
	}

	public int getEnumValue() {
		return EnumValue;
	}
	
	public void setEnumValue(int v) {
		this.EnumValue = v;
	}
}
