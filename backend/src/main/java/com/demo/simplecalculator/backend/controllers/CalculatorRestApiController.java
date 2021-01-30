package com.demo.simplecalculator.backend.controllers;

import com.demo.simplecalculator.backend.models.Equation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorRestApiController {

    @RequestMapping(value="/solve/{number1}/{sign}/{number2}", method = RequestMethod.GET)
    public ResponseEntity<Equation> solveEquation(
        @PathVariable("number1") String number1, 
        @PathVariable("sign") String sign,
        @PathVariable("number2") String number2
    ) {
        return new ResponseEntity<>(new Equation(), HttpStatus.OK);
    }
    
}
