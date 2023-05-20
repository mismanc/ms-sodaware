package com.sodaware.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sodaware.web.model.SodaDto;
import com.sodaware.web.model.SodaStyle;
import com.sodaware.web.services.SodaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SodaController.class)
class SodaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SodaService sodaService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getSoda() throws Exception {
        mockMvc.perform(get("/api/v1/soda/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON));
    }

    @Test
    void saveSoda() throws Exception {
        SodaDto sodaDto = getSodaDto();
        when(sodaService.saveSoda(sodaDto)).thenReturn(sodaDto);
        String sodaDtoJson = objectMapper.writeValueAsString(sodaDto);
        mockMvc.perform(post("/api/v1/soda/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sodaDtoJson)).andExpect(status().isCreated());
    }

    @Test
    void updateSoda() throws Exception {
        SodaDto sodaDto = getSodaDto();
        String sodaDtoJson = objectMapper.writeValueAsString(sodaDto);

        mockMvc.perform(put("/api/v1/soda/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sodaDtoJson)).andExpect(status().isNoContent());
    }

    private SodaDto getSodaDto(){
        return SodaDto.builder().id(UUID.randomUUID())
                .sodaStyle(SodaStyle.SIMPLE)
                .sodaName("Test Soda Name")
                .upc(12312312L)
                .price(new BigDecimal("3.99"))
                .build();
    }
}
