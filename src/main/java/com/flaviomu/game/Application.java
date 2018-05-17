package com.flaviomu.game;

import com.flaviomu.game.config.TicTacToeConfiguration;
import com.flaviomu.game.generic.GameManager;
import com.flaviomu.game.tictactoe.TicTacToeGameManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Application 2.0 Application
 *
 * Basic Game rules at:
 *
 * Added rules:
 *  -
 *
 */
public class Application {

    // TODO: rename file game.etc1.etc2.=value
    private static final String PROPERTIES_FILE_NAME = "tictactoe.properties";
    private static final String APP_NAME = Application.class.getSimpleName();
    private static final Logger log = LoggerFactory.getLogger(APP_NAME);

    public static void main(String[] args) {
        // Load and print active properties
        TicTacToeConfiguration configuration = new TicTacToeConfiguration(PROPERTIES_FILE_NAME);
        Properties properties = configuration.getProperties();
        log.debug("Starting " + APP_NAME + " with following properties: ");
        properties.keySet().forEach(prop ->
                System.out.println("\t" + prop + ": " + properties.getProperty(prop.toString())));

        // Create generic manager and start the parallel games
        int gamesNumber = 1;
        GameManager ticTacToeGameManager = new TicTacToeGameManager(gamesNumber, properties);
        ((TicTacToeGameManager)ticTacToeGameManager).startGames();
    }
}
