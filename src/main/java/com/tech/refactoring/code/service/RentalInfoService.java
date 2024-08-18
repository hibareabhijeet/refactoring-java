package com.tech.refactoring.code.service;

import com.tech.refactoring.code.model.Customer;
import com.tech.refactoring.code.model.Movie;
import com.tech.refactoring.code.model.MovieRental;
import com.tech.refactoring.code.util.Constants;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.tech.refactoring.code.util.MovieCodes.*;

@Service
public class RentalInfoService {

    public String statement(Customer customer) {
        HashMap<String, Movie> movies = new HashMap();
        movies.put("F001", new Movie("You've Got Mail", REGULAR));
        movies.put("F002", new Movie("Matrix", REGULAR));
        movies.put("F003", new Movie("Cars", CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", NEW_RELEASE));

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        String result = "Rental Record for " + customer.getName() + "\n";
        for (MovieRental r : customer.getRentals()) {
            double thisAmount = 0;

            // determine amount for each movie
            if (movies.get(r.getMovieId()).getCode().equals(REGULAR)) {
                thisAmount = 2;
                if (r.getDays() > 2) {
                    thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
                }
            }
            if (movies.get(r.getMovieId()).getCode().equals(NEW_RELEASE)) {
                thisAmount = r.getDays() * 3;
            }
            if (movies.get(r.getMovieId()).getCode().equals(CHILDRENS)) {
                thisAmount = 1.5;
                if (r.getDays() > 3) {
                    thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
                }
            }

            //add frequent bonus points
            frequentEnterPoints++;
            // add bonus for a two day new release rental
            if (movies.get(r.getMovieId()).getCode() == NEW_RELEASE && r.getDays() > 2) frequentEnterPoints++;

            //print figures for this rental
            result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
            totalAmount = totalAmount + thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentEnterPoints + " frequent points\n";

        return result;
    }
}
