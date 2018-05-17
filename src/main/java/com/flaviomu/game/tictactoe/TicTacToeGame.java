package com.flaviomu.game.tictactoe;

import com.flaviomu.game.generic.Game;
import com.flaviomu.game.generic.Move;
import com.flaviomu.game.generic.Player;
import com.flaviomu.game.generic.PlayerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToeGame extends Game {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private int number;
    private TicTacToePlayground ticTacToePlayground;
    private List<Player> players;

    public TicTacToeGame(int number, TicTacToePlayground ticTacToePlayground, List<Player> players) {
        this.number = number;
        this.ticTacToePlayground = ticTacToePlayground;
        this.players = players;
    }

    /**
     * The thread representing the generic
     *
     * @return The name of the
     */
    @Override
    public String call() {
        // set randomly the order of the players list
        definePlayersOrder();

        // set and start bonus move timer


        int nextPlayer = 0;
        while(true) {
            Player player = players.get(nextPlayer % players.size());

            log.info("Now " + ((PlayerImpl)player).getName() + " plays");
            TicTacToeMove move = ((TicTacToePlayer)player).doTicTacToeMove(ticTacToePlayground);
            //System.out.println();
            log.info("Game " + number + " -> TicTacToePlayground State:");
            ticTacToePlayground.printPlayground();
            //System.out.println();

            if (isGameWon(ticTacToePlayground, move))
                return "Game " + this.number + ": the winner is " + ((PlayerImpl) player).getName();
            else if (isGameDraw(ticTacToePlayground, move))
                return "Game " + this.number + ": it ended with a draw.";

            nextPlayer++;

            // TODO: add bonus move
        }
    }

    /**
     * Reorder the players list randomly
     *
     */
    private void definePlayersOrder() {
        List<Player> orderedPlayers = new ArrayList<>();
        Random random = new Random();

        while(! players.isEmpty()) {
            int index = random.nextInt(players.size());
            orderedPlayers.add(players.remove(index));
        }

        players = orderedPlayers;
    }

    /**
     * Check if the current state of the TicTacToe playground represents a victory
     *
     * @param playground The updated ticTacToePlayground after the last move executed
     * @param move The last move executed
     * @return true if the last move brought to a win, false otherwise
     */
     protected boolean isGameWon(TicTacToePlayground playground, Move move) {
        int row = ((TicTacToeMove) move).getRow();
        int column = ((TicTacToeMove) move).getColumn();
        String symbol = ((TicTacToeMove) move).getSymbol();

        // Check row
        boolean win = true;
        for (int c = 1; c <= playground.getPlaygroundSize(); c++) {
            if (! playground.getSymbolInCell(row, c).equals(symbol)) {
                win = false;
                break;
            }
        }
        if (win) return win;

        // Check column
        win = true;
        for (int r = 1; r <= playground.getPlaygroundSize(); r++) {
            if (! playground.getSymbolInCell(r, column).equals(symbol)) {
                win = false;
                break;
            }
        }
        if (win) return win;

        // Check diagonal
        if (isMoveOnDiagonal(playground, move)) {
            int halfPlaygroundSize = (int) Math.ceil((double) playground.getPlaygroundSize() / 2);
            if (column < halfPlaygroundSize)
                if (row < halfPlaygroundSize)
                    return checkDownDiagonal(playground, move);
                else
                    return checkUpDiagonal(playground, move);

            if (column > halfPlaygroundSize)
                if (row < halfPlaygroundSize)
                    return checkUpDiagonal(playground, move);
                else
                    return checkDownDiagonal(playground, move);

            // playgroundSize is odd and the cell is the central one
            return checkDownDiagonal(playground, move) || checkUpDiagonal(playground, move);
        }

        return false;
    }


    /*
        Verify if the move is in a diagonal of the playground
     */
    private boolean isMoveOnDiagonal(TicTacToePlayground playground, Move move) {
        int row = ((TicTacToeMove) move).getRow();
        int column = ((TicTacToeMove) move).getColumn();
        int playgroundSize = playground.getPlaygroundSize();

        if (row == column)
            return true;

        for (int i = 0; i < playgroundSize; i++) {
            if (row == (i + 1) && column == (playgroundSize - i))
                return true;
        }

        return false;
    }

    /*
        Verify if the move is in the diagonal which goes from cell [1:1] to cell [playgroundSize:playgroundSize]
     */
    private boolean checkDownDiagonal(TicTacToePlayground playground, Move move) {
        int playgroundSize = playground.getPlaygroundSize();
        String symbol = ((TicTacToeMove) move).getSymbol();

        for (int i = 0; i < playgroundSize; i++) {
            if (!playground.getSymbolInCell(i + 1, i + 1).equals(symbol))
                return false;
        }

        return true;
    }

    /*
        Verify if the move is in the diagonal which goes from cell [1:playgroundSize] to cell [playgroundSize:1]
     */
    private boolean checkUpDiagonal(TicTacToePlayground playground, Move move) {
        int playgroundSize = playground.getPlaygroundSize();
        String symbol = ((TicTacToeMove) move).getSymbol();

        for (int i = 0; i < playgroundSize; i++) {
            if (!playground.getSymbolInCell(i + 1, playgroundSize - i).equals(symbol))
                return false;
        }

        return true;
    }


    /**
     * Check if the current state of the TicTacToe playground represents a draw
     *
     * @param playground The updated ticTacToePlayground after the last move executed
     * @param move The last move executed
     * @return true if the last move brought to a win, false otherwise
     */
    protected boolean isGameDraw(TicTacToePlayground playground, Move move) {
        // The game is considered to finish with a draw only when there is no win and no free cells anymore
        //if (! isGameWon(playground, move))
        return ! playground.isAnyNewMovePossible();
    }

}
