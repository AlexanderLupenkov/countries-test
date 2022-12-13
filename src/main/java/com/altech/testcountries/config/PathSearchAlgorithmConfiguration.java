package com.altech.testcountries.config;

import com.altech.testcountries.loader.CountryLoader;
import com.altech.testcountries.service.algorithm.SearchAlgorithm;
import com.altech.testcountries.service.algorithm.impl.BfsAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PathSearchAlgorithmConfiguration {
    @Bean
    public SearchAlgorithm pathSearchAlgorithm(CountryLoader countryLoader) {
        return new BfsAlgorithm(countryLoader);
    }
}