package com.flaviomu.games.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class TicTacToeConfiguration {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private static final String ticTacToePrefixKey = "tictactoe.";
    private static final String playgroundSizeKey = ticTacToePrefixKey + "playground-size";
    private static final String computerSymbolKey = ticTacToePrefixKey + "computer-symbol";
    private static final String player1SymbolKey = ticTacToePrefixKey + "player1-symbol";
    private static final String player2SymbolKey = ticTacToePrefixKey + "player2-symbol";

    private static final Integer PLAYGROUND_SIZE_DEFAULT = 3;
    private static final String COMPUTER_SYMBOL_DEFAULT = "C";
    private static final String PLAYER1_SYMBOL_DEFAULT = "1";
    private static final String PLAYER2_SYMBOL_DEFAULT = "2";

    private Properties properties;

    /**
     * Builds the TicTacToe games configurations
     *
     * @param configurations the general games configurations
     */
    public TicTacToeConfiguration(GamesConfiguration configurations) {
        properties = new Properties();
        if (configurations.getProperties().containsKey(playgroundSizeKey))
            properties.setProperty(getPlaygroundSizeKey(), configurations.getProperties().getProperty(playgroundSizeKey));
        else
            properties.setProperty(playgroundSizeKey, PLAYGROUND_SIZE_DEFAULT.toString());

        if (configurations.getProperties().containsKey(computerSymbolKey))
            properties.setProperty(computerSymbolKey, configurations.getProperties().getProperty(computerSymbolKey));
        else
            properties.setProperty(computerSymbolKey, COMPUTER_SYMBOL_DEFAULT);

        if (configurations.getProperties().containsKey(player1SymbolKey))
            properties.setProperty(player1SymbolKey, configurations.getProperties().getProperty(player1SymbolKey));
        else
            properties.setProperty(player1SymbolKey, PLAYER1_SYMBOL_DEFAULT);

        if (configurations.getProperties().containsKey(player2SymbolKey))
            properties.setProperty(player2SymbolKey, configurations.getProperties().getProperty(player2SymbolKey));
        else
            properties.setProperty(player2SymbolKey, PLAYER2_SYMBOL_DEFAULT);
    }

    /*public TicTacToeConfiguration(String propertiesFileName) {
        try {
            properties = new Properties();
            log.debug("Loading properties file: " + propertiesFileName);
            properties.load(this.getClass().getClassLoader().getResourceAsStream(propertiesFileName));
        } catch (NullPointerException ex) {
            log.error("Properties file not found: " + propertiesFileName);
            log.error("Exception message: " + ex.getLocalizedMessage());
            log.warn("Loading DEFAULT properties.");
            loadDefaultProperties();
        } catch (IOException ex) {
            log.error("Problems while loading properties file");
            log.error(ex.getLocalizedMessage());
            log.warn("Loading DEFAULT properties.");
            loadDefaultProperties();
        }
    }*/


    public Properties getProperties() {
        return properties;
    }

    public static String getPlaygroundSizeKey() {
        return playgroundSizeKey;
    }

    public static String getComputerSymbolKey() {
        return computerSymbolKey;
    }

    public static String getPlayer1SymbolKey() {
        return player1SymbolKey;
    }

    public static String getPlayer2SymbolKey() {
        return player2SymbolKey;
    }

    public static Integer getPlaygroundSizeDefault() {
        return PLAYGROUND_SIZE_DEFAULT;
    }

    public static String getComputerSymbolDefault() {
        return COMPUTER_SYMBOL_DEFAULT;
    }

    public static String getPlayer1SymbolDefault() {
        return PLAYER1_SYMBOL_DEFAULT;
    }

    public static String getPlayer2SymbolDefault() {
        return PLAYER2_SYMBOL_DEFAULT;
    }


    /*
        Load the default properties values
     *//*
    private void loadDefaultProperties() {
        properties.setProperty(playgroundSizeKey, PLAYGROUND_SIZE_DEFAULT.toString());
        properties.setProperty(computerSymbolKey, COMPUTER_SYMBOL_DEFAULT);
        properties.setProperty(player1SymbolKey, PLAYER1_SYMBOL_DEFAULT);
        properties.setProperty(player2SymbolKey, PLAYER2_SYMBOL_DEFAULT);
    }*/
}
