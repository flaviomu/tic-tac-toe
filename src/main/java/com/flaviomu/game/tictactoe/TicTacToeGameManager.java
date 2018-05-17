package com.flaviomu.game.tictactoe;

import com.flaviomu.game.config.TicTacToeConfiguration;
import com.flaviomu.game.core.Computer;
import com.flaviomu.game.core.Human;
import com.flaviomu.game.generic.Game;
import com.flaviomu.game.generic.GameManager;
import com.flaviomu.game.generic.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


// TODO Generalise the role of TicTacToeGameManager and/or create a TicTacToeManager
public class TicTacToeGameManager extends GameManager {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private ExecutorService executorService;
    private List<Callable<String>> games;

    /**
     * Initialise the game creating playground and players
     *
     * @param gamesNumber
     * @param gameProperties
     */
    public TicTacToeGameManager(int gamesNumber, Properties gameProperties) {
        // Load Application configurations
        int playgroundSize = Integer.parseInt(gameProperties.getProperty(TicTacToeConfiguration.getPlaygroundSizeKey()));
        String computerSymbol = gameProperties.getProperty(TicTacToeConfiguration.getComputerSymbolKey());
        String player1Symbol = gameProperties.getProperty(TicTacToeConfiguration.getPlayer1SymbolKey());
        String player2Symbol = gameProperties.getProperty(TicTacToeConfiguration.getPlayer2SymbolKey());

        executorService = Executors.newFixedThreadPool(gamesNumber);
        games = new ArrayList<>();

        // TODO
        // Create Application playground and players for each generic game
        for (int gameNumber = 1; gameNumber <= gamesNumber; gameNumber++) {
            TicTacToePlayground ticTacToePlayground = new TicTacToePlayground(playgroundSize);
            List<Player> players = new ArrayList<>();
            players.add(new Computer("Computer", computerSymbol));
            players.add(new Human("Player1", player1Symbol));
            players.add(new Human("Player2", player2Symbol));

            Game game = new TicTacToeGame(gameNumber, ticTacToePlayground, players);
            games.add(game);

            log.info("Starting Game " + gameNumber);
            log.info("Game " + gameNumber + " -> TicTacToePlayground State:");
            ticTacToePlayground.printPlayground();
        }

    }

    /**
     * Start the games managed by this manager
     *
     */
    public void startGames() {
        try {
            // Start the games
            List<Future<String>> winners = executorService.invokeAll(games);

            winners.stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(System.out::println);

        } catch (InterruptedException e) {
            log.error("Play time is over: back to work!");
            e.printStackTrace();
        }
    }
}
