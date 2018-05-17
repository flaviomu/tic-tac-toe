package com.flaviomu.game.tictactoe;

import com.flaviomu.game.generic.Move;
import com.flaviomu.game.generic.Playground;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicTacToePlayground extends Playground {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private final String FREE_CELL_SYMBOL = "_";
    private int playgroundSize;
    private int playgroundArraySize;
    private String[] playground;


    public TicTacToePlayground(int playgroundSize) {
        this.playgroundSize = playgroundSize;
        playgroundArraySize = playgroundSize * playgroundSize;
        playground = new String[playgroundArraySize];
        for (int playgroundCellIndex = 0; playgroundCellIndex < playgroundArraySize; playgroundCellIndex++) {
            playground[playgroundCellIndex] = FREE_CELL_SYMBOL;
        }
    }

    public int getPlaygroundSize() {
        return playgroundSize;
    }

    /**
     * Verify that the move is valid (the cell is within the boundaries of the playground and is free)
     *
     * @param move the move to be validated
     * @return True if the move is valid, False otherwise
     */
    @Override
    public boolean isValidMove(Move move) {
        int row = ((TicTacToeMove) move).getRow();
        int column = ((TicTacToeMove) move).getColumn();
        int max = Math.max(row, column);
        int min = Math.min(row, column);

        if (max > playgroundSize || min < 1)
            return false;
        else {
            // Check if the selected cell is free
            int cellIndex = getCellIndex(row, column);
            return playground[cellIndex].equals(FREE_CELL_SYMBOL);
        }
    }

    /*
        Return the cell index to which a Move is related
     */
    private int getCellIndexOfMove(Move move) {
        int row = ((TicTacToeMove) move).getRow();
        int column = ((TicTacToeMove) move).getColumn();

        return getCellIndex(row, column);
    }

    /*
        Return the index of the array representing the playground starting from the row and column indexes of the
        playground matrix
     */
    private int getCellIndex(int row, int column) {
        // The row and column indexes inserted by the player are between 1 and playgroundSize
        return ((row - 1) * playgroundSize) + column - 1;
    }

    /**
     * Get the symbol existing in the cell index defined by the row and column indexes of the playground matrix
     *
     * @param row the row index of the playground matrix
     * @param column the column index of the playground matrix
     * @return the symbol contained in the cell
     */
    public String getSymbolInCell(int row, int column) {
        int cellIndex = getCellIndex(row, column);
        return playground[cellIndex];
    }


    /**
     * Verify if there is still at least one free cell
     *
     * @return True if there is one free cell, False otherwise
     */
    @Override
    public boolean isAnyNewMovePossible() {
        for (int index = 0; index < playgroundArraySize; index++)
            if (playground[index].equals(FREE_CELL_SYMBOL))
                return true;

        return false;
    }


    /**
     * Update the playground matrix executing the passed @TicTacToeMove move
     *
     * @param move the move to be executed
     * @throws IllegalArgumentException if the move passed is not valid
     */
    @Override
    public void updatePlayground(Move move) throws IllegalArgumentException {
        if (! this.isValidMove(move))
            throw new IllegalArgumentException("Invalid move: row/column indexes must be in the interval 1-" +
                                                playgroundSize + " and you must choose a free cell!");

        int cellIndex = getCellIndexOfMove(move);

        playground[cellIndex] = ((TicTacToeMove)move).getSymbol();
    }

    /**
     * Print the playground
     *
     */
    @Override
    public void printPlayground() {
        for (int index = 0; index < playgroundArraySize; index++) {
            System.out.print("\t" + playground[index]);
            if ((index % playgroundSize) == (playgroundSize - 1) )
                System.out.println();
        }
        System.out.println();
    }
}
