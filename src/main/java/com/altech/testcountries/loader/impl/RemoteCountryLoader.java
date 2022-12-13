package com.altech.testcountries.loader.impl;

import com.altech.testcountries.domain.dto.CountryDto;
import com.altech.testcountries.exception.RemoteLoadingException;
import com.altech.testcountries.loader.CountryLoader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class RemoteCountryLoader implements CountryLoader {
    private final RestTemplate restTemplate;
    private final String url;

    @Override
    public Map<String, CountryDto> loadCountries() {
        try {
            log.info("Trying to get countries from remote source: {}", url);
            var countryMap = new HashMap<String, CountryDto>();
            var response = restTemplate.getForObject(url, CountryDto[].class);
            for (CountryDto country : response) {
                countryMap.put(country.getCca3(), country);
            }
            log.info("{} countries has been mapped", response.length);
            return countryMap;
        } catch (Exception e) {
            log.error("Something went wrong until getting countries. Reason: {}", e.getMessage());
            throw new RemoteLoadingException(e.getMessage());
        }
    }
}
