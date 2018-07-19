package com.flaviomu.games.tictactoe;

import com.flaviomu.games.generic.Player;

public interface TicTacToePlayer extends Player {

    TicTacToeMove doTicTacToeMove(TicTacToePlayground ticTacToePlayground) throws InterruptedException;
}
