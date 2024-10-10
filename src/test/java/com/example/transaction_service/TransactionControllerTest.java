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

    @Test
    public void testGetTotalAmount() throws Exception {

        //Para agregar las mismas transacciones que en el PDF
        String transactionJson10 = "{\"amount\": 5000, \"type\": \"cars\"}";
        String transactionJson11 = "{\"amount\": 10000, \"type\": \"shopping\", \"parentId\": 10}";
        String transactionJson12 = "{\"amount\": 5000, \"type\": \"shopping\", \"parentId\": 11}";

        mockMvc.perform(put("/transactions/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson10))
                .andExpect(status().isOk());

        mockMvc.perform(put("/transactions/11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson11))
                .andExpect(status().isOk());

        mockMvc.perform(put("/transactions/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson12))
                .andExpect(status().isOk());

        mockMvc.perform(get("/transactions/sum/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sum").value(20000.0));
    }

}
