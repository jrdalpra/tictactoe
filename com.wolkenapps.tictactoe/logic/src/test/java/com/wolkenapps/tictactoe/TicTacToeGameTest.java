package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Mark.*;
import static com.wolkenapps.tictactoe.Point.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wolkenapps.tictactoe.winnerverifiers.DiagonalWinnerVerifier;

public class TicTacToeGameTest {

   private TicTacToeGame game;

   @Before
   public void setup() {
      game = new TicTacToeGame(Arrays.<WinnerVerifier> asList(new DiagonalWinnerVerifier()));
   }

   @Test
   public void afterCreateATicTacToeGame_TheSpacesNumberShouldBeNine() {
      assertEquals(9, game.getSpacesNumber());
   }

   @Test
   public void afterCreateATicTacToeGame_AllSpacesMustHaveBlankMarks() {
      assertEquals(9, game.getSpacesNumberWithMark(BLANK));
   }

   @Test
   public void afterCreateATicTacToeGame_NoneSpacesMustHaveCrossesMarks() {
      assertEquals(0, game.getSpacesNumberWithMark(CROSS));
   }

   @Test
   public void afterCreateATicTacToeGame_NoneSpacesMustHaveNoughtsMarks() {
      assertEquals(0, game.getSpacesNumberWithMark(NOUGHT));
   }

   @Test
   public void afterPutOnlyOneCrossMark_TheNumberOfSpacesWithCrossMarkMustBeOne() {
      game.putAMarkAt(MIDDLE_CENTER, CROSS);
      assertEquals(1, game.getSpacesNumberWithMark(CROSS));
   }

   @Test(expected = TicTacToeGame.SpaceAlreadyFilled.class)
   public void whenTryToPutAMarkAtOneSpaceThatAlreadyHasOne_ShouldThrowAnException() {
      game.putAMarkAt(TOP_LEFT, CROSS);
      game.putAMarkAt(TOP_LEFT, CROSS);
   }

   @Test(expected = TicTacToeGame.CannotPlayTwiceInARow.class)
   public void whenTryToPutTheSameMarkTwiceInARow_ShouldThrowAnException() {
      game.putAMarkAt(TOP_LEFT, CROSS);
      game.putAMarkAt(TOP_CENTER, CROSS);
   }

   @Test
   public void afterMarkWithOneCrossAndOneNought_MustHaveOneSpaceWithEachMarkAnd7WithBlank() {
      game.putAMarkAt(TOP_LEFT, CROSS);
      game.putAMarkAt(TOP_CENTER, NOUGHT);
      assertEquals(1, game.getSpacesNumberWithMark(CROSS));
      assertEquals(1, game.getSpacesNumberWithMark(NOUGHT));
      assertEquals(7, game.getSpacesNumberWithMark(BLANK));
   }

   @Test
   public void afterMakeSomeAWinnerCombination_MustHasAWinner() {
      makeADiagonalNoughtMarksWinner();
      assertTrue(game.hasAWinner());
   }

   @Test(expected = TicTacToeGame.AlreadHasAWinner.class)
   public void afterThatThereIsAWinner_IfTryToPlay_MustThrowAnException() {
      makeADiagonalNoughtMarksWinner();
      game.putAMarkAt(BOTTOM_LEFT, CROSS);
   }

   private void makeADiagonalNoughtMarksWinner() {
      game.putAMarkAt(TOP_LEFT, NOUGHT);
      game.putAMarkAt(TOP_CENTER, CROSS);
      game.putAMarkAt(MIDDLE_CENTER, NOUGHT);
      game.putAMarkAt(TOP_RIGHT, CROSS);
      game.putAMarkAt(BOTTOM_RIGHT, NOUGHT);
   }

   @Test(expected = TicTacToeGame.HasNoVerifiers.class)
   public void mustThrowAException_WhenTryToPutAMarkOnAGameWithoutVerifiers() {
      gameWithoutVerifiers().putAMarkAt(TOP_LEFT, CROSS);
   }

   private TicTacToeGame gameWithoutVerifiers() {
      return new TicTacToeGame(null);
   }

   @Test
   public void afterFinishAGame_WithAClockWiseDiagonalLine_MustReturnTheWinnerLine() {
      makeADiagonalNoughtMarksWinner();
      assertEquals(new WinnerPoints(TOP_LEFT, MIDDLE_CENTER, BOTTOM_RIGHT), game.getWinnerPoints());
   }

   @Test(expected = TicTacToeGame.HasNoWinner.class)
   public void mustThrowA_TicTacToeHasNoWinner_WhenTryToGetTheWinnerPathInADrawGame() {
      makeADrawGame();
      game.getWinnerPoints();
   }

   private void makeADrawGame() {
      game.putAMarkAt(TOP_LEFT, NOUGHT);
      game.putAMarkAt(TOP_CENTER, CROSS);
      game.putAMarkAt(TOP_RIGHT, NOUGHT);
      game.putAMarkAt(MIDDLE_LEFT, CROSS);
      game.putAMarkAt(MIDDLE_CENTER, NOUGHT);
      game.putAMarkAt(BOTTOM_LEFT, CROSS);
      game.putAMarkAt(BOTTOM_CENTER, NOUGHT);
      game.putAMarkAt(BOTTOM_RIGHT, CROSS);
      game.putAMarkAt(MIDDLE_RIGHT, NOUGHT);
   }

   @After
   public void teardown() {
   }

}
