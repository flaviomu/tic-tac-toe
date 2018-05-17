package com.flaviomu.game.core;

import com.flaviomu.game.generic.PlayerImpl;
import com.flaviomu.game.tictactoe.TicTacToeMove;
import com.flaviomu.game.tictactoe.TicTacToePlayer;
import com.flaviomu.game.tictactoe.TicTacToePlayground;

import java.util.Random;

public class Computer extends PlayerImpl implements TicTacToePlayer {

    public Computer(String name, String symbol) {
        super(name, symbol);
    }

    @Override
    public TicTacToeMove doTicTacToeMove(TicTacToePlayground ticTacToePlayground) {
        Random random = new Random();
        TicTacToeMove move = new TicTacToeMove(-1, -1, this.getSymbol());

        int playgroundSize = ticTacToePlayground.getPlaygroundSize();

        while(! ticTacToePlayground.isValidMove(move)) {
            int row = random.nextInt(playgroundSize) + 1;
            int column = random.nextInt(playgroundSize) + 1;

            // TODO: improve move selection choosing random number only among free rows/columns
            move = new TicTacToeMove(row, column, this.getSymbol());
        }

        // execute the move
        ticTacToePlayground.updatePlayground(move);

        return move;
    }
}
