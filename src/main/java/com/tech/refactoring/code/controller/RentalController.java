package com.tech.refactoring.code.controller;

import com.tech.refactoring.code.model.Customer;
import com.tech.refactoring.code.service.RentalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rental")
public class RentalController {
    @Autowired
    RentalInfoService rentalInfoService;

    @GetMapping("statement")
    public ResponseEntity<String> statement(@RequestBody Customer customer) {
        System.out.println(customer.getName());
        System.out.println(customer.getRentals());
        return ResponseEntity.ok().body(rentalInfoService.statement(customer));
    }
}
