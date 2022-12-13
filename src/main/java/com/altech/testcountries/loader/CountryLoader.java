package com.altech.testcountries.loader;

import com.altech.testcountries.domain.dto.CountryDto;

import java.util.Map;

public interface CountryLoader {
    Map<String, CountryDto> loadCountries();
}
