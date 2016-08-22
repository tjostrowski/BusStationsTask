package org.otto.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by tomek on 2016-08-20.
 */
public interface Configuration {

    void onNewBusRoute(int busRouteId);
    void onNewBusRouteOutstation(int busRouteId, int busStationId, int routePosition);
    boolean hasDirectBusRoutes(int busDepStationId, int busArrStationId);
    void setExpectedNumBusRoutes(int expectedNumBusRoutes);
    boolean isStationIdConfigured(int busStationId);
    int getActualNumBusRoutes();
    int getExpectedNumBusRoutes();
}
