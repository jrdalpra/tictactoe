package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Mark.CROSS;
import static com.wolkenapps.tictactoe.Mark.NOUGHT;
import static com.wolkenapps.tictactoe.Point.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.wolkenapps.tictactoe.winnerverifiers.DiagonalWinnerVerifier;

public class DiagonalWinnerVerifierTest {

   DiagonalWinnerVerifier verifier;

   @Before
   public void setup() {
      verifier = new DiagonalWinnerVerifier();
   }

   @Test
   public void canFindAWinnerAtAClockWiseWinnerGame() {
      assertTrue(verifier.confirmsThatThereIsWinner(clockwiseDiagonalGame()));
   }

   private TicTacToeGame clockwiseDiagonalGame() {
      TicTacToeGame game = new TicTacToeGame(Arrays.<WinnerVerifier> asList(verifier));
      game.putAMarkAt(TOP_LEFT, NOUGHT);
      game.putAMarkAt(TOP_CENTER, CROSS);
      game.putAMarkAt(MIDDLE_CENTER, NOUGHT);
      game.putAMarkAt(TOP_RIGHT, CROSS);
      game.putAMarkAt(BOTTOM_RIGHT, NOUGHT);
      return game;
   }

   @Test
   public void canFindAWinnerAtAnAntiClockWiseWinnerGame() {
      assertTrue(verifier.confirmsThatThereIsWinner(antiClockwiseDiagonalGame()));
   }

   private TicTacToeGame antiClockwiseDiagonalGame() {
      TicTacToeGame game = new TicTacToeGame(Arrays.<WinnerVerifier> asList(verifier));
      game.putAMarkAt(BOTTOM_LEFT, NOUGHT);
      game.putAMarkAt(TOP_CENTER, CROSS);
      game.putAMarkAt(MIDDLE_CENTER, NOUGHT);
      game.putAMarkAt(TOP_LEFT, CROSS);
      game.putAMarkAt(TOP_RIGHT, NOUGHT);
      return game;
   }

   @Test
   public void mustReturnAClockWiseWinnerPoints() {
      assertEquals(new WinnerPoints(TOP_LEFT, MIDDLE_CENTER, BOTTOM_RIGHT), verifier.getWinnerPointsAt(clockwiseDiagonalGame()));
   }

   @Test
   public void mustReturnAnAntiClockWiseWinnerPoints() {
      assertEquals(new WinnerPoints(BOTTOM_LEFT, MIDDLE_CENTER, TOP_RIGHT), verifier.getWinnerPointsAt(antiClockwiseDiagonalGame()));
   }

}
