package com.altech.testcountries.api.v1;

import com.altech.testcountries.domain.dto.RouteDto;
import com.altech.testcountries.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RoutingControllerV1Constants.CONTROLLER_VERSION + RoutingControllerV1Constants.ROOT)
@RequiredArgsConstructor
public class RoutingControllerV1 {
    private final RouteService routeService;

    @GetMapping(path = RoutingControllerV1Constants.ROUTE_URI)
    public RouteDto getRoute(@PathVariable(value = RoutingControllerV1Constants.ROUTE_FROM) String from,
                             @PathVariable(value = RoutingControllerV1Constants.ROUTE_TO) String to) {
        return routeService.getRoute(from, to);
    }
}
