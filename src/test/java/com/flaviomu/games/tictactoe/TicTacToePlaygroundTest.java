package com.flaviomu.games.tictactoe;

import org.junit.jupiter.api.*;
import java.util.function.Consumer;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Defines the test for the @{@link TicTacToePlayground} class
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TicTacToePlaygroundTest {

    private int playgroundSize = TicTacToeConfiguration.getPlaygroundSizeDefault();
    private TicTacToePlayground playground;

    /**
     * Sets up the @{@link TicTacToePlayground} playground
     *
     */
    @BeforeEach
    void setUp() {
        playground = new TicTacToePlayground(playgroundSize);
    }


    /**
     * Tests the execution of a valid @{@link TicTacToeMove} move
     */
    @Test
    void testValidMove() {
        int row = playgroundSize - 1;
        int column = playgroundSize - 1;
        String symbol = "ST";
        TicTacToeMove move = new TicTacToeMove(row, column, symbol);

        assertTrue(playground.isValidMove(move));
    }


    /**
     * Tests the execution of a not valid @{@link TicTacToeMove} move
     */
    @Test
    void testNotValidRowForMove() {
        int row = -1;
        int column = playgroundSize - 1;
        String symbol = "ST";
        TicTacToeMove move = new TicTacToeMove(row, column, symbol);

        assertFalse(playground.isValidMove(move));
    }


    /**
     * Tests the execution of a not valid @{@link TicTacToeMove} move
     */
    @Test
    void testNotValidColumnsForMove() {
        int row = playgroundSize - 1;
        int column = -1;
        String symbol = "ST";
        TicTacToeMove move = new TicTacToeMove(row, column, symbol);

        assertFalse(playground.isValidMove(move));
    }


    /**
     * Tests the execution of a not valid @{@link TicTacToeMove} move
     */
    @Test
    void testNotFreeCellForMove() {
        int row = 1;
        int column = 1;
        String symbol = "ST";
        TicTacToeMove move = new TicTacToeMove(row, column, symbol);

        /*Consumer<TicTacToeMove> consumer = new Consumer<TicTacToeMove>() {
            public void accept(TicTacToeMove m) {
                playground.updatePlayground(m);
            }
        };*/
        Consumer<TicTacToeMove> consumer = (m) -> playground.updatePlayground(m);

        assertDoesNotThrow(() -> consumer.accept(move));
        // The same move is not allowed
        assertThrows(IllegalArgumentException.class, () -> consumer.accept(move));
    }


    /**
     * Tests the possibility to execute a new valid @{@link TicTacToeMove} move
     */
    @Test
    void testIsAnyNewMovePossible() {
        for (int row = 0; row < playground.getPlaygroundSize(); row++) {
            for (int column = 0; column < playground.getPlaygroundSize(); column++) {
                assertTrue(playground.isAnyNewMovePossible());
                TicTacToeMove move = new TicTacToeMove(row + 1, column + 1,
                                                        TicTacToeConfiguration.getComputerSymbolDefault());
                playground.updatePlayground(move);
            }
        }
        assertFalse(playground.isAnyNewMovePossible());
    }


    /**
     * Tests the update of the @{@link TicTacToePlayground} playground with a new valid @{@link TicTacToeMove} move
     */
    @Test
    void testUpdatePlayground() {
        int row = 1;
        int column = 1;
        String symbol = "ST";
        TicTacToeMove move = new TicTacToeMove(row, column, symbol);
        playground.updatePlayground(move);

        assertEquals(playground.getSymbolInCell(move.getRow(), move.getColumn()), move.getSymbol());
    }
}