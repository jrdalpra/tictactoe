package com.wolkenapps.tictactoe.winnerverifiers;

import static com.wolkenapps.tictactoe.WinnerPoints.*;

import com.wolkenapps.tictactoe.TicTacToeGame;
import com.wolkenapps.tictactoe.WinnerPoints;
import com.wolkenapps.tictactoe.WinnerVerifier;

public class VerticalWinnerVerifier implements WinnerVerifier {

   public boolean confirmsThatThereIsWinner(TicTacToeGame game) {
      return canFindAWinnerGameAtLeftColumnAt(game) ||
             canFindAWinnerGameAtCenterColumnAt(game) ||
             canFindAWinnerGameAtRightColumnAt(game);
   }

   private boolean canFindAWinnerGameAtLeftColumnAt(TicTacToeGame game) {
      return WinnerVerifier.CheckIf.theWinnerPointsHaveAWinner(game, leftColumn());
   }

   private boolean canFindAWinnerGameAtCenterColumnAt(TicTacToeGame game) {
      return WinnerVerifier.CheckIf.theWinnerPointsHaveAWinner(game, centerColumn());
   }

   private boolean canFindAWinnerGameAtRightColumnAt(TicTacToeGame game) {
      return WinnerVerifier.CheckIf.theWinnerPointsHaveAWinner(game, rightColumn());
   }

   @Override
   public WinnerPoints getWinnerPointsAt(TicTacToeGame game) {
      if (canFindAWinnerGameAtLeftColumnAt(game))
         return leftColumn();
      if (canFindAWinnerGameAtCenterColumnAt(game))
         return centerColumn();
      if (canFindAWinnerGameAtRightColumnAt(game))
         return rightColumn();
      throw new WinnerPoints.NotFound();
   }

}
