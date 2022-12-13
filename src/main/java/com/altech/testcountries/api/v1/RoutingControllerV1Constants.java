package com.altech.testcountries.api.v1;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoutingControllerV1Constants {
    public static final String CONTROLLER_VERSION = "/api/v1";
    public static final String ROOT = "/routes";
    public static final String ROUTE_FROM = "from";
    public static final String ROUTE_TO = "to";
    public static final String ROUTE_URI = "/{" + ROUTE_FROM + "}/{" + ROUTE_TO + "}";
    public static final String ABSOLUTE_GET_PATH = CONTROLLER_VERSION + ROOT + ROUTE_URI;
}
