package com.flaviomu.games.tictactoe;

import com.flaviomu.games.generic.Player;

/**
 * Defines the player interface for the TicTacToe players
 *
 */
public interface TicTacToePlayer extends Player {

    /**
     * Executes the @{@link TicTacToeMove} move
     *
     * @param ticTacToePlayground the @{@link TicTacToePlayground} playground where to execute the move
     * @return the @{@link TicTacToeMove} executed
     * @throws InterruptedException
     */
    TicTacToeMove doTicTacToeMove(TicTacToePlayground ticTacToePlayground) throws InterruptedException;
}
