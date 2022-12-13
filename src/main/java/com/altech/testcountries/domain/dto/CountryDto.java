package com.altech.testcountries.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto {
    @JsonProperty("cca3")
    private String cca3;
    @JsonProperty("borders")
    private List<String> neighbourCountriesNames;
}
