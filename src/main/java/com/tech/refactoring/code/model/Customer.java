package com.tech.refactoring.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Customer {
    private String name;
    private Set<MovieRental> rentals;
}
