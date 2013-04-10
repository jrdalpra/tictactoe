package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Mark.BLANK;

public interface WinnerVerifier {

   public static final class CheckIf {
      public static boolean theWinnerPointsHaveAWinner(TicTacToeGame game, WinnerPoints points) {
         Mark first = game.getMarkAt(points.getFirst());
         Mark second = game.getMarkAt(points.getSecond());
         Mark third = game.getMarkAt(points.getThird());
         return !first.equals(BLANK) && first.equals(second) && second.equals(third);
      }
   }

   boolean confirmsThatThereIsWinner(TicTacToeGame game);

   WinnerPoints getWinnerPointsAt(TicTacToeGame game);

}
