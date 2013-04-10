package com.wolkenapps.tictactoe.winnerverifiers;

import static com.wolkenapps.tictactoe.WinnerPoints.*;

import com.wolkenapps.tictactoe.TicTacToeGame;
import com.wolkenapps.tictactoe.WinnerPoints;
import com.wolkenapps.tictactoe.WinnerVerifier;

public class HorizontalWinnerVerificer implements WinnerVerifier {

   public boolean confirmsThatThereIsWinner(TicTacToeGame game) {
      return canFindAWinnerOnTopRowAt(game) ||
             canFindAWinnerOnMiddleRowAt(game) ||
             canFindAWinnerOnBottomRowAt(game);
   }

   private boolean canFindAWinnerOnTopRowAt(TicTacToeGame game) {
      return WinnerVerifier.CheckIf.theWinnerPointsHaveAWinner(game, topRow());
   }

   private boolean canFindAWinnerOnMiddleRowAt(TicTacToeGame game) {
      return WinnerVerifier.CheckIf.theWinnerPointsHaveAWinner(game, middleRow());
   }

   private boolean canFindAWinnerOnBottomRowAt(TicTacToeGame game) {
      return WinnerVerifier.CheckIf.theWinnerPointsHaveAWinner(game, bottomRow());
   }

   @Override
   public WinnerPoints getWinnerPointsAt(TicTacToeGame game) {
      if (canFindAWinnerOnTopRowAt(game))
         return topRow();
      if (canFindAWinnerOnMiddleRowAt(game))
         return middleRow();
      if (canFindAWinnerOnBottomRowAt(game))
         return bottomRow();
      throw new WinnerPoints.NotFound();
   }

}
