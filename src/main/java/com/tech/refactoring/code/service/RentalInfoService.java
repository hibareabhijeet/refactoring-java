package com.tech.refactoring.code.service;

import com.tech.refactoring.code.model.Customer;
import com.tech.refactoring.code.model.Movie;
import com.tech.refactoring.code.model.MovieRental;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static com.tech.refactoring.code.util.Constants.movies;
import static com.tech.refactoring.code.util.MovieCategory.*;

@Slf4j
@Service
public class RentalInfoService {

    /**
     * @param customer input
     * @return Gives final rental statement
     */
    public String getStatement(Customer customer) {

        AtomicReference<Double> totalAmount = new AtomicReference<>((double) 0);
        AtomicInteger frequentEnterPoints = new AtomicInteger();
        StringBuffer result = new StringBuffer("Rental Record for " + customer.getName() + "\n");

        customer.getRentals().stream().forEach( rental -> {
            Movie movie = movies.get(rental.getMovieId());
            if(movie != null){
            double thisAmount = calculateAmount(rental);

            frequentEnterPoints.addAndGet(getTotalFrequentsPoints(rental));

            //print figures for this rental
            result.append( "\t" + movie.getTitle() + "\t" + thisAmount + "\n");
            totalAmount.set(totalAmount.get() + thisAmount);


            }else{
                result.append( "\t" + "Invalid MovieId " + rental.getMovieId() + "\n");
            }
        });

        // add footer lines
        result.append( "Amount owed is " + totalAmount + "\n");
        result.append( "You earned " + frequentEnterPoints + " frequent points\n");

        return result.toString();
    }


    /**
     * determine amount for each movie
     * @return Amount for individual movie
     */
    private double calculateAmount(MovieRental movieRentalDetails){

        double thisAmount = 0;
        switch(movies.get(movieRentalDetails.getMovieId()).getCategory()){
            case REGULAR:
                thisAmount = 2;
                if (movieRentalDetails.getDays() > 2) {
                    thisAmount = ((movieRentalDetails.getDays() - 2) * 1.5) + thisAmount;
                }
                break;
            case NEW_RELEASE:
                thisAmount = movieRentalDetails.getDays() * 3;
                break;
            case CHILDRENS:
                thisAmount = 1.5;
                if (movieRentalDetails.getDays() > 3) {
                    thisAmount = ((movieRentalDetails.getDays() - 3) * 1.5) + thisAmount;
                }
                break;
        }
        return thisAmount;
    }

    /**
     * add frequent bonus points
     * @return frequentEnterPoints
     */
    private int getTotalFrequentsPoints(MovieRental movieRentalDetails){
        int frequentEnterPoints = 0;
        frequentEnterPoints++;
        // add bonus for a two day new release rental
        if (movies.get(movieRentalDetails.getMovieId()).getCategory().equals(NEW_RELEASE) && movieRentalDetails.getDays() > 2) {frequentEnterPoints++;}

        return frequentEnterPoints;
    }
}
