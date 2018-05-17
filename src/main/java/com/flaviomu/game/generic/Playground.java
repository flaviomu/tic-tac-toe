package com.flaviomu.game.generic;

public abstract class Playground {

    public abstract boolean isValidMove(Move move);

    public abstract void updatePlayground(Move move) throws IllegalArgumentException;

    //public abstract void printPlayground();

    public abstract void printPlayground();

    public abstract boolean isAnyNewMovePossible();
}
