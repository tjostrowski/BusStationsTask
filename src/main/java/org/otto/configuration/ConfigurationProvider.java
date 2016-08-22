package org.otto.configuration;

/**
 * Created by tomek on 2016-08-20.
 */
public interface ConfigurationProvider {
    int getPort();
    String getBasePath();
}
