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
    private int number1;
    private int number2;
    private Sign sign = null;
    private double result;

    public Equation() {
    }

    public Equation ( long id, int number1, int number2, Sign sign, double result ) {
        this.id = id;
        this.number1 = number1;
        this.number2 = number2;
        this.sign = sign;
        this.result = result;
    }
    
    public Equation ( int number1, int number2, Sign sign, double result ) {
        this.number1 = number1;
        this.number2 = number2;
        this.sign = sign;
        this.result = result;
    }
    
    public Equation ( int number1, int number2, Sign sign ) {
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

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
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
    
}