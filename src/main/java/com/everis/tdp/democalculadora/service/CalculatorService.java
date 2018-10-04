package com.everis.tdp.democalculadora.service;

import com.everis.tdp.democalculadora.pojos.Calculator;
import com.everis.tdp.democalculadora.util.ResponseController;

/**
 * Available operations
 * @author mmorepan
 *
 */
public interface CalculatorService {

	ResponseController add(Calculator calculator);
	ResponseController subtract(Calculator calculator);
	ResponseController multiply(Calculator calculator);
	ResponseController divide(Calculator calculator);
	
}
