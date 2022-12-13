package com.altech.testcountries.service.impl;

import com.altech.testcountries.domain.dto.RouteDto;
import com.altech.testcountries.exception.CountryNotFoundException;
import com.altech.testcountries.exception.RemoteLoadingException;
import com.altech.testcountries.service.RouteService;
import com.altech.testcountries.service.algorithm.SearchAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final SearchAlgorithm searchAlgorithm;

    @Override
    public RouteDto getRoute(String from, String to) {
        try {
            if (from == null || to == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided data cannot be null");
            }
            var routes = searchAlgorithm.findRoute(from, to);
            if (routes.isEmpty()) {
                log.warn("There isn't any route between countries");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There isn't any route between countries");
            }
            return RouteDto.builder()
                    .route(routes)
                    .build();
        } catch (CountryNotFoundException e) {
            log.error("Trying to get path from(to) non-existed country", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        } catch (RemoteLoadingException e) {
            log.error("Cannot load countries from external source", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
