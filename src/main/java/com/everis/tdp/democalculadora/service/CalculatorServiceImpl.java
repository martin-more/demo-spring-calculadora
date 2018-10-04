package com.everis.tdp.democalculadora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.tdp.democalculadora.pojos.Calculator;
import com.everis.tdp.democalculadora.util.CalculatorType;
import com.everis.tdp.democalculadora.util.CalculatorUtil;
import com.everis.tdp.democalculadora.util.ResponseController;
import com.everis.tdp.democalculadora.util.ResponseControllerService;

/**
 * Fake backend
 * @author mmorepan
 *
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {
	
	@Autowired
	private ResponseControllerService responseControllerService;

	@Override
	public ResponseController add(Calculator calculator) {
		ResponseController response;
		
		Double result = calculator.getA() + calculator.getB();	// Simple operation or Call fake Backend
		
		response = responseControllerService.getSuccessResponseCalculation(CalculatorType.ADD, calculator);
		response.setResult(CalculatorUtil.roundDoubleByRange(result, 3));
		return response;
	}

	@Override
	public ResponseController subtract(Calculator calculator) {
		ResponseController response;
		
		Double result = calculator.getA() - calculator.getB();	// Simple operation or Call fake Backend
		
		response = responseControllerService.getSuccessResponseCalculation(CalculatorType.SUBTRACT, calculator);
		response.setResult(CalculatorUtil.roundDoubleByRange(result, 3));
		return response;
	}

	@Override
	public ResponseController multiply(Calculator calculator) {
		ResponseController response;
		
		Double result = calculator.getA() * calculator.getB();	// Simple operation or Call fake Backend
		
		response = responseControllerService.getSuccessResponseCalculation(CalculatorType.MULTIPLY, calculator);
		response.setResult(CalculatorUtil.roundDoubleByRange(result, 3));
		return response;
	}

	@Override
	public ResponseController divide(Calculator calculator) {
		ResponseController response;
		Double result;
		
		if (calculator.getB() == 0) {
			response = responseControllerService.getErrorResponseCalculation(CalculatorType.DIVIDE);
			response.setResult(Double.NaN);
		} else {
			result = calculator.getA() / calculator.getB();	// Simple operation or Call fake Backend 
			response = responseControllerService.getSuccessResponseCalculation(CalculatorType.DIVIDE, calculator);
			response.setResult(CalculatorUtil.roundDoubleByRange(result, 3));
		}
		return response;
	}

}
