package com.tech.refactoring.code.controller;

import com.tech.refactoring.code.model.Customer;
import com.tech.refactoring.code.service.RentalInfoService;
import com.tech.refactoring.code.util.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rental")
@Slf4j
public class RentalController {
    @Autowired
    RentalInfoService rentalInfoService;

    @GetMapping("statement")
    public ResponseEntity<String> getStatement(@RequestBody Customer customer) {
        if(Validation.isRequestValid(customer)) {
            log.info("Rental Statement request received for " + customer.getName());

            return ResponseEntity.ok().body(rentalInfoService.getStatement(customer));
        }
        else{
            return ResponseEntity.badRequest().body("Request can not complete.");
        }
    }
}
