package com.everis.tdp.democalculadora.util;

import java.text.MessageFormat;

import org.springframework.stereotype.Service;

import com.everis.tdp.democalculadora.pojos.Calculator;

@Service
public class ResponseControllerService {
	
	/**
	 * Prepare response message success like '[{0}, {1}]. {2} correctly done!'
	 * Where:
	 * 	0: Calculator.A
	 * 	1: Calculator.B
	 * 	2: CalculatorType (sum,subtract,multiply,divide)
	 * Example:
	 *  '[134, 32]. sum correctly done!'
	 * @param type
	 * @param calculator
	 * @return
	 */
	public ResponseController getSuccessResponseCalculation(CalculatorType type, Calculator calculator) {
		return ResponseController.getSuccessResponse(
				MessageFormat.format(
						Constants.CONTROLLER_RESPONSE.MESSAGE_CALCULATION_SUCCESS,
						calculator.getA(), 
						calculator.getB(), 
						type.getName()));
	}
	
	/**
	 * Prepare response message error like 'Error performing the {0} operation'
	 * Where:
	 * 	0: CalculatorType (sum,subtract,multiply,divide)
	 * Example:
	 * 	'Error performing the 'sum' operation
	 * @param type
	 * @return
	 */
	public ResponseController getErrorResponseCalculation(CalculatorType type) {
		return ResponseController.getErrorResponse(
				MessageFormat.format(
						Constants.CONTROLLER_RESPONSE.MESSAGE_CALCULATION_ERROR, 
						type.getName()));
	}
	
	
}
