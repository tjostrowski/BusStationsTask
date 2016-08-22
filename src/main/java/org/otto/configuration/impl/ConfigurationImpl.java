package org.otto.configuration.impl;

import org.apache.log4j.Logger;
import org.otto.configuration.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by tomek on 2016-08-20.
 */
public class ConfigurationImpl implements Configuration {
    private static final Logger LOGGER = Logger.getLogger(ConfigurationImpl.class);

    private int expectedNumRoutes;

    private Set<Integer> busRouteIds = new HashSet<>();
    private Map<Integer, Map<Integer, Integer>> busStationsRoutes = new HashMap<>(); // busStationId -> busStation, pos in route

    @Override
    public void onNewBusRoute(int busRouteId) {
        LOGGER.debug("Adding new bus route with id: " + busRouteId);
        busRouteIds.add(busRouteId);
    }

    @Override
    public void onNewBusRouteOutstation(int busRouteId, int busStationId, int routePosition) {
        LOGGER.debug(String.format("Adding connection for bus route:%d with id:%d and position:%d",
                busRouteId, busStationId, routePosition));
        if (!busStationsRoutes.containsKey(busStationId)) {
            busStationsRoutes.put(busStationId, new HashMap<>());
        }
        Map<Integer, Integer> stationRoutes = busStationsRoutes.get(busStationId);
        stationRoutes.put(busRouteId, routePosition);
    }

    @Override
    public boolean hasDirectBusRoutes(int busDepStationId, int busArrStationId) {
        LOGGER.debug(String.format("Checking if:%d has connection to:%d", busDepStationId, busArrStationId));
        final Map<Integer, Integer> busDepStationRoutes = busStationsRoutes.get(busDepStationId);
        final Map<Integer, Integer> busArrStationRoutes = busStationsRoutes.get(busArrStationId);

        Set<Integer> busDepRouteIds = busDepStationRoutes.keySet();
        Set<Integer> busArrRouteIds = busArrStationRoutes.keySet();

        for (Integer busRouteId : busDepRouteIds) {
            if (busArrRouteIds.contains(busRouteId)
                    /*&& busDepStationRoutes.get(busRouteId) < busArrStationRoutes.get(busRouteId)*/ ) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void setExpectedNumBusRoutes(int expectedNumBusRoutes) {
        LOGGER.debug("Configured number of bus routes is: " + expectedNumBusRoutes);
        this.expectedNumRoutes = expectedNumBusRoutes;
    }

    @Override
    public int getExpectedNumBusRoutes() {
        return expectedNumRoutes;
    }

    @Override
    public int getActualNumBusRoutes() {
        return busRouteIds.size();
    }

    @Override
    public boolean isStationIdConfigured(int busStationId) {
        return busStationsRoutes.keySet().contains(busStationId);
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "busRouteIds=" + busRouteIds +
                ", busStationsRoutes=" + busStationsRoutes +
                '}';
    }
}
