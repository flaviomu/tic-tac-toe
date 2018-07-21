package com.flaviomu.games.tictactoe;

import com.flaviomu.games.generic.Move;
import com.flaviomu.games.generic.Playground;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Provides an implementation of the TicTacToe playground
 */
public class TicTacToePlayground extends Playground {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private final String FREE_CELL_SYMBOL = "_";
    private int playgroundSize;
    private int playgroundArraySize;
    private String[] playground;

    /**
     * Creates a new TicTacToe playground
     *
     * @param playgroundSize the playground size
     */
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
     * Verifies that the move is valid (the cell is within the boundaries of the playground and is free)
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
        Returns the cell index to which a Move is related
     */
    private int getCellIndexOfMove(Move move) {
        int row = ((TicTacToeMove) move).getRow();
        int column = ((TicTacToeMove) move).getColumn();

        return getCellIndex(row, column);
    }

    /*
        Returns the index of the array representing the playground starting from the row and column indexes of the
        playground matrix
     */
    private int getCellIndex(int row, int column) {
        // The row and column indexes inserted by the player are between 1 and playgroundSize
        return ((row - 1) * playgroundSize) + column - 1;
    }

    /**
     * Gets the symbol existing in the cell index defined by the row and column indexes of the playground matrix
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
     * Verifies if there is still at least one free cell
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
     * Updates the playground matrix executing the passed @TicTacToeMove move
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
     * Prints the playground
     *
     */
    @Override
    public void printPlayground() {
        for (int index = 0; index < playgroundArraySize; index++) {
            System.out.print("\t" + playground[index]);
            if ((index % playgroundSize) == (playgroundSize - 1) )
                System.out.println();
        }
        System.out.println("\n");
    }

    /**
     * Cleans the state of the playground bringing it to an initial state
     */
    public void cleanPlayground() {
        for (int playgroundCellIndex = 0; playgroundCellIndex < playgroundArraySize; playgroundCellIndex++)
            playground[playgroundCellIndex] = FREE_CELL_SYMBOL;
    }



    /**
     * Checks if the current state of the playground represents a victory
     *
     * @param move the last @{@link Move} move executed
     * @return true if the last move caused a victory, false otherwise
     */
    public boolean isGameWon(Move move) {
        int row = ((TicTacToeMove) move).getRow();
        int column = ((TicTacToeMove) move).getColumn();
        String symbol = ((TicTacToeMove) move).getSymbol();

        // Checks row
        boolean win = true;
        for (int c = 1; c <= this.getPlaygroundSize(); c++) {
            if (! this.getSymbolInCell(row, c).equals(symbol)) {
                win = false;
                break;
            }
        }
        if (win) return win;

        // Checks column
        win = true;
        for (int r = 1; r <= this.getPlaygroundSize(); r++) {
            if (! this.getSymbolInCell(r, column).equals(symbol)) {
                win = false;
                break;
            }
        }
        if (win) return win;

        // Checks diagonal
        if (isMoveOnDiagonal(move)) {
            int halfPlaygroundSize = (int) Math.ceil((double) this.getPlaygroundSize() / 2);
            if (column < halfPlaygroundSize)
                if (row < halfPlaygroundSize)
                    return checkDownDiagonal(move);
                else
                    return checkUpDiagonal(move);

            if (column > halfPlaygroundSize)
                if (row < halfPlaygroundSize)
                    return checkUpDiagonal(move);
                else
                    return checkDownDiagonal(move);

            // playgroundSize is odd and the cell is the central one
            return checkDownDiagonal(move) || checkUpDiagonal(move);
        }

        return false;
    }


    /*
        Verifies if the move is in a diagonal of the playground
     */
    private boolean isMoveOnDiagonal(Move move) {
        int row = ((TicTacToeMove) move).getRow();
        int column = ((TicTacToeMove) move).getColumn();
        int playgroundSize = this.getPlaygroundSize();

        if (row == column)
            return true;

        for (int i = 0; i < playgroundSize; i++) {
            if (row == (i + 1) && column == (playgroundSize - i))
                return true;
        }

        return false;
    }

    /*
        Verifies if the move is in the diagonal which goes from cell [1:1] to cell [playgroundSize:playgroundSize]
     */
    private boolean checkDownDiagonal(Move move) {
        int playgroundSize = this.getPlaygroundSize();
        String symbol = ((TicTacToeMove) move).getSymbol();

        for (int i = 0; i < playgroundSize; i++) {
            if (! this.getSymbolInCell(i + 1, i + 1).equals(symbol))
                return false;
        }

        return true;
    }

    /*
        Verifies if the move is in the diagonal which goes from cell [1:playgroundSize] to cell [playgroundSize:1]
     */
    private boolean checkUpDiagonal(Move move) {
        int playgroundSize = this.getPlaygroundSize();
        String symbol = ((TicTacToeMove) move).getSymbol();

        for (int i = 0; i < playgroundSize; i++) {
            if (! this.getSymbolInCell(i + 1, playgroundSize - i).equals(symbol))
                return false;
        }

        return true;
    }


    /**
     * Checks if the current state of the playground represents a draw
     *
     * @param move the last @{@link Move} move executed
     * @return true if the last move caused a draw, false otherwise
     */
    public boolean isGameDraw(Move move) {
        // The games is considered to finish with a draw only when there is no win and no free cells anymore
        //if (! isGameWon(playground, move))
        return ! this.isAnyNewMovePossible();
    }

}
