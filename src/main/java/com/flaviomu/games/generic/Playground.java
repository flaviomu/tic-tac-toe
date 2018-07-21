package com.flaviomu.games.generic;

/**
 * This class provides a general implementation for the various playgrounds
 *
 */
public abstract class Playground {

    /**
     * Determines if a @{@link Move} is valid
     *
     * @param move the @{@link Move} executed
     * @return true if the move is valid, false otherwise
     */
    public abstract boolean isValidMove(Move move);

    /**
     * Update the @{@link Playground} with the @{@link Move} executed
     *
     * @param move the @{@link Move} executed
     */
    public abstract void updatePlayground(Move move) throws IllegalArgumentException;

    /**
     * Print the status of the @{@link Playground}
     */
    public abstract void printPlayground();

    /**
     * Determines if there is the possibilities to execute any other new @{@link Move}
     * @return true if it is possible to execute a new move, false otherwise
     */
    public abstract boolean isAnyNewMovePossible();
}
