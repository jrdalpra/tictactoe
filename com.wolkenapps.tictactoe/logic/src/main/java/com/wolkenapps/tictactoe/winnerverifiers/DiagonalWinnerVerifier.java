package com.wolkenapps.tictactoe.winnerverifiers;

import static com.wolkenapps.tictactoe.WinnerPoints.antiClockWiseDiagonal;
import static com.wolkenapps.tictactoe.WinnerPoints.clockWiseDiagonal;

import com.wolkenapps.tictactoe.TicTacToeGame;
import com.wolkenapps.tictactoe.WinnerPoints;
import com.wolkenapps.tictactoe.WinnerVerifier;

public class DiagonalWinnerVerifier implements WinnerVerifier {

   public boolean confirmsThatThereIsWinner(TicTacToeGame game) {
      return canFindAWinnerAtClockWiseDiagonalAt(game) ||
             canFindAWinnerAtAntiClockWiseDiagonalAt(game);
   }

   private boolean canFindAWinnerAtAntiClockWiseDiagonalAt(TicTacToeGame game) {
      return WinnerVerifier.CheckIf.theWinnerPointsHaveAWinner(game, antiClockWiseDiagonal());
   }

   private boolean canFindAWinnerAtClockWiseDiagonalAt(TicTacToeGame game) {
      return WinnerVerifier.CheckIf.theWinnerPointsHaveAWinner(game, clockWiseDiagonal());
   }

   @Override
   public WinnerPoints getWinnerPointsAt(TicTacToeGame game) {
      if (canFindAWinnerAtAntiClockWiseDiagonalAt(game))
         return antiClockWiseDiagonal();
      if (canFindAWinnerAtClockWiseDiagonalAt(game))
         return clockWiseDiagonal();
      throw new WinnerPoints.NotFound();
   }

}
