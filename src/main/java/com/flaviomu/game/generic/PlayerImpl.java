package com.flaviomu.game.generic;

public abstract class PlayerImpl implements Player {

    private String name;
    private String symbol;

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
