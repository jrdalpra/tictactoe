package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Mark.CROSS;
import static com.wolkenapps.tictactoe.Mark.NOUGHT;

import java.util.Arrays;

import com.wolkenapps.tictactoe.winnerverifiers.DiagonalWinnerVerifier;
import com.wolkenapps.tictactoe.winnerverifiers.HorizontalWinnerVerificer;
import com.wolkenapps.tictactoe.winnerverifiers.VerticalWinnerVerifier;

public class TicTacToeGameController {

   private TicTacToeGame game;
   private Mark          mark;

   public boolean theGameHasStarted() {
      return game != null && !game.hasEnded();
   }

   public void startsTheGame() {
      game = new TicTacToeGame(withAllVerifiers());
      mark = Mark.BLANK;
   }

   private Iterable<WinnerVerifier> withAllVerifiers() {
      return Arrays.<WinnerVerifier> asList(new DiagonalWinnerVerifier(),
                                            new HorizontalWinnerVerificer(),
                                            new VerticalWinnerVerifier());
   }

   public boolean theGameHasEnded() {
      return game != null && game.hasEnded();
   }

   public Mark putTheNextMarkAt(Point location) {
      if (!theGameHasStarted())
         throw new TicTacToeGame.NotStarted();
      game.putAMarkAt(location, nextMark());
      return mark;
   }

   private Mark nextMark() {
      return mark = (mark.equals(CROSS) ? NOUGHT : CROSS);
   }

   public WinnerPoints getWinnerPoints() {
      return game.getWinnerPoints();
   }

   public boolean theGameHasDrawn() {
      return game != null && game.hasDrawn();
   }
}
