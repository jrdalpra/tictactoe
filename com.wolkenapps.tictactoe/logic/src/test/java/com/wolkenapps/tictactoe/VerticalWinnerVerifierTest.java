package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Mark.CROSS;
import static com.wolkenapps.tictactoe.Mark.NOUGHT;
import static com.wolkenapps.tictactoe.Point.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.wolkenapps.tictactoe.winnerverifiers.VerticalWinnerVerifier;

public class VerticalWinnerVerifierTest {

   private VerticalWinnerVerifier verifier;

   @Before
   public void setup() {
      verifier = new VerticalWinnerVerifier();
   }

   @Test
   public void afterPutSomeMarks_MustFindAWinnerAtLeftColumn() {
      assertTrue(verifier.confirmsThatThereIsWinner(aGameThatHasAWinnerAtLeftColumn()));
   }

   @Test
   public void afterPutSomeMarks_MustFindTheWinnerPointsAtLeftColumn() {
      assertEquals(new WinnerPoints(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT),
                   verifier.getWinnerPointsAt(aGameThatHasAWinnerAtLeftColumn()));
   }

   private TicTacToeGame aGameThatHasAWinnerAtLeftColumn() {
      TicTacToeGame game = aSimpleGame();
      game.putAMarkAt(TOP_LEFT, CROSS);
      game.putAMarkAt(TOP_CENTER, NOUGHT);
      game.putAMarkAt(MIDDLE_LEFT, CROSS);
      game.putAMarkAt(MIDDLE_CENTER, NOUGHT);
      game.putAMarkAt(BOTTOM_LEFT, CROSS);
      return game;
   }

   @Test
   public void afterPutSomeMarks_MustFindAWinnerAtCenterColumn() {
      assertTrue(verifier.confirmsThatThereIsWinner(aGameThatHasAWinnerAtCenterColumn()));
   }

   @Test
   public void afterPutSomeMarks_MustFindTheWinnerPointsAtSecontColumn() {
      assertEquals(new WinnerPoints(TOP_CENTER, MIDDLE_CENTER, BOTTOM_CENTER), verifier.getWinnerPointsAt(aGameThatHasAWinnerAtCenterColumn()));
   }

   private TicTacToeGame aGameThatHasAWinnerAtCenterColumn() {
      TicTacToeGame game = aSimpleGame();
      game.putAMarkAt(TOP_CENTER, CROSS);
      game.putAMarkAt(TOP_LEFT, NOUGHT);
      game.putAMarkAt(MIDDLE_CENTER, CROSS);
      game.putAMarkAt(MIDDLE_LEFT, NOUGHT);
      game.putAMarkAt(BOTTOM_CENTER, CROSS);
      return game;
   }

   private TicTacToeGame aSimpleGame() {
      return new TicTacToeGame(Arrays.<WinnerVerifier> asList(verifier));
   }

   @Test
   public void afterPutSomeMarks_MustFindAWinnerAtRightColumn() {
      assertTrue(verifier.confirmsThatThereIsWinner(aGameThatHasAWinnerAtRightColumn()));
   }

   @Test
   public void afterPutSomeMarks_MustFindTheWinnerPointsAtRightColumn() {
      assertEquals(new WinnerPoints(TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT), verifier.getWinnerPointsAt(aGameThatHasAWinnerAtRightColumn()));
   }

   private TicTacToeGame aGameThatHasAWinnerAtRightColumn() {
      TicTacToeGame game = aSimpleGame();
      game.putAMarkAt(TOP_RIGHT, CROSS);
      game.putAMarkAt(TOP_LEFT, NOUGHT);
      game.putAMarkAt(MIDDLE_RIGHT, CROSS);
      game.putAMarkAt(MIDDLE_LEFT, NOUGHT);
      game.putAMarkAt(BOTTOM_RIGHT, CROSS);
      return game;
   }

}
