package org.otto.parser;

import org.otto.configuration.Configuration;
import org.otto.configuration.ConfigurationFactory;
import org.otto.exception.SevereException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by tomek on 2016-08-20.
 */
public class ConfigurationParser {
    private String fileName;
    private Configuration configuration;

    public ConfigurationParser(String fileName) {
        this.fileName = fileName;
        this.configuration = ConfigurationFactory.fetchConfiguration();
    }

    public void parse() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            line = reader.readLine().trim();
            final int expectedNumBusRoutes = Integer.valueOf(line);
            configuration.setExpectedNumBusRoutes(expectedNumBusRoutes);

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] outstations = line.split("\\s+");

                if (outstations.length < 3) {
                    throw new SevereException("Configuration input line should contain at least 3 numbers");
                }

                int busRouteId = Integer.valueOf(outstations[0]);
                configuration.onNewBusRoute(busRouteId);

                for (int idx = 1; idx < outstations.length; ++idx) {
                    int busStationId = Integer.valueOf(outstations[idx]);
                    configuration.onNewBusRouteOutstation(busRouteId, busStationId, idx - 1);
                }
            }

            if (expectedNumBusRoutes != configuration.getActualNumBusRoutes()) {
                throw new SevereException("Input file " + fileName + " is inconsistent");
            }

        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
