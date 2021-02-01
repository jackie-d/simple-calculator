package com.demo.simplecalculator.backend.service;

import com.demo.simplecalculator.backend.models.Equation;
import com.demo.simplecalculator.backend.repository.CalculatorHistoryRepository;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CalculatorHistoryService {
    
    final public static int PAGE_SIZE = 10;

    private CalculatorHistoryRepository calculatorHistoryRepository;
    
    public CalculatorHistoryService(CalculatorHistoryRepository calculatorHistoryRepository) {
        this.calculatorHistoryRepository = calculatorHistoryRepository;
    }
    
    public List<Equation> getHistory(){
        return this.getHistoryByPage(0);
    }
    
    public List<Equation> getHistoryByPage(int pageOffset){
        Pageable pageable = PageRequest.of(pageOffset, PAGE_SIZE);
        return calculatorHistoryRepository.findAll(pageable).getContent();
    }
    
}
