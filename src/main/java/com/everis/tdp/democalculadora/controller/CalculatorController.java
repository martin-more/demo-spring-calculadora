package com.everis.tdp.democalculadora.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.everis.tdp.democalculadora.pojos.Calculator;
import com.everis.tdp.democalculadora.service.CalculatorService;
import com.everis.tdp.democalculadora.util.Constants;
import com.everis.tdp.democalculadora.util.ResponseController;

/**
 * Calculator Controller
 * @author mmorepan
 *
 */
@RestController
public class CalculatorController {
	
	@Autowired
	private CalculatorService calculatorService;

	@RequestMapping(path="/api/v1/calculator",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			) 
	public ResponseEntity<ResponseController> performCalculation(@Valid @RequestBody Calculator calculator){
		ResponseController response;
		
		switch (calculator.getType()) {
			case "+":
				response = calculatorService.add(calculator);
				break;
			case "-":
				response = calculatorService.subtract(calculator);
				break;
			case "*":
				response = calculatorService.multiply(calculator);
				break;
			case "/":
				response = calculatorService.divide(calculator);
				break;
			default:
				response = ResponseController.getErrorResponse(Constants.OPERATOR_INVALID);
				break;
		}
		return new ResponseEntity<ResponseController>(response, HttpStatus.OK);
	}
	
	/**
	 * Bad Request Handler for HttpMessageNotReadableException exception with status 200
	 * @param exception
	 * @return ResponseEntity<ResponseController>
	 */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseController> handleExceptionJsonMalformed(HttpMessageNotReadableException exception) {
    	String errorMsg = Constants.BODY_PARSE_INVALID.concat(exception.getMessage());
        return new ResponseEntity<ResponseController>(ResponseController.getErrorResponse(errorMsg), HttpStatus.OK);
	}
	
    /**
     * Bad Request Handler for MethodArgumentNotValidException exception with status 200
     * @param exception
     * @return ResponseEntity<ResponseController>
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseController> handleExceptionMethodArgument(MethodArgumentNotValidException exception) {
    	String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());
        return new ResponseEntity<ResponseController>(ResponseController.getErrorResponse(errorMsg), HttpStatus.OK);
	}
    
}
