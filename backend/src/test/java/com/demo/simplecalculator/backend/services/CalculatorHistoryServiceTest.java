package com.demo.simplecalculator.backend.services;

import com.demo.simplecalculator.backend.models.Equation;
import com.demo.simplecalculator.backend.repository.CalculatorHistoryRepository;
import com.demo.simplecalculator.backend.service.CalculatorHistoryService;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CalculatorHistoryServiceTest {
    
    @Autowired
    private CalculatorHistoryRepository calculatorHistoryRepository;
    
    @Test
    void getHistory(){
        Equation firstEquation = new Equation(10, 20, Equation.Sign.PLUS, 30);
        calculatorHistoryRepository.save(firstEquation);
        
        Equation secondEquation = new Equation(0, 0, Equation.Sign.PLUS, 0);
        calculatorHistoryRepository.save(secondEquation);
        
        Equation lastEquation = new Equation(40, 1000, Equation.Sign.DIVISION, 0.004);
        calculatorHistoryRepository.save(lastEquation);
        
        CalculatorHistoryService calculatorHistoryService = new CalculatorHistoryService(calculatorHistoryRepository);

        List<Equation> equationsList = calculatorHistoryService.getHistory();
        Equation savedFirstEquation = equationsList.get(0);
        Equation savedLastEquation = equationsList.get(equationsList.size()-1);
        
        assertEquals(equationsList.size(), 3);
        
        assertEquals(savedFirstEquation.getNumber1(), firstEquation.getNumber1());
        assertEquals(savedFirstEquation.getNumber2(), firstEquation.getNumber2());
        assertEquals(savedFirstEquation.getSign(), firstEquation.getSign());
        assertEquals(savedFirstEquation.getResult(), firstEquation.getResult());
        Assertions.assertNotNull(savedLastEquation.getId());
        
        assertEquals(savedLastEquation.getNumber1(), lastEquation.getNumber1());
        assertEquals(savedLastEquation.getResult(), lastEquation.getResult());
        Assertions.assertNotNull(savedLastEquation.getId());
    }
    
}
