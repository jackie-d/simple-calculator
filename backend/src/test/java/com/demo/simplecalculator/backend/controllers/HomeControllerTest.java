package com.demo.simplecalculator.backend.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;


@WebMvcTest
@ExtendWith(SpringExtension.class)
public class HomeControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Test
    void getHomeRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(forwardedUrl("/index.html"));
    }
    
}
