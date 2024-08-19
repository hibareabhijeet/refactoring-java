package com.tech.refactoring.code.service;

import com.tech.refactoring.code.model.Customer;
import com.tech.refactoring.code.model.MovieRental;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentalInfoServiceTest {

    @Autowired
    RentalInfoService rentalInfoService;

    @Test
    public void testCustomerWithEmptyRentals() {
        Customer customer = new Customer("Abhi",new LinkedHashSet<>());
        String expected = "Rental Record for Abhi\n"
                +"Amount owed is 0.0\n"
                +"You earned 0 frequent points\n";
        assertEquals(expected,rentalInfoService.getStatement(customer));
    }

    @Test
    public void testAddTwoRegularMovies() {
        Set<MovieRental> inputMovieRental = new LinkedHashSet<>(
                Arrays.asList(new MovieRental("F001", 3),
                        new MovieRental("F002", 1))
        );
        Customer customer = new Customer("Abhi",inputMovieRental);
        String expected = "Rental Record for Abhi\n" +
                "\tYou've Got Mail\t3.5\n" +
                "\tMatrix\t2.0\n" +
                "Amount owed is 5.5\n" +
                "You earned 2 frequent points\n";
        assertEquals(expected,rentalInfoService.getStatement(customer));
    }

    @Test
    public void testAddOneRegularMovies() {
        Set<MovieRental> inputMovieRental = new LinkedHashSet<>(
                Arrays.asList(new MovieRental("F002", 1))
        );
        Customer customer = new Customer("Abhi",inputMovieRental);
        String expected = "Rental Record for Abhi\n" +
                "\tMatrix\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent points\n";
        assertEquals(expected,rentalInfoService.getStatement(customer));
    }

    @Test
    public void testAddOneChildrenMovies() {
        Set<MovieRental> inputMovieRental = new LinkedHashSet<>(
                Arrays.asList(new MovieRental("F003", 6))
        );
        Customer customer = new Customer("Abhi",inputMovieRental);
        String expected = "Rental Record for Abhi\n" +
                "\tCars\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 1 frequent points\n";
        assertEquals(expected,rentalInfoService.getStatement(customer));
    }

    @Test
    public void testAddOneNewMovies() {
        Set<MovieRental> inputMovieRental = new LinkedHashSet<>(
                Arrays.asList(new MovieRental("F004", 1))
        );
        Customer customer = new Customer("Abhi",inputMovieRental);
        String expected = "Rental Record for Abhi\n" +
                "\tFast & Furious X\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent points\n";
        assertEquals(expected,rentalInfoService.getStatement(customer));
    }

    @Test
    public void testAddTwoMixMovies() {
        Set<MovieRental> inputMovieRental = new LinkedHashSet<>(
                Arrays.asList(new MovieRental("F001", 3),
                        new MovieRental("F003", 2))
        );
        Customer customer = new Customer("Abhi",inputMovieRental);
        String expected = "Rental Record for Abhi\n" +
                "\tYou've Got Mail\t3.5\n" +
                "\tCars\t1.5\n" +
                "Amount owed is 5.0\n" +
                "You earned 2 frequent points\n";
        assertEquals(expected,rentalInfoService.getStatement(customer));
    }

    @Test
    public void testAddOneNewMoviesWithBonus() {
        Set<MovieRental> inputMovieRental = new LinkedHashSet<>(
                Arrays.asList(new MovieRental("F004", 4))
        );
        Customer customer = new Customer("Abhi",inputMovieRental);
        String expected = "Rental Record for Abhi\n" +
                "\tFast & Furious X\t12.0\n" +
                "Amount owed is 12.0\n" +
                "You earned 2 frequent points\n";
        assertEquals(expected,rentalInfoService.getStatement(customer));
    }

    @Test
    public void testInvalidMovieId() {
        Set<MovieRental> inputMovieRental = new LinkedHashSet<>(
                Arrays.asList(new MovieRental("F006", 4))
        );
        Customer customer = new Customer("Abhi",inputMovieRental);
        String expected = "Rental Record for Abhi\n" +
                "\tInvalid MovieId F006\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent points\n";
        assertEquals(expected,rentalInfoService.getStatement(customer));
    }
}