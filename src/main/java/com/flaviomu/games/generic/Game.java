package com.flaviomu.games.generic;

import java.util.concurrent.Callable;


public abstract class Game implements Callable<Game> {

    protected abstract boolean isGameWon(Move move);

    protected abstract boolean isGameDraw(Move move);

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
