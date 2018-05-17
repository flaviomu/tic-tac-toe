package com.flaviomu.game.core;

import com.flaviomu.game.generic.PlayerImpl;
import com.flaviomu.game.tictactoe.TicTacToeMove;
import com.flaviomu.game.tictactoe.TicTacToePlayer;
import com.flaviomu.game.tictactoe.TicTacToePlayground;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Human extends PlayerImpl implements TicTacToePlayer {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private static final Scanner scanner = new Scanner(System.in);

    public Human(String name, String symbol) {
        super(name, symbol);
    }

    @Override
    public TicTacToeMove doTicTacToeMove(TicTacToePlayground ticTacToePlayground) {
        TicTacToeMove move = new TicTacToeMove(-1, -1, this.getSymbol());

        while(true) {
            System.out.print("\tInsert row index (valid values are in range: 1-" + ticTacToePlayground.getPlaygroundSize() + "): ");
            int row = scanner.nextInt();
            System.out.print("\tInsert column index (valid values are in range: 1-" + ticTacToePlayground.getPlaygroundSize() + "): ");
            int column = scanner.nextInt();

            move = new TicTacToeMove(row, column, this.getSymbol());

            if (ticTacToePlayground.isValidMove(move))
                break;
            else
                System.out.println("\n\tMove not valid. Please, insert a new move.\n");
        }

        // execute the move
        ticTacToePlayground.updatePlayground(move);

        return move;
    }

//    private TicTacToeMove getTicTacToeMove() {
//        System.out.print("\tInsert row index (valid values are in range: 1-" + ticTacToePlayground.getPlaygroundSize() + "): ");
//        int row = scanner.nextInt();
//        System.out.print("\tInsert column index (valid values are in range: 1-" + ticTacToePlayground.getPlaygroundSize() + "): ");
//        int column = scanner.nextInt();
//
//        return new TicTacToeMove(row, column, this.getSymbol());
//    }
}
