package com.wolkenapps.tictactoe.ia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.wolkenapps.tictactoe.TicTacToeGameController;
import com.wolkenapps.tictactoe.ia.putters.MiddleCenterMarkPutter;

public class RobotPlayerTest {

    private RobotPlayer             robot;

    private TicTacToeGameController controller;

    @Before
    public void setup() {
        robot = new RobotPlayer(controller = new TicTacToeGameController(), allPossiblePutters());
    }

    private Iterable<MarkPutter> allPossiblePutters() {
        return Arrays.<MarkPutter> asList(new MiddleCenterMarkPutter());
    }

    @Test(expected = RobotPlayer.HasNoPutters.class)
    public void whenTryToMarkWithANoPuttersRobot_MustThrowAnException() {
        robot = new RobotPlayer(controller, null);
        controller.startsTheGame();
        robot.putSomeMarkInTheGame();
    }

    @Test
    public void afterPutAMark_RobotMustKnowWhatIsHisMark() {
        controller.startsTheGame();
        robot.putSomeMarkInTheGame();
        assertNotNull(robot.getMarkItIsUsing());
    }

    @Test
    public void rightAfterPutAMark_TheLastMarkInController_MustBeTheSameMarkThatRobotIsUsing() {
        controller.startsTheGame();
        robot.putSomeMarkInTheGame();
        assertNotNull(robot.getMarkItIsUsing());
        assertEquals(controller.getLastMark(), robot.getMarkItIsUsing());
    }

}
