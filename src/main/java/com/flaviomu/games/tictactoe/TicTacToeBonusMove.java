package com.flaviomu.games.tictactoe;

import com.flaviomu.games.generic.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Callable;

public class TicTacToeBonusMove implements Callable<String> {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private int gameNumber;
    private TicTacToePlayground ticTacToePlayground;
    private Map<Player, Integer> actualPlayingTimes;

    public TicTacToeBonusMove(int gameNumber, TicTacToePlayground ticTacToePlayground, Map<Player, Integer> actualPlayingTimes) {
        this.gameNumber = gameNumber;
        this.ticTacToePlayground = ticTacToePlayground;
        this.actualPlayingTimes = actualPlayingTimes;
    }

    @Override
    public String call() {
//        Player player = actualPlayingTimes.keySet().iterator().next();
//        Integer time = actualPlayingTimes.get(player);
//        for (Map.Entry<Player, Integer> entry : actualPlayingTimes.entrySet()) {
//            if (entry.getValue() < time)
//                player = entry.getKey();
//        }
//
//        log.info(" **** BONUS MOVE **** Now " + ((PlayerImpl)player).getName() + " plays");
//
//        TicTacToeMove move = ((TicTacToePlayer)player).doTicTacToeMove(ticTacToePlayground);
//
//        log.info("\nGame " + gameNumber + " -> TicTacToePlayground State:");
//        ticTacToePlayground.printPlayground();
//
//        if (ticTacToePlayground.isGameWon(move)) {
//            return "Game " + this.gameNumber + ": the winner is " + ((PlayerImpl) player).getName();
//        }
//        else if (ticTacToePlayground.isGameDraw(move)) {
//            return "Game " + this.gameNumber + ": it ended with a draw.";
//        }
//        else {
//            // Set and start next bonus move timer
////            Random bonusMoveDelayGenerator = new Random();
////            long bonusMoveDelay = (long) bonusMoveDelayGenerator.nextInt(10) + 10;
////
////            ScheduledExecutorService bonusMoveScheduler = new ScheduledThreadPoolExecutor(1);
////
////            bonusMoveScheduler.schedule(new TicTacToeBonusMove(gameNumber, ticTacToePlayground, actualPlayingTimes), bonusMoveDelay, TimeUnit.SECONDS);
//
//        }

        return null;
    }
}
