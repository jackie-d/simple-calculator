package com.demo.simplecalculator.backend.controllers;

import com.demo.simplecalculator.backend.models.Equation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorRestApiController {
    
    @Autowired
    SimpleCalculator simpleCalculator;

    @RequestMapping(value="/solve/{number1}/{sign}/{number2}", method = RequestMethod.GET)
    public ResponseEntity<Equation> solveEquation(
        @PathVariable("number1") String number1, 
        @PathVariable("sign") String sign,
        @PathVariable("number2") String number2
    ) {
        boolean isInputAcceptable = isInputAcceptable(number1, number2, sign);
        
        if ( !isInputAcceptable ) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        
        return new ResponseEntity<>(new Equation(), HttpStatus.OK);
    }
    
    private boolean isInputAcceptable(String number1, String number2, String sign) {
        return isIntNumber(number1) && isIntNumber(number2) && isValidSign(sign);
    }
    
    private boolean isIntNumber(String value) {
        try{
            Integer.valueOf(value);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    private boolean isValidSign(String value) {
        return String.valueOf( value ).matches( "[+-/\\*]" );
    }
    
}
