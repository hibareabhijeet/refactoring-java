package com.tech.refactoring.code.controller;

import static com.tech.refactoring.code.util.MovieCategory.REGULAR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import com.tech.refactoring.code.model.Customer;
import com.tech.refactoring.code.model.Movie;
import com.tech.refactoring.code.model.MovieRental;
import com.tech.refactoring.code.service.RentalInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalControllerTest {

    @InjectMocks
    RentalController rentalController;
    @Mock
    RentalInfoService rentalInfoService;

    @Test
    public void testRentalController() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String expected = "Rental Record for Abhi\n" +
                "\tYou've Got Mail\t3.5\n" +
                "\tMatrix\t2.0\n" +
                "Amount owed is 5.5\n" +
                "You earned 2 frequent points\n";
        when(rentalInfoService.getStatement(any(Customer.class))).thenReturn(expected);
        Set<MovieRental> inputMovieRental = new LinkedHashSet<>(
                Arrays.asList(new MovieRental(new Movie("You've Got Mail", REGULAR), 3),
                        new MovieRental(new Movie("Matrix", REGULAR), 1))
        );
        Customer customer = new Customer("Abhi",inputMovieRental);

        ResponseEntity<String> responseEntity = rentalController.getStatement(customer);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(expected);
    }

    @Test
    public void testRentalControllerBadRequest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String expected = "Request can not complete.";
        Customer customer = new Customer("Abhi",new HashSet<MovieRental>());

        ResponseEntity<String> responseEntity = rentalController.getStatement(customer);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo(expected);
    }

    @Test
    public void testRentalControllerBadRequestNullRentals() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String expected = "Request can not complete.";
        Customer customer = new Customer("Abhi", null);

        ResponseEntity<String> responseEntity = rentalController.getStatement(customer);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo(expected);
    }

    @Test
    public void testRentalControllerBadRequestEmptyName() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String expected = "Request can not complete.";
        Set<MovieRental> inputMovieRental = new LinkedHashSet<>(
                Arrays.asList(new MovieRental(new Movie("You've Got Mail", REGULAR), 3),
                        new MovieRental(new Movie("Matrix", REGULAR), 1))
        );
        Customer customer = new Customer("",inputMovieRental);

        ResponseEntity<String> responseEntity = rentalController.getStatement(customer);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo(expected);
    }

    @Test
    public void testRentalControllerBadRequestNullName() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String expected = "Request can not complete.";
        Set<MovieRental> inputMovieRental = new LinkedHashSet<>(
                Arrays.asList(new MovieRental(new Movie("You've Got Mail", REGULAR), 3),
                        new MovieRental(new Movie("Matrix", REGULAR), 1))
        );
        Customer customer = new Customer(null,inputMovieRental);

        ResponseEntity<String> responseEntity = rentalController.getStatement(customer);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo(expected);
    }

    @Test
    public void testRentalControllerBadRequestNullCustomer() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String expected = "Request can not complete.";

        ResponseEntity<String> responseEntity = rentalController.getStatement(null);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo(expected);
    }
}