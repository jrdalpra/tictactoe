package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Point.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeGameControllerTest {

    private TicTacToeGameController controller;

    @Before
    public void setup() {
        controller = new TicTacToeGameController();
    }

    @Test
    public void aYoungControllerObject_MustHaveNoStaredGame() {
        assertFalse(controller.theGameHasStarted());
    }

    @Test
    public void whenStartsAGame_MustBeTrueThatTheGameHasStared() {
        controller.startsTheGame();
        assertTrue(controller.theGameHasStarted());
    }

    @Test
    public void afterAWinnerGame_MustBeTrueThatTheGameHasEnded() {
        makeAWinnerGame();
        assertFalse(controller.theGameHasStarted());
        assertTrue(controller.theGameHasEnded());
    }

    @Test
    public void afterAWinnerGame_MustReturnTheWinnerPoints() {
        makeAWinnerGame();
        assertNotNull(controller.getWinnerPoints());
    }

    @Test
    public void aYoungGameController_MustReturnANullLastMark() {
        assertNull(controller.getLastMark());
    }

    @Test
    public void aYoungGameController_MustReturnANotNullLastMark() {
        controller.startsTheGame();
        assertNotNull(controller.getLastMark());
    }

    @Test
    public void afterPutTheNext_MustHaveANonBlankMark() {
        controller.startsTheGame();
        controller.putTheNextMarkAt(TOP_CENTER);
        assertNotEquals(Mark.BLANK, controller.getLastMark());
    }

    private void makeAWinnerGame() {
        controller.startsTheGame();
        controller.putTheNextMarkAt(TOP_LEFT);
        controller.putTheNextMarkAt(BOTTOM_CENTER);
        controller.putTheNextMarkAt(TOP_CENTER);
        controller.putTheNextMarkAt(BOTTOM_LEFT);
        controller.putTheNextMarkAt(TOP_RIGHT);
    }

    @Test
    public void afterADrawGame_MustAssertThatIsTrue() {
        makeADrawGame();
        assertTrue(controller.theGameHasDrawn());
    }

    @Test
    public void afterAMove_MustReturnTheMarkAtPosition() {
        controller.startsTheGame();
        controller.putTheNextMarkAt(MIDDLE_CENTER);
        assertNotNull(controller.getMarkAt(MIDDLE_CENTER));
        assertNotEquals(Mark.BLANK, controller.getMarkAt(MIDDLE_CENTER));
    }

    @Test
    public void afterStopsTheGame_MustHaveNoGameStared() {
        controller.startsTheGame();
        controller.stopTheGame();
        assertFalse(controller.theGameHasStarted());
    }

    private void makeADrawGame() {
        controller.startsTheGame();
        controller.putTheNextMarkAt(TOP_LEFT);
        controller.putTheNextMarkAt(BOTTOM_LEFT);
        controller.putTheNextMarkAt(TOP_RIGHT);
        controller.putTheNextMarkAt(BOTTOM_RIGHT);
        controller.putTheNextMarkAt(BOTTOM_CENTER);
        controller.putTheNextMarkAt(TOP_CENTER);
        controller.putTheNextMarkAt(MIDDLE_LEFT);
        controller.putTheNextMarkAt(MIDDLE_CENTER);
        controller.putTheNextMarkAt(MIDDLE_RIGHT);
    }

    @After
    public void teardown() {
    }

}
