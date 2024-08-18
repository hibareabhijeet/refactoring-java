package com.tech.refactoring.code.model;

import static com.tech.refactoring.code.util.Constants.movies;
import static com.tech.refactoring.code.util.MovieCodes.NEW_RELEASE;

public class MovieRental {
    private String movieId;
    private int days;

    public MovieRental(String movieId, int days) {
        this.movieId = movieId;
        this.days = days;
    }

    public String getMovieId() {
        return movieId;
    }

    public int getDays() {
        return days;
    }


    public double calculateAmount(){
        // determine amount for each movie
        double thisAmount = 0;
        switch(movies.get(getMovieId()).getCode()){
            case REGULAR:
                thisAmount = 2;
                if (getDays() > 2) {
                    thisAmount = ((getDays() - 2) * 1.5) + thisAmount;
                }
                break;
            case NEW_RELEASE:
                thisAmount = getDays() * 3;
                break;
            case CHILDRENS:
                thisAmount = 1.5;
                if (getDays() > 3) {
                    thisAmount = ((getDays() - 3) * 1.5) + thisAmount;
                }
                break;
        }
        return thisAmount;
    }

    public int getTotalFrequentsPoints(){
        int frequentEnterPoints = 1;
        //add frequent bonus points
//        frequentEnterPoints++;
        // add bonus for a two day new release rental
        if (movies.get(getMovieId()).getCode().equals(NEW_RELEASE) && getDays() > 2) {frequentEnterPoints++;}

        return frequentEnterPoints;
    }
}
