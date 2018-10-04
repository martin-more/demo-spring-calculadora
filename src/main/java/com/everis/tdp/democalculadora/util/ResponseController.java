package com.everis.tdp.democalculadora.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseController {

	private String state;
	
	@JsonInclude(Include.NON_NULL)
	private String message;
	
	@JsonInclude(Include.NON_NULL)
	private Object result;
	

	public ResponseController(String state, String message) {
		 this.state = state;
		 this.message = message;
	}
	
	public ResponseController(String state, Object result) {
		this.state = state;
		this.result = result;
	}
	
	public static ResponseController getErrorResponse(String message) {
		return new ResponseController(Constants.CONTROLLER_RESPONSE.ERROR_STATE, message);
	}
	
	public static ResponseController getSuccessResponse(String message) {
		return new ResponseController(Constants.CONTROLLER_RESPONSE.SUCCESS_STATE, message);
	}
	
	public static ResponseController getSuccessResponseWithExtraInfo(Object extraInfo) {
		return new ResponseController(Constants.CONTROLLER_RESPONSE.SUCCESS_STATE, extraInfo);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
