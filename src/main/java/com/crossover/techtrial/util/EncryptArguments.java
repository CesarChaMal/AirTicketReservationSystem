package com.crossover.techtrial.util;

public class EncryptArguments {
	public enum encryption_method {
		ENCRYPT,DECRYPT
	}
	private encryption_method method;
	private String text;
	
	
	
	public encryption_method getMethod() {
		return method;
	}
	public void setMethod(encryption_method method) {
		this.method = method;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
