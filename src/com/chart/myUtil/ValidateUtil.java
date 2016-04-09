package com.chart.myUtil;

import org.apache.commons.lang3.Validate;

public class ValidateUtil extends Validate{

	public static boolean isValid(String password) {
		// TODO Auto-generated method stub
		if(password==null || password.trim().equals(""))
			return false;
		else 
			return true;
	}
	
}
