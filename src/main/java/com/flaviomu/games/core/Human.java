package com.flaviomu.games.core;

import com.flaviomu.games.generic.PlayerImpl;
import com.flaviomu.games.tictactoe.TicTacToeMove;
import com.flaviomu.games.tictactoe.TicTacToePlayer;
import com.flaviomu.games.tictactoe.TicTacToePlayground;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Defines a human kind of @{@link TicTacToePlayer} player.
 * It extends @{@link PlayerImpl}.
 *
 */
public class Human extends PlayerImpl implements TicTacToePlayer {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Creates a new human player.
     *
     * @param name the name of the player
     * @param symbol the symbol associated to the player
     */
    public Human(String name, String symbol) {
        super(name, symbol);
    }

    /**
     * Defines the logic for a @{@link TicTacToeMove} move executed by the @{@link Human} player
     *
     * @param ticTacToePlayground the @{@link TicTacToePlayground} where to execute the @{@link TicTacToeMove} move
     * @return the @TicTacToeMove executed by the @{@link Human} player
     * @throws @{@link InterruptedException}
     */
    @Override
    public TicTacToeMove doTicTacToeMove(TicTacToePlayground ticTacToePlayground) {
        TicTacToeMove move;

        while(true) {
            move = getTicTacToeMove(ticTacToePlayground);

            if (ticTacToePlayground.isValidMove(move))
                break;
            else
                System.out.println("\n\tMove not valid. Please, insert a new move.\n");
        }

        // Executes the move
        ticTacToePlayground.updatePlayground(move);

        return move;
    }

    /*
        Reads the human move from the standard input
     */
    private TicTacToeMove getTicTacToeMove(TicTacToePlayground ticTacToePlayground) {
        System.out.print("\tInsert row and column indexes separated by a comma (valid values are in range: 1-" + ticTacToePlayground.getPlaygroundSize() + ") (e.g. row,column): ");
        String input = scanner.nextLine();
        String[] indexes = input.split(",");
        int row = Integer.parseInt(indexes[0].trim());
        int column = Integer.parseInt(indexes[1].trim());

        return new TicTacToeMove(row, column, this.getSymbol());
    }
}
