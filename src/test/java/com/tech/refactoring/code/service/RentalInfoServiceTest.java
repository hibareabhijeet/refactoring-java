package com.tech.refactoring.code.service;

import com.tech.refactoring.code.model.Customer;
import com.tech.refactoring.code.model.Movie;
import com.tech.refactoring.code.model.MovieRental;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tech.refactoring.code.util.MovieCodes.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentalInfoServiceTest {

    @Autowired
    RentalInfoService rentalInfoService;

    @Test
    public void testCustomerWithEmptyRentals() {
        Customer customer = new Customer("Abhi",new ArrayList<MovieRental>());
        String expected = "Rental Record for Abhi\n"
                +"Amount owed is 0.0\n"
                +"You earned 0 frequent points\n";
        assertEquals(expected,rentalInfoService.getStatement(customer));
    }

    @Test
    public void testAddTwoRegularMovies() {
        List<MovieRental> inputMovieRental = new ArrayList<>(
                Arrays.asList(new MovieRental(new Movie("You've Got Mail", REGULAR), 3),
                        new MovieRental(new Movie("Matrix", REGULAR), 1))
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
        List<MovieRental> inputMovieRental = new ArrayList<>(
                Arrays.asList(new MovieRental(new Movie("Matrix", REGULAR), 1))
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
        List<MovieRental> inputMovieRental = new ArrayList<>(
                Arrays.asList(new MovieRental(new Movie("Cars", CHILDRENS), 6))
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
        List<MovieRental> inputMovieRental = new ArrayList<>(
                Arrays.asList(new MovieRental(new Movie("Fast & Furious X", NEW_RELEASE), 1))
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
        List<MovieRental> inputMovieRental = new ArrayList<>(
                Arrays.asList(new MovieRental(new Movie("You've Got Mail", REGULAR), 3),
                        new MovieRental(new Movie("Cars", CHILDRENS), 2))
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
        List<MovieRental> inputMovieRental = new ArrayList<>(
                Arrays.asList(new MovieRental(new Movie("Fast & Furious X", NEW_RELEASE), 4))
        );
        Customer customer = new Customer("Abhi",inputMovieRental);
        String expected = "Rental Record for Abhi\n" +
                "\tFast & Furious X\t12.0\n" +
                "Amount owed is 12.0\n" +
                "You earned 2 frequent points\n";
        assertEquals(expected,rentalInfoService.getStatement(customer));
    }
}