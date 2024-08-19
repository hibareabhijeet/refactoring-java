package com.tech.refactoring.code.util;


import com.tech.refactoring.code.model.Movie;
import java.util.HashMap;

import static com.tech.refactoring.code.util.MovieCategory.*;

public class Constants {
    public static HashMap<String, Movie> movies = new HashMap();

    static{
        movies.put("F001", new Movie("F001","You've Got Mail", REGULAR));
        movies.put("F002", new Movie("F002","Matrix", REGULAR));
        movies.put("F003", new Movie("F003","Cars", CHILDRENS));
        movies.put("F004", new Movie("F004","Fast & Furious X", NEW_RELEASE));
    }
}