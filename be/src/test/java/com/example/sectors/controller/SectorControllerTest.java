package com.example.sectors.controller;

import com.example.sectors.BaseIntegrationTest;
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


class SectorControllerTest extends BaseIntegrationTest {
    private static final String BASE_URL = "/api/sector";

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        sectorRepository.save(
                TestDataCreator.createSubSector(TestDataCreator.createMainSector()));
    }

    @Test
    public void getAll() throws Exception {
        // given
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .get(BASE_URL)
                        .contentType(APPLICATION_JSON);

        // when
        ResultActions perform = mockMvc.perform(requestBuilder);

        // then
        perform
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}