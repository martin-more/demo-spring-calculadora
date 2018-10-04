package com.everis.tdp.democalculadora.util;

public enum CalculatorType {
	ADD("+", "add"), SUBTRACT("-", "subtract"), MULTIPLY("*", "multiply"), DIVIDE("/", "divide");
	
	private String key;
	private String name;

	private CalculatorType(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
