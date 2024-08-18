package com.tech.refactoring.code.model;

import static com.tech.refactoring.code.util.MovieCodes.NEW_RELEASE;


public class MovieRental {
    private Movie movie;
    private int days;

    public MovieRental(Movie movie, int days) {
        this.movie = movie;
        this.days = days;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDays() {
        return days;
    }


    /**
     * determine amount for each movie
     * @return Amount for individual movie
     */
    public double calculateAmount(){

        double thisAmount = 0;
        switch(getMovie().getCode()){
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

    /**
     * add frequent bonus points
     * @return frequentEnterPoints
     */
    public int getTotalFrequentsPoints(){
        int frequentEnterPoints = 1;
        // add bonus for a two day new release rental
        if (getMovie().getCode().equals(NEW_RELEASE) && getDays() > 2) {frequentEnterPoints++;}

        return frequentEnterPoints;
    }
}
