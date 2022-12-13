package com.altech.testcountries.service;

import com.altech.testcountries.domain.dto.RouteDto;

public interface RouteService {
    RouteDto getRoute(String from, String to);
}
