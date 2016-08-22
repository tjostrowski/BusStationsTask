package org.otto.configuration;

import org.otto.configuration.impl.ConfigurationImpl;
import org.otto.configuration.impl.ConfigurationProviderImpl;

/**
 * Created by tomek on 2016-08-20.
 */
public class ConfigurationFactory {
    private static ConfigurationProvider confProvider = new ConfigurationProviderImpl();
    private static Configuration configuration = new ConfigurationImpl();

    public static ConfigurationProvider fetchConfigurationProvider() {
        return confProvider;
    }

    public static Configuration fetchConfiguration() { return configuration; }
}
