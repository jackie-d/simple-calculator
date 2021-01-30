package com.demo.simplecalculator.backend.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class CalculatorRestApiControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Test
    void getEquationSolution() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","12","+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
    }
    
    @Test
    void getEquationSolutionFiltersInvalidInput() throws Exception {
        // filter block
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","A","+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","","+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}",null,"+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","12","5","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","12",null,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","12","","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","12","+","?")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","12","+",null)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","12","+","")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","2,2","+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}",".2","+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","2.","+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","2.2.","+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","2-","+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","2","•","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","3.5","+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","3","+","3.0")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        // filter pass
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","3","+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
       
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","-3","+","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/solve/{number1}/{sign}/{number2}","3","+","-3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
    }
    
}
