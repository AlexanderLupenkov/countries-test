package com.altech.testcountries.domain.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class RouteDto {
    private List<String> route;
}
