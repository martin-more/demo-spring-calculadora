package com.everis.tdp.democalculadora;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//Statics imports
import static org.assertj.core.api.Assertions.assertThat;

import com.everis.tdp.democalculadora.pojos.Calculator;
import com.everis.tdp.democalculadora.service.CalculatorService;
import com.everis.tdp.democalculadora.util.ResponseController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorServiceTest {

	@Autowired
	private CalculatorService calculatorService;
	
	@Test
	public void calculatorAddTest() throws Exception {
		Calculator c = new Calculator(24.95, 15.25);
		ResponseController rc = calculatorService.add(c);
		assertThat(rc.getResult()).isEqualTo(new Double(40.20));
	}
	
	@Test
	public void calculatorSubtractTest() throws Exception {
		Calculator c = new Calculator(24.95, 15.25);
		ResponseController rc = calculatorService.subtract(c);
		assertThat(rc.getResult()).isEqualTo(new Double(9.70));
	}
	
	@Test
	public void calculatorMultiplyTest() throws Exception {
		Calculator c = new Calculator(24.95, 15.25);
		ResponseController rc = calculatorService.multiply(c);
		assertThat(rc.getResult()).isEqualTo(new Double(380.488));
	}
	
	@Test
	public void calculatorDivideTest() throws Exception {
		Calculator c = new Calculator(24.95, 15.25);
		ResponseController rc = calculatorService.divide(c);
		assertThat(rc.getResult()).isEqualTo(new Double(1.6360));
	}
	
	@Test
	public void calculatorDivideByZeroTest() throws Exception {
		Calculator c = new Calculator(24.95, 0.0);
		ResponseController rc = calculatorService.divide(c);
		assertThat(rc.getResult()).isEqualTo(Double.NaN);
	}

}
