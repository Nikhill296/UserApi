package com.UserApi.Service.CustomAnno;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class isPrimeNumValidator implements ConstraintValidator<isPrimeNum, String>{

	

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		int val = Integer.parseInt(value);
		for(int i = 2; i<= Math.sqrt(val);i++) {
			if(val%i==0)
				return false;
		}
		return true;
	}

}
