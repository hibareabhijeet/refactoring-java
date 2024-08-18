package com.tech.refactoring.code.util;

import com.tech.refactoring.code.model.Movie;

import java.util.HashMap;

import static com.tech.refactoring.code.util.MovieCodes.*;

public class Constants {
    public static HashMap<String, Movie> movies = new HashMap();

    static{
        movies.put("F001", new Movie("You've Got Mail", REGULAR));
        movies.put("F002", new Movie("Matrix", REGULAR));
        movies.put("F003", new Movie("Cars", CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", NEW_RELEASE));
    }
}
