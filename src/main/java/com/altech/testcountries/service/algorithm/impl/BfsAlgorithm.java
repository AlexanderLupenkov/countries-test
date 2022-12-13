package com.altech.testcountries.service.algorithm.impl;

import com.altech.testcountries.domain.dto.CountryDto;
import com.altech.testcountries.exception.CountryNotFoundException;
import com.altech.testcountries.loader.CountryLoader;
import com.altech.testcountries.service.algorithm.SearchAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class BfsAlgorithm implements SearchAlgorithm {
    private final CountryLoader countryLoader;
    private Map<String, CountryDto> countries;

    @PostConstruct
    protected void init() {
        this.countries = countryLoader.loadCountries();
    }

    @Override
    public List<String> findRoute(String from, String to) {
        log.info("Trying to find a path from {} to {}", from, to);
        if (from.equals(to)) {
            return Collections.emptyList();
        }
        if (countries.get(from) == null || countries.get(to) == null) {
            throw new CountryNotFoundException("Provided country name doesn't exist");
        }
        var countryFrom = countries.get(from);
        var countryTo = countries.get(to);
        return findShortestPath(countryFrom, countryTo);
    }

    private List<String> findShortestPath(CountryDto from, CountryDto to) {
        final Set<String> visitedCountriesPerStep = new HashSet<>(); // find neighbour countries for current
        visitedCountriesPerStep.add(from.getCca3());

        final Deque<String> storedCountryForSearch = new LinkedList<>();
        storedCountryForSearch.add(from.getCca3());

        final Map<String, String> routeMap = new HashMap<>();

        while (!storedCountryForSearch.isEmpty()) {
            final String currentCountryForSearch = storedCountryForSearch.removeFirst();
            for (String borderCountry : countries.get(currentCountryForSearch).getNeighbourCountriesNames()) {
                if (visitedCountriesPerStep.contains(borderCountry)) {
                    continue;
                }
                visitedCountriesPerStep.add(borderCountry);
                routeMap.put(borderCountry, currentCountryForSearch);
                if (Objects.equals(borderCountry, to.getCca3())) {
                    String extracted = to.getCca3();
                    final List<String> routeList = new ArrayList<>();
                    routeList.add(extracted);
                    while (!Objects.equals(from.getCca3(), extracted)) {
                        extracted = routeMap.get(extracted);
                        routeList.add(extracted);
                    }
                    Collections.reverse(routeList); // reverse list for pretty view
                    return routeList;
                }
                storedCountryForSearch.add(borderCountry);
            }
        }
        return Collections.emptyList(); // return empty list if there aren't any country in path
    }
}
