package com.flaviomu.games.generic;

import com.flaviomu.games.tictactoe.GameMode;

import java.util.concurrent.Callable;

/**
 * Provides a general implementation for the various games
 *
 */
public abstract class Game implements Callable<Game> {

    /**
     * Determines if the game ended with a victory or not
     *
     * @param move the last @{@link Move} executed
     * @return true if the game ended with a victory, false otherwise
     */
    protected abstract boolean isGameWon(Move move, GameMode gameMode);

    /**
     * Determines if the game ended with a draw or not
     *
     * @param move the last @{@link Move} executed
     * @return true if the game ended with a draw, false otherwise
     */
    protected abstract boolean isGameDraw(Move move);

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
