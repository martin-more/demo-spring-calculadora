package com.everis.tdp.democalculadora.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorUtil {
	
	
	public static Double roundDoubleByRange(Double d, Integer sc) {
		return BigDecimal.valueOf(d).setScale(sc, RoundingMode.HALF_UP).doubleValue();
	}

}
