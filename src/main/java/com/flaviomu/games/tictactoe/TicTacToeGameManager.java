package com.flaviomu.games.tictactoe;

import com.flaviomu.games.config.TicTacToeConfiguration;
import com.flaviomu.games.core.Computer;
import com.flaviomu.games.core.Human;
import com.flaviomu.games.generic.Game;
import com.flaviomu.games.generic.GameManager;
import com.flaviomu.games.generic.Player;
import com.flaviomu.games.generic.PlayerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.*;


// TODO Generalise the role of TicTacToeGameManager and/or create a TicTacToeManager
public class TicTacToeGameManager extends GameManager {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private Properties gameProperties;

    private ExecutorService executorService;
    private List<Callable<Game>> games;


    /**
     * Initialise the games creating playground and players
     *
     * @param gamesNumber the number of TicTacToe games to be created
     * @param gameProperties the TicTacToe games properties
     */
    public TicTacToeGameManager(int gamesNumber, Properties gameProperties) {
        this.gameProperties = gameProperties;
        executorService = Executors.newFixedThreadPool(gamesNumber);
        games = new ArrayList<>();

        // TODO
        // Create Application playground and players for each generic games
        for (int gameNumber = 1; gameNumber <= gamesNumber; gameNumber++) {
            int playgroundSize = Integer.parseInt(gameProperties.getProperty(TicTacToeConfiguration.getPlaygroundSizeKey()));
            TicTacToePlayground ticTacToePlayground = new TicTacToePlayground(playgroundSize);

            Game game = createGame(gameNumber, ticTacToePlayground);
            games.add(game);

            log.info("Starting Game " + gameNumber);
            log.info("Game " + gameNumber + " -> TicTacToePlayground State:");
            ticTacToePlayground.printPlayground();
        }

    }


    /*
        Create and initialise a  TicTacToe games
     */
    private Game createGame(int gameNumber, TicTacToePlayground ticTacToePlayground) {
        // Load Application configurations

        String computerSymbol = gameProperties.getProperty(TicTacToeConfiguration.getComputerSymbolKey());
        String player1Symbol = gameProperties.getProperty(TicTacToeConfiguration.getPlayer1SymbolKey());
        String player2Symbol = gameProperties.getProperty(TicTacToeConfiguration.getPlayer2SymbolKey());

        List<Player> players = new ArrayList<>();
        players.add(new Computer("Computer", computerSymbol));
        players.add(new Human("Player1", player1Symbol));
        players.add(new Human("Player2", player2Symbol));

        return new TicTacToeGame(gameNumber, ticTacToePlayground, players);
    }


    /**
     * Start the games managed by this manager
     *
     */
    public void startGames() {
        try {
            // Start the games
            List<Future<Game>> winners = executorService.invokeAll(games);

            winners.stream()
                    .map(winner -> {
                        try {
                            return winner.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(game -> {
                        printGameResult((TicTacToeGame) game);

                        while (true) {
                            Scanner scanner = new Scanner(System.in);
                            System.out.print("\n\n\n\tWould you like to play again [y/N]? ");
                            String yesNo = scanner.next();
                            if (String.valueOf(yesNo.charAt(0)).equals("y")) {
                                System.out.println();
                                ((TicTacToeGame) game).resetGame();

                                Future<Game> winner = executorService.submit(game);
                                try {
                                    printGameResult((TicTacToeGame) winner.get());
                                } catch (InterruptedException | ExecutionException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.exit(0);
                            }
                        }
                    });
        } catch (InterruptedException e) {
            log.error("Play time is over: back to work!");
            e.printStackTrace();
        }
    }


    /*
        Print the result of a games assuming it finished with a victory or a draw
     */
    private void printGameResult(TicTacToeGame game) {
        TicTacToeGame ticTacToeGame = game;
        if (ticTacToeGame.getWinner() != null) {
            System.out.println();
            System.out.println("\t************************************");
            System.out.println("\tGame " + ticTacToeGame.getNumber() + ": the winner is " + ((PlayerImpl) ticTacToeGame.getWinner()).getName());
            System.out.println("\t************************************");
        }
        else {
            System.out.println();
            System.out.println("\t************************************");
            System.out.println("\tGame " + ticTacToeGame.getNumber() + " ended with a draw");
            System.out.println("\t************************************");
        }
    }
}
