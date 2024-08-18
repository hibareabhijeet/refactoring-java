package com.tech.refactoring.code;

import com.tech.refactoring.code.model.Customer;
import com.tech.refactoring.code.model.MovieRental;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class RefactoringJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefactoringJavaApplication.class, args);

//		String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
//
//		String result = new RentalInfo().statement(new Customer("C. U. Stomer",
//				Arrays.asList(new MovieRental("F001", 3),
//						new MovieRental("F002", 1)
////                    ,new MovieRental("F004", 4)
//				)));
//
//		if (!result.equals(expected)) {
//			throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
//		}
//
//		System.out.println(result);
//		System.out.println("Success");
	}

}
