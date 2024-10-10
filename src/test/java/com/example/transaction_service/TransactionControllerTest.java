package com.example.transaction_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddTransaction() throws Exception {
        String transactionJson = "{\"amount\": 5000, \"type\": \"cars\"}";

        mockMvc.perform(put("/transactions/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\": \"ok\"}"))
                .andDo(print());
    }
    
}
