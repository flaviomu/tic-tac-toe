package com.flaviomu.games.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Contains all the games configuration
 *
 */
public class GamesConfiguration {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private Properties properties;

    /**
     * Creates the Games configurations
     *
     * @param propertiesFileName the file containg the properties
     */
    public GamesConfiguration(String propertiesFileName) {
        try {
            properties = new Properties();
            log.debug("Loading properties file: " + propertiesFileName);
            properties.load(this.getClass().getClassLoader().getResourceAsStream(propertiesFileName));
        } catch (NullPointerException ex) {
            log.error("Properties file not found: " + propertiesFileName);
            log.error("Exception message: " + ex.getLocalizedMessage());
        } catch (IOException ex) {
            log.error("Problems while loading properties file");
            log.error(ex.getLocalizedMessage());
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
