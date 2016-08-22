package org.otto;

import org.apache.log4j.Logger;
import org.otto.configuration.ConfigurationFactory;
import org.otto.exception.SevereException;
import org.otto.initializer.RestHandler;
import org.otto.parser.ConfigurationParser;

import java.io.IOException;

/**
 * Created by tomek on 2016-08-20.
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        final String inputFileName = args[0];

        LOGGER.debug("Parsing input file: " + inputFileName);
        ConfigurationParser parser = new ConfigurationParser(inputFileName);
        try {
            parser.parse();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new SevereException("Cannot parse input file: " + inputFileName);
        }
        LOGGER.debug("Input file: " + inputFileName + " parsed");
        LOGGER.debug(">> Configuration: " + ConfigurationFactory.fetchConfiguration().toString());

        LOGGER.debug("Initializing rest micro service");
        RestHandler restHandler = new RestHandler();
        restHandler.initialize();

        LOGGER.debug("Done!");
    }
}
