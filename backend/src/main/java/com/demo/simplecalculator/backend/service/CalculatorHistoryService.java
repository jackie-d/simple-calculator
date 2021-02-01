package com.demo.simplecalculator.backend.service;

import com.demo.simplecalculator.backend.models.Equation;
import com.demo.simplecalculator.backend.repository.CalculatorHistoryRepository;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CalculatorHistoryService {

    private CalculatorHistoryRepository calculatorHistoryRepository;
    
    public CalculatorHistoryService(CalculatorHistoryRepository calculatorHistoryRepository) {
        this.calculatorHistoryRepository = calculatorHistoryRepository;
    }
    
    public List<Equation> getHistory(){
        return calculatorHistoryRepository.findAll();
    }
    
}
