package com.demo.simplecalculator.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Equation {
    public enum Sign {PLUS, MINUS, MULTIPLICATION, DIVISION};
    
    @Id
    @GeneratedValue
    private long id;
    private double number1;
    private double number2;
    private Sign sign = null;
    private double result;
    private boolean isNan = false;

    public Equation() {
    }

    public Equation ( long id, double number1, double number2, Sign sign, double result, boolean isNan ) {
        this.id = id;
        this.number1 = number1;
        this.number2 = number2;
        this.sign = sign;
        this.result = result;
        this.isNan = isNan;
    }
    
    public Equation ( long id, double number1, double number2, Sign sign, double result ) {
        this.id = id;
        this.number1 = number1;
        this.number2 = number2;
        this.sign = sign;
        this.result = result;
    }
    
    public Equation ( double number1, double number2, Sign sign, boolean isNan ) {
        this.number1 = number1;
        this.number2 = number2;
        this.sign = sign;
        this.isNan = isNan;
    }
    
    public Equation ( double number1, double number2, Sign sign, double result ) {
        this.number1 = number1;
        this.number2 = number2;
        this.sign = sign;
        this.result = result;
    }
    
    public Equation ( double number1, double number2, Sign sign ) {
        this.number1 = number1;
        this.number2 = number2;
        this.sign = sign;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public boolean isNan() {
        return isNan;
    }

    public void setNan(boolean isNan) {
        this.isNan = isNan;
    }
    
    
}