package com.UserApi.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class CalculationUtils {

	public static int AgeCalculator(LocalDate birthDate) {
		// TODO Auto-generated method stub
    	Period age =  Period.between(birthDate, LocalDate.now());
		return age.getYears();
	}
	

	public static Long nullToEmpty(Long id) {
		// TODO Auto-generated method stub
		return (id==null)?0:id;
	}
	
	public static String nullToEmpty(String id) {
		// TODO Auto-generated method stub
		return (id==null)?"":id;
	}

}
