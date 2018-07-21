package com.flaviomu.games.generic;

/**
 * Provides a general implementation for the various players
 *
 */
public abstract class PlayerImpl implements Player {

    private String name;
    private String symbol;

    /**
     * Creates a general game player
     *
     * @param name the name of the player
     * @param symbol the symbol associated to the player
     */
    public PlayerImpl(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
