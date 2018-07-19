package com.flaviomu.games.tictactoe;

import com.flaviomu.games.config.TicTacToeConfiguration;
import com.flaviomu.games.core.Computer;
import com.flaviomu.games.core.Human;
import com.flaviomu.games.generic.Player;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TicTacToeGameTest {

    private int playgroundSize = TicTacToeConfiguration.getPlaygroundSizeDefault();
    private TicTacToePlayground playground;
    private TicTacToeGame game;
    private List<Player> players;

    @BeforeAll
    void setUpAll() {
        players = new ArrayList<>();
        players.add(new Computer("Computer", TicTacToeConfiguration.getComputerSymbolDefault()));
        players.add(new Human("Player1", TicTacToeConfiguration.getPlayer1SymbolDefault()));
        players.add(new Human("Player2", TicTacToeConfiguration.getPlayer2SymbolDefault()));
    }

    @BeforeEach
    void setUp() {
        playground = new TicTacToePlayground(playgroundSize);
        game = new TicTacToeGame(1, playground, players);
    }

    @Test
    void isGameWonOnRow() {
        int row = 1;
        TicTacToeMove move = null;
        for (int column = 0; column < playground.getPlaygroundSize(); column++) {
            move = new TicTacToeMove(row, column + 1,
                    TicTacToeConfiguration.getComputerSymbolDefault());
            playground.updatePlayground(move);
        }

        assertTrue(game.isGameWon(move));
    }

    @Test
    void isGameWonOnColumn() {
        int column = 1;
        TicTacToeMove move = null;
        for (int row = 0; row < playground.getPlaygroundSize(); row++) {
            move = new TicTacToeMove(row + 1, column,
                    TicTacToeConfiguration.getPlayer1SymbolDefault());
            playground.updatePlayground(move);
        }

        assertTrue(game.isGameWon(move));
    }

    @Test
    void isGameWonOnDiagonal() {
        TicTacToeMove move = null;
        for (int index = 0; index < playground.getPlaygroundSize(); index++) {
            move = new TicTacToeMove(index + 1, index + 1,
                    TicTacToeConfiguration.getPlayer2SymbolDefault());
            playground.updatePlayground(move);
        }

        assertTrue(game.isGameWon(move));
    }

    @Test
    void isGameDraw() {
        TicTacToeMove move = null;
        int symbol = 0;
        for (int row = 0; row < playground.getPlaygroundSize(); row++) {
            for (int column = 0; column < playground.getPlaygroundSize(); column++) {
                move = new TicTacToeMove(row + 1, column + 1, Integer.toString(symbol));
                playground.updatePlayground(move);
                symbol++;
            }
        }

        playground.printPlayground();
        assertTrue(game.isGameDraw(move));
    }
}