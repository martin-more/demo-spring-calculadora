package com.everis.tdp.democalculadora;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.everis.tdp.democalculadora.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	/**
	 * Example:
	 * Request URI 	= /api/v1/calculator
	 * Request Body	= 
	 * 		{
	 * 			"a": "15", 
	 * 			"b":"23", 
	 * 			"type":"+"
	 * 		}
	 * Response Body =
	 * 		{
	 * 			"state":"success",
	 * 			"message":"[15, 23]. add correctly done!",
	 * 			"result":38.0
	 * 		}
	 * @throws Exception
	 */
	@Test
	public void shouldReturnAddStateSuccess() throws Exception {
		mockMvc.perform(post(Constants.API_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"a\": \"15\", \"b\":\"23\", \"type\":\"+\" }"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.state").value(Constants.CONTROLLER_RESPONSE.SUCCESS_STATE));
	}
	
	/**
	 * Example:
	 * Request URI 	= /api/v1/calculator
	 * Request Body	= 
	 * 		{
	 * 			"a": "350.50", 
	 * 			"b":"0", 
	 * 			"type":"/"
	 * 		}
	 * Response Body =
	 * 		{
	 * 			"state":"error",
	 * 			"message":"Error performing the divide operation",
	 * 			"result":"NaN"
	 * 		}
	 * @throws Exception
	 */
	@Test
	public void shouldReturnDivisionByZeroErro() throws Exception {
		mockMvc.perform(post(Constants.API_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"a\": \"350.50\", \"b\":\"0\", \"type\":\"/\" }"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result").value(Double.NaN));
	}
	
	/**
	 * Handle HttpMessageNotReadableException when the body is not well formed
	 * Example :
	 * 
	 * Request Body		=
	 * 		{
	 * 			"a": "350bad",
	 * 			"b":"20",
	 * 			"type":"+"
	 * 		}
	 * 
	 * Response Body	=
	 * 		{
	 * 			"state":"error",
	 * 			"message":"Malformed JSON request | JSON parse error: Cannot deserialize value of type `java.lang.Double` from String \"350bad\": not a valid Double value; 
	 * 					   nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.lang.Double` from String \"350bad\": 
	 * 					   not a valid Double value\n at [Source: (PushbackInputStream); line: 1, column: 7] (through reference chain: com.everis.tdp.democalculadora.pojos.Calculator[\"a\"])"
	 * 		} 
	 * @throws Exception
	 */
	@Test
	public void shouldHandleHttpMessageNotReadableException() throws Exception {
		mockMvc.perform(post(Constants.API_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"a\": \"350bad\", \"b\":\"20\", \"type\":\"+\" }"))
		.andDo(print())
		.andExpect(jsonPath("$.message", containsString(Constants.BODY_PARSE_INVALID)));
	}
	
	/**
	 * Handle HttpMethodArgumentNotValidException when the body is not well formed
	 * Example :
	 * 
	 * Request Body		=
	 * 		{
	 * 			"b":"20",
	 * 			"type":"+"
	 * 		}
	 * 
	 * Response Body	=
	 * 		{
	 * 			"state":"error",
	 * 			"message":"Malformed JSON request | JSON parse error: Cannot deserialize value of type `java.lang.Double` from String \"350bad\": not a valid Double value; 
	 * 					   nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.lang.Double` from String \"350bad\": 
	 * 					   not a valid Double value\n at [Source: (PushbackInputStream); line: 1, column: 7] (through reference chain: com.everis.tdp.democalculadora.pojos.Calculator[\"a\"])"
	 * 		} 
	 * @throws Exception
	 */
	@Test
	public void shouldHandleMethodArgumentNotValidException() throws Exception {
		mockMvc.perform(post(Constants.API_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"b\":\"20\", \"type\":\"+\" }"))
		.andDo(print())
		.andExpect(jsonPath("$.message", containsString("Operator A is required")));
	}
}
