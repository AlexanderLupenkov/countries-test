package com.altech.testcountries.service;

import com.altech.testcountries.exception.CountryNotFoundException;
import com.altech.testcountries.service.algorithm.SearchAlgorithm;
import com.altech.testcountries.service.impl.RouteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {
    @Mock
    private SearchAlgorithm algorithm;
    private RouteService routeService;

    @BeforeEach
    public void initDefaultBehavior() {
        MockitoAnnotations.openMocks(this);
        routeService = new RouteServiceImpl(algorithm);
    }

    @Test
    void shouldPassedIfRouteIsCorrect() {
        final List<String> path = List.of("FRM", "TO");
        Mockito.when(algorithm.findRoute("from", "to")).thenReturn(path);

        var route = routeService.getRoute("from", "to");

        Assertions.assertEquals(route.getRoute(), path);
        Mockito.verify(algorithm, Mockito.times(1)).findRoute("from", "to");
    }

    @Test
    void shouldThrowsExceptionWhenThereIsTryingToFindRouteBetweenInvalidCountries(){
        Mockito.when(algorithm.findRoute("from", "to")).thenThrow(CountryNotFoundException.class);

        var thrownException =
            assertThrows(
                ResponseStatusException.class,
                () -> routeService.getRoute("from", "to")
            );

        assertEquals(thrownException.getMessage(), "400 BAD_REQUEST");
    }
}
