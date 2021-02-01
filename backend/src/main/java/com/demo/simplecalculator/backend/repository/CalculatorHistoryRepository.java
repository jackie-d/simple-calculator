package com.demo.simplecalculator.backend.repository;

import com.demo.simplecalculator.backend.models.Equation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("calculatorHistoryRepository")
public interface CalculatorHistoryRepository extends JpaRepository<Equation, Long> {
    
}