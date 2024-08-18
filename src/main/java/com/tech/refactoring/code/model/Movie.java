package com.tech.refactoring.code.model;

import com.tech.refactoring.code.util.MovieCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private String title;
    private MovieCategory category;
}
