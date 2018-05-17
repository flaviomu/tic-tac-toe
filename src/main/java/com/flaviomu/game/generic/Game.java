package com.flaviomu.game.generic;

import com.flaviomu.game.tictactoe.TicTacToePlayground;

import java.util.concurrent.Callable;


public abstract class Game implements Callable<String> {

    protected abstract boolean isGameWon(TicTacToePlayground playground, Move move);

    protected abstract boolean isGameDraw(TicTacToePlayground playground, Move move);

}
