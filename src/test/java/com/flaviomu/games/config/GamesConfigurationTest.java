package com.flaviomu.games.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Defines the test for the @{@link GamesConfiguration} class
 *
 */
class GamesConfigurationTest {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private GamesConfiguration gamesConfiguration;

    /**
     * Tests the loading of the default games properties
     */
    @Test
    void testLoadDefaultProperties() {
        log.info("Testing loading of DEFAULT properties");

        Executable codeUnderTest = () ->
                gamesConfiguration = new GamesConfiguration("properties-file-not-existing.txt");

        assertDoesNotThrow(codeUnderTest);

        assertEquals(gamesConfiguration.getProperties().size(), 0);
    }
}