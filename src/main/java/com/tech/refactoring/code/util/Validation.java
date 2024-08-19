package com.tech.refactoring.code.util;

import com.tech.refactoring.code.model.Customer;

public class Validation {

    public static boolean isRequestValid(Customer customer) {
        if(customer == null || customer.getName() == null || customer.getName().isEmpty() || customer.getRentals() == null || customer.getRentals().size()<1){
            return false;
        }
        return true;
    }
}
