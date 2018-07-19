package com.flaviomu.games.tictactoe;

import com.flaviomu.games.generic.Move;

public class TicTacToeMove extends Move {

    private int row;
    private int column;
    private String symbol;

    /**
     * Basic constructor of TicTacToe Move
     *
     * @param row The TicTacToe playground row index associated to the move
     * @param column The TicTacToe playground columns index associated to the move
     * @param symbol The Player symbol associated to the move
     */
    public TicTacToeMove(int row, int column, String symbol) {
        this.row = row;
        this.column = column;
        this.symbol = symbol;
    }

    TicTacToeMove(int cell, String symbol) {

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getSymbol() {
        return symbol;
    }
}
