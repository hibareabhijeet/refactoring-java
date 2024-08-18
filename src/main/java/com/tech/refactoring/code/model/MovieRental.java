package com.tech.refactoring.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.tech.refactoring.code.util.MovieCategory.NEW_RELEASE;

@Data
@AllArgsConstructor
public class MovieRental {
    private Movie movie;
    private int days;
}
