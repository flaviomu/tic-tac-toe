package com.flaviomu.games.tictactoe;

import com.flaviomu.games.generic.Game;
import com.flaviomu.games.generic.Move;
import com.flaviomu.games.generic.Player;
import com.flaviomu.games.generic.PlayerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TicTacToeGame extends Game {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private int number;
    private TicTacToePlayground ticTacToePlayground;
    private List<Player> players;
    private Map<Player, Long> actualPlayingTimes;
    private Player winner;

    private boolean bonusMoveTimerActive;
    private boolean bonusMove;

    private ScheduledExecutorService bonusMoveScheduler;


    public TicTacToeGame(int number, TicTacToePlayground ticTacToePlayground, List<Player> players) {
        this.number = number;
        this.ticTacToePlayground = ticTacToePlayground;
        this.players = players;

        this.bonusMoveScheduler = new ScheduledThreadPoolExecutor(1);
        this.actualPlayingTimes = new HashMap<>();
        for (Player p : players)
            this.actualPlayingTimes.put(p, (long) -1);
    }

    public int getNumber() {
        return number;
    }

    public Player getWinner() {
        return winner;
    }


    /**
     * Reset the games bringing it to a clean state
     *
     */
    public void resetGame() {
        ticTacToePlayground.cleanPlayground();
        bonusMoveTimerActive = false;
        bonusMove = false;
        for (Player p : players)
            this.actualPlayingTimes.put(p, (long) -1);
        winner = null;
    }

    /**
     * Define the TicTacToe games logic
     *
     * @return the current Game when it has finished with a victory or with a draw
     */
    @Override
    public Game call() {
        // set randomly the order of the players list
        definePlayersOrder();

        Timer bonusTimer = new Timer();
        int nextPlayer = 0;
        while(true) {

            // Bonus move
            if (! bonusMoveTimerActive) {
                System.out.println();
                log.debug("Activating the bonus move timer");
                System.out.println();
                bonusMoveTimerActive = true;

                // Set and start bonus move timer runnable
                Random bonusMoveDelayGenerator = new Random();
                long bonusMoveDelay = ((long) bonusMoveDelayGenerator.nextInt(10) + 50);
                bonusMoveScheduler.schedule( () -> {
                        System.out.println();
                        log.debug("Activating the bonus move");
                        bonusMove = true;
                }, bonusMoveDelay, TimeUnit.SECONDS);
            }

            Player player;
            if (bonusMove) {
                player = getFasterPlayer(actualPlayingTimes);
                log.info(" **** BONUS MOVE **** Now " + ((PlayerImpl)player).getName() + " plays");

                bonusMoveTimerActive = false;
                bonusMove = false;
            }
            else {
                player = players.get(nextPlayer % players.size());
                nextPlayer++;

                log.info("Now " + ((PlayerImpl)player).getName() + " plays");
            }

            // Execute the move recording the time used
            long startTime = System.currentTimeMillis();
            TicTacToeMove move = null;
            try {
                move = ((TicTacToePlayer)player).doTicTacToeMove(ticTacToePlayground);
            } catch (InterruptedException e) {
                log.error("Error during " + ((PlayerImpl) player).getName() + " move");
                e.printStackTrace();
            }
            long finishTime = System.currentTimeMillis();
            long moveTime = finishTime - startTime;
            long previousMovesTime = actualPlayingTimes.get(player);
            actualPlayingTimes.put(player, previousMovesTime + moveTime);
            log.debug(((PlayerImpl)player).getName() + " moved in " + (moveTime / 1000) + " seconds");

            //System.out.println("\n");
            log.info("Game " + number + " -> TicTacToePlayground State:");
            ticTacToePlayground.printPlayground();

            if (isGameWon(move)) {
                // TODO cancel bonus move callable
                if (bonusMoveTimerActive) {
                    log.debug("Deactivating the bonus move timer");
                    bonusTimer.cancel();
                }

                //return "Game " + this.number + ": the winner is " + ((PlayerImpl) player).getName();
                winner = player;
                return this;
            }
            else if (isGameDraw(move)) {
                // TODO cancel bonus move callable
                if (bonusMoveTimerActive) {
                    log.debug("Deactivating the bonus move timer");
                    bonusTimer.cancel();
                }

                //return "Game " + this.number + ": it ended with a draw.";
                return this;
            }
        }
    }


    /**
     * Reorder the players list randomly
     *
     */
    private void definePlayersOrder() {
        List<Player> orderedPlayers = new ArrayList<>();
        Random random = new Random();

        while(! players.isEmpty()) {
            int index = random.nextInt(players.size());
            orderedPlayers.add(players.remove(index));
        }

        players = orderedPlayers;

        log.info(" The Players will play in the following order:");
        orderedPlayers.forEach(p -> System.out.println("\t" + ((PlayerImpl)p).getName()));
    }


    /**
     * Determine the player who has made all its move in the shortest actual time
     *
     * @param actualPlayingTimes the actual playing time of the players
     * @return the fastest player
     */
    private Player getFasterPlayer(Map<Player, Long> actualPlayingTimes) {
        Player player = actualPlayingTimes.keySet().iterator().next();
        Long time = actualPlayingTimes.get(player);

        for (Map.Entry<Player, Long> entry : actualPlayingTimes.entrySet()) {
            log.trace("Player: " + entry.getKey() + " - Actual playing time: " + entry.getValue());
            if (entry.getValue() < time)
                player = entry.getKey();
        }

        log.debug("Fastest player: " + ((PlayerImpl)player).getName());
        return player;
    }


    /**
     * Determine if after a move the games finishes with a victory
     *
     * @param move the move that might determine a victory
     * @return True if the move determine a victory, False otherwise
     */
    @Override
    protected boolean isGameWon(Move move) {
        return ticTacToePlayground.isGameWon(move);
    }


    /**
     * Determine if after a move the games finishes with a draw
     *
     * @param move the move that might determine a draw
     * @return True if the move determine a draw, False otherwise
     */
    @Override
    protected boolean isGameDraw(Move move) {
        return ticTacToePlayground.isGameDraw(move);
    }
}
