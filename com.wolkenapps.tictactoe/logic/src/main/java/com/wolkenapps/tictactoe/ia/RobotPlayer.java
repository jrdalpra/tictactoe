package com.wolkenapps.tictactoe.ia;

import com.wolkenapps.tictactoe.Mark;
import com.wolkenapps.tictactoe.Point;
import com.wolkenapps.tictactoe.TicTacToeGameController;

public class RobotPlayer {

    @SuppressWarnings("serial")
    public class HasNoPutters extends RuntimeException {
    }

    @SuppressWarnings("serial")
    public class CannotPutAMark extends RuntimeException {
    }

    private Mark                    markItIsUsing;

    private TicTacToeGameController controller;

    private Iterable<MarkPutter>    putters;

    private Point                   lastPoint;

    public RobotPlayer(TicTacToeGameController controller,
                       Iterable<MarkPutter> putters) {
        this.controller = controller;
        this.putters = putters;
    }

    public Mark getMarkItIsUsing() {
        return markItIsUsing;
    }

    public void putSomeMarkInTheGame() {
        if (!hasFinders())
            throw new RobotPlayer.HasNoPutters();
        tryToPutAMark();
        keepTheLastMark();
    }

    private void keepTheLastMark() {
        markItIsUsing = controller.getLastMark();
    }

    private void tryToPutAMark() {
        for (MarkPutter putter : putters) {
            try {
                putter.putAMarkUsing(controller);
                lastPoint = putter.getThePointUsed();
                return;
            } catch (Exception e) {
            }
        }
        throw new RobotPlayer.CannotPutAMark();
    }

    private boolean hasFinders() {
        return putters != null && putters.iterator().hasNext();
    }


    public Point getLastPoint() {
        return lastPoint;
    }
}
