package com.tech.refactoring.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieRental {
    private String movieId;
    private int days;
}
