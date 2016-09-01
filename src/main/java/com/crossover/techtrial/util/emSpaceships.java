package com.crossover.techtrial.util;

public enum emSpaceships {
	WINGER("Winger", 0, CommonConstants.WINGER), 
	ANGLE("Angle", 1, CommonConstants.ANGLE),
	A_CLASS("A-Class", 2, CommonConstants.A_CLASS), 
	B_CLASS("B-Class", 3, CommonConstants.B_CLASS), 
	S_CLASS("S-Class", 4, CommonConstants.S_CLASS);
	
	private final String EnumName;
	private final int EnumValue;
	private final char EnumCharValue;
	
	private emSpaceships(String n, int v, char c) {
		this.EnumName = n;
		this.EnumValue = v;
		this.EnumCharValue = c;
	}

	public String getName(){
		return EnumName;
	}
	public int getValue(){
		return EnumValue;
	}
	public char getCharValue(){
		return EnumCharValue;
	}
}
