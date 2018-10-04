package com.everis.tdp.democalculadora.util;

public class Constants {
	
	public static final String OPERATOR_INVALID = "Invalid operator, use (+ -, *, /)";
	public static final String BODY_PARSE_INVALID = "Malformed JSON request | ";
	public static final String API_URL = "/api/v1/calculator";
	
	public static abstract class CONTROLLER_RESPONSE {
		public static final String ERROR_STATE = "error";
		public static final String SUCCESS_STATE = "success";
		
		public static final String MESSAGE_CALCULATION_SUCCESS = "[{0}, {1}]. {2} correctly done!";
		public static final String MESSAGE_CALCULATION_ERROR = "Error performing the {0} operation";
		
	}

}
