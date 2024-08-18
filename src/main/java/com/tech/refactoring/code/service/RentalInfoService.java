package com.tech.refactoring.code.service;

import com.tech.refactoring.code.model.Customer;
import com.tech.refactoring.code.model.MovieRental;
import com.tech.refactoring.code.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class RentalInfoService {

    public String getStatement(Customer customer) {

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        StringBuffer result = new StringBuffer("Rental Record for " + customer.getName() + "\n");
        for (MovieRental rental : customer.getRentals()) {
            double thisAmount = rental.calculateAmount();

            frequentEnterPoints += rental.getTotalFrequentsPoints();

            //print figures for this rental
            result.append( "\t" + Constants.movies.get(rental.getMovieId()).getTitle() + "\t" + thisAmount + "\n");
            totalAmount = totalAmount + thisAmount;
        }
        // add footer lines
        result.append( "Amount owed is " + totalAmount + "\n");
        result.append( "You earned " + frequentEnterPoints + " frequent points\n");

        return result.toString();
    }
}
