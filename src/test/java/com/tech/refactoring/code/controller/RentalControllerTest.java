package com.tech.refactoring.code.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import com.tech.refactoring.code.model.Customer;
import com.tech.refactoring.code.model.MovieRental;
import com.tech.refactoring.code.service.RentalInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
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
        String expected = "Rental Record for Abhi\n"
                +"Amount owed is 0.0\n"
                +"You earned 0 frequent points\n";
        when(rentalInfoService.statement(any(Customer.class))).thenReturn(expected);
        Customer customer = new Customer("Abhi",new ArrayList<MovieRental>());

        ResponseEntity<String> responseEntity = rentalController.statement(customer);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(expected);
    }
}