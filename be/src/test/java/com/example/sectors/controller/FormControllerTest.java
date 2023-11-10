package com.example.sectors.controller;

import com.example.sectors.BaseIntegrationTest;
import com.example.sectors.dto.FormRequest;
import com.example.sectors.entity.Sector;
import com.example.sectors.util.TestConstant;
import com.example.sectors.util.TestDataCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FormControllerTest extends BaseIntegrationTest {
    private static final String BASE_URL = "/api/form";

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        Sector sector = sectorRepository.save(TestDataCreator.createMainSector());
        formRepository.save(TestDataCreator.createForm(sector));
    }

    @Test
    public void save() throws Exception {
        // given
        FormRequest request = FormRequest.builder()
                .agreeToTerms(true)
                .sectorId(TestConstant.DEFAULT_SECTOR_ID)
                .username(TestConstant.FORM_NAME)
                .build();

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post(BASE_URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request));

        // when
        ResultActions perform = mockMvc.perform(requestBuilder);

        // then
        perform.andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        // given
        long formId = 1L;
        FormRequest request = FormRequest.builder()
                .agreeToTerms(true)
                .sectorId(TestConstant.DEFAULT_SECTOR_ID)
                .username(TestConstant.FORM_NAME)
                .build();

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .put(BASE_URL + "/" + formId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request));

        // when
        ResultActions perform = mockMvc.perform(requestBuilder);

        // then
        perform.andExpect(status().isOk());
    }
}