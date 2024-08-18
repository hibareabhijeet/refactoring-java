package com.tech.refactoring.code.model;

import com.tech.refactoring.code.util.MovieCodes;

public class Movie {
    private String title;
    private MovieCodes code;

    public Movie(String title, MovieCodes code) {
        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public MovieCodes getCode() {
        return code;
    }

}
