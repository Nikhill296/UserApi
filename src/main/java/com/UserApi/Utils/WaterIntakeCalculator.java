package com.UserApi.Utils;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Scanner;

public class WaterIntakeCalculator {

    public static void main(String[] args) {
    	
//    	LocalDate birthdate = LocalDate.of(1999, 06, 29);
//    	
////    	birthdate.minus(null)
//    	Period age =  Period.between(birthdate, LocalDate.now());
////    			Duration.between(birthdate, LocalDate.now());
//    	System.out.println(age.getYears());

    	
    	LocalTime wakeupTime = LocalTime.of(07, 30);
	    LocalTime bedTime = LocalTime.now();
		
	    Duration duration = Duration.between(wakeupTime, bedTime);
    	System.out.println(duration);
	    System.out.println(duration.toHours());
    	System.out.println(duration.toMinutes());

		double totalWakeTime = 0;
		
    	
    }
    
    private void calculatedaliyWaterComsum()
    {

        Scanner scanner = new Scanner(System.in);

        // Input from user
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Enter your gender (M/F): ");
        char gender = scanner.next().charAt(0);

        System.out.print("Enter your weight in kg: ");
        double weightKg = scanner.nextDouble();

        System.out.print("Enter your height in cm: ");
        double heightCm = scanner.nextDouble();

        System.out.print("Enter exercise duration in minutes: ");
        int exerciseMinutes = scanner.nextInt();

        // Base water requirement (in ounces)
        double weightLbs = weightKg * 2.20462; // Convert kg to lbs
        double baseWaterOunces = weightLbs * 0.5;

        // Additional water for exercise
        double exerciseWaterOunces = (exerciseMinutes / 30) * 12;

        // Adjustments based on gender
        if (gender == 'M' || gender == 'm') {
            baseWaterOunces += 10; // Men need slightly more water
        }

        // Body Surface Area (BSA) factor
        double bsa = Math.sqrt((heightCm * weightKg) / 3600);
        double bsaFactorOunces = bsa * 10; // Adjust based on BSA

        // Total water requirement (in ounces)
        double totalWaterOunces = baseWaterOunces + exerciseWaterOunces + bsaFactorOunces;

        // Convert to liters
        double totalWaterLiters = totalWaterOunces * 0.0295735;

        // Output result
        System.out.printf("Your daily water intake requirement is approximately: %.2f liters.%n", totalWaterLiters);

        scanner.close();
    
    }
}
