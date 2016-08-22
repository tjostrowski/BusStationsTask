package org.otto.configuration.impl;

import org.otto.configuration.ConfigurationProvider;

/**
 * Created by tomek on 2016-08-20.
 */
public class ConfigurationProviderImpl implements ConfigurationProvider {

    @Override
    public int getPort() {
        return 8088;
    }

    @Override
    public String getBasePath() {
        return "/rest/provider/goeurobus/direct";
    }
}
