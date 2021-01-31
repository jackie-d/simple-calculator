package com.demo.simplecalculator.backend.controllers;

import com.demo.simplecalculator.backend.models.Equation;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${API_PREFIX_V}")
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
        
        Equation solution = getEquationSolution(number1, number2, sign);
        return new ResponseEntity<>(solution, HttpStatus.OK);
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
        return Equation.SignSymbols.values().contains(value);
    }
    
    private Equation getEquationSolution(String number1, String number2, String signSymbol) {
        int int1 = Integer.valueOf(number1);
        int int2 = Integer.valueOf(number2);
        
        Equation.Sign sign = Equation.getSignBySymbol(signSymbol);
        double result = 0;
        switch( sign ) {
            case PLUS:
                    result = simpleCalculator.add(int1, int2);
                    break;
            case MINUS:
                    result = simpleCalculator.subtract(int1, int2);
                    break;
            case MULTIPLICATION:
                    result = simpleCalculator.multiply(int1, int2);
                    break;
            case DIVISION:
                    result = simpleCalculator.divide(int1, int2);
                    break;
        }
        
        Equation solution = new Equation(int1, int2, sign, result);
        
        return solution;
    }

}
