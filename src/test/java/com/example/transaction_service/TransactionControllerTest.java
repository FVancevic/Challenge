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
                .andDo(print()); // Para que se muestre en consola
    }

    @Test
    public void testGetTransactionsByType() throws Exception {

        //Agrego transaccion para que tenga datos que consultar
        String transactionJson = "{\"amount\": 5000, \"type\": \"cars\"}";

        mockMvc.perform(put("/transactions/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/transactions/types/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0]").value(10L))
                .andDo(print()); // Para que se muestre en consola
    }



}
