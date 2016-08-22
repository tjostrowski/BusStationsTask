package org.otto.initializer;

import org.otto.configuration.Configuration;
import org.otto.configuration.ConfigurationFactory;
import org.otto.configuration.ConfigurationProvider;
import org.otto.json.DirectConnectionCheckResult;
import org.otto.json.JsonSerializer;
import org.otto.json.JsonSerializerFactory;
import org.apache.log4j.Logger;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Created by tomek on 2016-08-20.
 */
public class RestHandler {
    private static final Logger LOGGER = Logger.getLogger(RestHandler.class);

    private ConfigurationProvider provider;
    private Configuration configuration;

    public RestHandler() {
        provider = ConfigurationFactory.fetchConfigurationProvider();
        configuration = ConfigurationFactory.fetchConfiguration();
    }

    public void initialize() {
        initializeConfig();
        initializeServices();
    }

    public void initializeConfig() {
        port(provider.getPort());
    }

    public void initializeServices() {
        get(provider.getBasePath() + "/:dep_sid/:arr_sid", (request, response) -> {
            final int depSid = Integer.valueOf(request.params(":dep_sid"));
            final int arrSid = Integer.valueOf(request.params(":arr_sid"));

            DirectConnectionCheckResult res = null;
            if (!configuration.isStationIdConfigured(depSid)) {
                final String msg = String.format("%s: %d", MessageHandler.STATION_IDS_NOT_CONFIGURED.getMsg(), depSid);
                LOGGER.error(msg);
                res = new DirectConnectionCheckResult(depSid, arrSid, false);
//                return msg;
            }
            else if (!configuration.isStationIdConfigured(arrSid)) {
                final String msg = String.format("%s: %d", MessageHandler.STATION_IDS_NOT_CONFIGURED.getMsg(), arrSid);
                LOGGER.error(msg);
                res = new DirectConnectionCheckResult(depSid, arrSid, false);
//                return msg;
            } else {
                boolean hasDirectConnection = configuration.hasDirectBusRoutes(depSid, arrSid);
                res = new DirectConnectionCheckResult(depSid, arrSid, hasDirectConnection);
            }

            JsonSerializer serializer = JsonSerializerFactory.fetchSerializer();
            return serializer.serialize(res);
        });
    }

    private static enum MessageHandler {
        STATION_IDS_NOT_CONFIGURED("Station id is not configured");

        private String msg;

        MessageHandler(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }
}
