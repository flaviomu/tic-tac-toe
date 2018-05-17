package com.flaviomu.game.tictactoe;

import com.flaviomu.game.generic.Player;

public interface TicTacToePlayer extends Player {

    TicTacToeMove doTicTacToeMove(TicTacToePlayground ticTacToePlayground);
}
