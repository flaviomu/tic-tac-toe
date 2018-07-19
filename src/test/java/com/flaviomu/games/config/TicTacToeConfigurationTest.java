package com.flaviomu.games.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TicTacToeConfigurationTest")
class TicTacToeConfigurationTest {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private GamesConfiguration gamesConfiguration;
    private TicTacToeConfiguration ticTacToeConfiguration;

    @Test
    void testLoadDefaultProperties() {
        log.info("Testing loading of DEFAULT properties");


        Executable codeUnderTest = () ->
                gamesConfiguration = new GamesConfiguration("properties-file-not-existing.txt");

        assertDoesNotThrow(codeUnderTest);

        ticTacToeConfiguration = new TicTacToeConfiguration(gamesConfiguration);

        assertEquals(TicTacToeConfiguration.getPlaygroundSizeDefault().toString(),
                ticTacToeConfiguration.getProperties().getProperty(TicTacToeConfiguration.getPlaygroundSizeKey()));
        assertEquals(TicTacToeConfiguration.getComputerSymbolDefault(),
                ticTacToeConfiguration.getProperties().getProperty(TicTacToeConfiguration.getComputerSymbolKey()));
        assertEquals(TicTacToeConfiguration.getPlayer1SymbolDefault(),
                ticTacToeConfiguration.getProperties().getProperty(TicTacToeConfiguration.getPlayer1SymbolKey()));
        assertEquals(TicTacToeConfiguration.getPlayer2SymbolDefault(),
                ticTacToeConfiguration.getProperties().getProperty(TicTacToeConfiguration.getPlayer2SymbolKey()));
    }

}