package com.everis.tdp.democalculadora.pojos;

import javax.validation.constraints.NotNull;

public class Calculator {
	
	@NotNull(message = "Operator A is required")
	private Double a;
	@NotNull(message = "Operator B is required")
	private Double b;
	@NotNull(message = "Type string operation is required (+,-,*,/)")
	private String type;
	
	public Calculator() {}

	public Calculator(@NotNull(message = "Operator A is required") Double a,
			@NotNull(message = "Operator B is required") Double b) {
		super();
		this.a = a;
		this.b = b;
	}



	public Double getA() {
		return a;
	}

	public void setA(Double a) {
		this.a = a;
	}

	public Double getB() {
		return b;
	}

	public void setB(Double b) {
		this.b = b;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
