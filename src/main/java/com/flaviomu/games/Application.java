package com.flaviomu.games;

import com.flaviomu.games.config.TicTacToeConfiguration;
import com.flaviomu.games.generic.GameManager;
import com.flaviomu.games.tictactoe.TicTacToeGameManager;
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

    // TODO: rename file games.etc1.etc2.=value
    private static final String PROPERTIES_FILE_NAME_DEFAULT = "tictactoe.properties";
    private static final String APP_NAME = Application.class.getSimpleName();
    private static final Logger log = LoggerFactory.getLogger(APP_NAME);

    public static void main(String[] args) {
        // Load and print active properties
        String propertiesFileName = PROPERTIES_FILE_NAME_DEFAULT;
        if (args.length == 1)
            propertiesFileName = args[0];
        TicTacToeConfiguration configuration = new TicTacToeConfiguration(propertiesFileName);

        Properties properties = configuration.getProperties();
        log.debug("Starting " + APP_NAME + " with following properties: ");
        properties.keySet().forEach(prop ->
                System.out.println("\t" + prop + ": " + properties.getProperty(prop.toString())));
        System.out.println();

        // Create generic manager and start the parallel games
        int gamesNumber = 1;
        GameManager ticTacToeGameManager = new TicTacToeGameManager(gamesNumber, properties);
        ((TicTacToeGameManager)ticTacToeGameManager).startGames();
    }
}
