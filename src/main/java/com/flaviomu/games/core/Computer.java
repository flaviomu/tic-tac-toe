package com.flaviomu.games.core;

import com.flaviomu.games.generic.PlayerImpl;
import com.flaviomu.games.tictactoe.TicTacToeMove;
import com.flaviomu.games.tictactoe.TicTacToePlayer;
import com.flaviomu.games.tictactoe.TicTacToePlayground;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Defines a computer kind of @{@link TicTacToePlayer} player.
 * Extends @{@link PlayerImpl}.
 *
 */
public class Computer extends PlayerImpl implements TicTacToePlayer {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private final int minThinknigTimeInSeconds = 5;

    /**
     * Creates a new computer player.
     *
     * @param name the name of the player
     * @param symbol the symbol associated to the player
     */
    public Computer(String name, String symbol) {
        super(name, symbol);
    }

    /**
     * Defines the logic for a @{@link TicTacToeMove} move executed by the @{@link Computer} player
     *
     * @param ticTacToePlayground the @{@link TicTacToePlayground} where to execute the @{@link TicTacToeMove} move
     * @return the @TicTacToeMove executed by the @{@link Computer} player
     * @throws @{@link InterruptedException}
     */
    @Override
    public TicTacToeMove doTicTacToeMove(TicTacToePlayground ticTacToePlayground) throws InterruptedException {
        Random random = new Random();
        TicTacToeMove move = new TicTacToeMove(-1, -1, this.getSymbol());

        int playgroundSize = ticTacToePlayground.getPlaygroundSize();

        int moveAttempt = 0;
        while(! ticTacToePlayground.isValidMove(move)) {
            // TODO improve computer moves choosing the indexes only among the free rows and columns
            moveAttempt++;
            int row = random.nextInt(playgroundSize) + 1;
            int column = random.nextInt(playgroundSize) + 1;

            // Simulates a thinking time between 5 and "5 + maxThinkingTimeInSeconds - 1" seconds
            int thinkingTime = random.nextInt(5) + minThinknigTimeInSeconds;
            log.debug("Thinking about a valid move for " + thinkingTime + " seconds (attempt " + moveAttempt + ")");
            Thread.sleep((long) (thinkingTime * 1000));

            move = new TicTacToeMove(row, column, this.getSymbol());
        }

        // Executes the move
        ticTacToePlayground.updatePlayground(move);

        return move;
    }
}
