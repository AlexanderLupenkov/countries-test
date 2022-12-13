package com.altech.testcountries.config;

import com.altech.testcountries.service.RouteService;
import com.altech.testcountries.service.algorithm.SearchAlgorithm;
import com.altech.testcountries.service.impl.RouteServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public RouteService routeService(SearchAlgorithm algorithm) {
        return new RouteServiceImpl(algorithm);
    }
}