package com.altech.testcountries.controller;

import com.altech.testcountries.api.v1.RoutingControllerV1Constants;
import com.altech.testcountries.domain.dto.RouteDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldPassedIfReceivedPathIsCorrect() throws Exception {
        MockHttpServletResponse response =
            mockMvc.perform(
                get(RoutingControllerV1Constants.ABSOLUTE_GET_PATH, "CZE", "ITA")
            )
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        RouteDto result = convert(RouteDto.class, response.getContentAsString());
        Assertions.assertEquals(result.getRoute(), List.of("CZE", "AUT", "ITA"));
    }

    @Test
    void shouldPassedIfReturns4xxErrorCausesInvalidProvidedData() throws Exception {
        MockHttpServletResponse response =
            mockMvc.perform(
                get(RoutingControllerV1Constants.ABSOLUTE_GET_PATH, "NONEXISTEDCOUNTRY", "ITA")
            )
            .andExpect(status().is4xxClientError())
            .andReturn()
            .getResponse();
    }

    @Test
    void shouldPassedIfReturnsEmptyListAsUnreachablePath() throws Exception {
        MockHttpServletResponse response =
            mockMvc.perform(
                get(RoutingControllerV1Constants.ABSOLUTE_GET_PATH, "USA", "RUS")
            )
            .andExpect(status().is4xxClientError())
            .andReturn()
            .getResponse();
    }

    private <T> T convert(Class<T> clazz, String content) throws JsonProcessingException {
        return objectMapper.readValue(content, clazz);
    }
}
