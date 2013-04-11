package com.wolkenapps.tictactoe.ia.putters;

import static com.wolkenapps.tictactoe.Mark.BLANK;

import com.wolkenapps.tictactoe.Mark;
import com.wolkenapps.tictactoe.Point;
import com.wolkenapps.tictactoe.TicTacToeGame;
import com.wolkenapps.tictactoe.TicTacToeGameController;
import com.wolkenapps.tictactoe.WinnerPoints;
import com.wolkenapps.tictactoe.ia.MarkPutter;

public class DefensiveMarkPutter implements MarkPutter {

   private Point pointUsed;

   @Override
   public void putAMarkUsing(TicTacToeGameController controller) {
      Mark mine = controller.getPossibleNextMark();

      for (WinnerPoints points : WinnerPoints.allPossibleWinnerPoints()) {
         try {
            Mark first = controller.getMarkAt(points.getFirst());
            Mark second = controller.getMarkAt(points.getSecond());
            Mark third = controller.getMarkAt(points.getThird());

            if (!first.equals(BLANK) && first.equals(second) && !mine.equals(first)) {
               controller.putTheNextMarkAt(points.getThird());
               pointUsed = points.getThird();
               return;
            }

            if (!second.equals(BLANK) && second.equals(third) && !mine.equals(second)) {
               controller.putTheNextMarkAt(points.getFirst());
               pointUsed = points.getFirst();
               return;
            }

            if (!third.equals(BLANK) && third.equals(first) && !mine.equals(third)) {
               controller.putTheNextMarkAt(points.getSecond());
               pointUsed = points.getSecond();
               return;
            }
         } catch (TicTacToeGame.SpaceAlreadyFilled filled) {
         }
      }
      throw new MarkPutter.CannotPutAMark();
   }

   @Override
   public Point getThePointUsed() {
      return pointUsed;
   }

}
