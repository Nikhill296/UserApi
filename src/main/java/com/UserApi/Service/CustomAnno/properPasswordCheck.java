package com.UserApi.Service.CustomAnno;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class properPasswordCheck implements ConstraintValidator<properPassword, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub

		if (password == null || password.isEmpty() || password.length() < 10) {
			return false;
		}

		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasSpecialCharacter = false;

		for (char ch : password.toCharArray()) {

			if (!hasUppercase || !hasLowercase || !hasSpecialCharacter) {
				if (Character.isUpperCase(ch)) {
					hasUppercase = true;
				} else if (Character.isLowerCase(ch)) {
					hasLowercase = true;
				} else if ("@#$%^&+=".indexOf(ch) >= 0) {
					hasSpecialCharacter = true;
				}
			}
		}

		return hasUppercase && hasLowercase && hasSpecialCharacter;

	}

	
}

