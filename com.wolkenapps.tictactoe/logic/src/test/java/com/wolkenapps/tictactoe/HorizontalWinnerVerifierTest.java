package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Mark.CROSS;
import static com.wolkenapps.tictactoe.Mark.NOUGHT;
import static com.wolkenapps.tictactoe.Point.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.wolkenapps.tictactoe.winnerverifiers.HorizontalWinnerVerificer;

public class HorizontalWinnerVerifierTest {

   private HorizontalWinnerVerificer verifier;

   @Before
   public void setup() {
      verifier = new HorizontalWinnerVerificer();
   }

   @Test
   public void afterSomeMoves_MustFindAWinnerAtTopRow() {
      assertTrue(verifier.confirmsThatThereIsWinner(aGameThatHasAWinnerAtTopRow()));
   }

   @Test
   public void afterSomeMoves_MustFindTheWinnerPointsAtTopRow() {
      assertEquals(new WinnerPoints(TOP_LEFT, TOP_CENTER, TOP_RIGHT), verifier.getWinnerPointsAt(aGameThatHasAWinnerAtTopRow()));
   }

   private TicTacToeGame aGameThatHasAWinnerAtTopRow() {
      TicTacToeGame game = new TicTacToeGame(Arrays.<WinnerVerifier> asList(verifier));
      game.putAMarkAt(TOP_LEFT, CROSS);
      game.putAMarkAt(MIDDLE_LEFT, NOUGHT);
      game.putAMarkAt(TOP_CENTER, CROSS);
      game.putAMarkAt(MIDDLE_RIGHT, NOUGHT);
      game.putAMarkAt(TOP_RIGHT, CROSS);
      return game;
   }

   @Test
   public void afterSomeMoves_MustFindAWinnerAtMiddleRow() {
      assertTrue(verifier.confirmsThatThereIsWinner(aGameThatHasAWinnerAtMiddleRow()));
   }

   @Test
   public void afterSomeMoves_MustFindTheWinnerPointsAtMiddleRow() {
      assertEquals(new WinnerPoints(MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT), verifier.getWinnerPointsAt(aGameThatHasAWinnerAtMiddleRow()));
   }

   private TicTacToeGame aGameThatHasAWinnerAtMiddleRow() {
      TicTacToeGame game = new TicTacToeGame(Arrays.<WinnerVerifier> asList(verifier));
      game.putAMarkAt(MIDDLE_LEFT, CROSS);
      game.putAMarkAt(TOP_LEFT, NOUGHT);
      game.putAMarkAt(MIDDLE_CENTER, CROSS);
      game.putAMarkAt(TOP_RIGHT, NOUGHT);
      game.putAMarkAt(MIDDLE_RIGHT, CROSS);
      return game;
   }

   @Test
   public void afterSomeMoves_MustFindAWinnerAtBottomRow() {
      assertTrue(verifier.confirmsThatThereIsWinner(aGameThatHasAWinnerAtBottomRow()));
   }

   @Test
   public void afterSomeMoves_MustFindTheWinnerPointsAtBottomRow() {
      assertEquals(new WinnerPoints(BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT), verifier.getWinnerPointsAt(aGameThatHasAWinnerAtBottomRow()));
   }

   private TicTacToeGame aGameThatHasAWinnerAtBottomRow() {
      TicTacToeGame game = new TicTacToeGame(Arrays.<WinnerVerifier> asList(verifier));
      game.putAMarkAt(BOTTOM_LEFT, CROSS);
      game.putAMarkAt(TOP_LEFT, NOUGHT);
      game.putAMarkAt(BOTTOM_CENTER, CROSS);
      game.putAMarkAt(TOP_RIGHT, NOUGHT);
      game.putAMarkAt(BOTTOM_RIGHT, CROSS);
      return game;
   }

}
