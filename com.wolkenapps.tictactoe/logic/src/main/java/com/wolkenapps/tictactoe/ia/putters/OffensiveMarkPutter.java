package com.wolkenapps.tictactoe.ia.putters;

import com.wolkenapps.tictactoe.Mark;
import com.wolkenapps.tictactoe.Point;
import com.wolkenapps.tictactoe.TicTacToeGameController;
import com.wolkenapps.tictactoe.WinnerPoints;
import com.wolkenapps.tictactoe.ia.MarkPutter;

public class OffensiveMarkPutter implements MarkPutter {

   private Point pointUsed;

   @Override
   public void putAMarkUsing(TicTacToeGameController controller) {
      for (WinnerPoints points : WinnerPoints.allPossibleWinnerPoints()) {
         Mark mine = controller.getPossibleNextMark();

         Mark first = controller.getMarkAt(points.getFirst());
         Mark second = controller.getMarkAt(points.getSecond());
         Mark third = controller.getMarkAt(points.getThird());

         if (first.equals(second) && mine.equals(first)) {
            controller.putTheNextMarkAt(pointUsed = points.getThird());
            return;
         }
         if (second.equals(third) && mine.equals(second)) {
            controller.putTheNextMarkAt(pointUsed = points.getFirst());
            return;
         }
         if (third.equals(first) && mine.equals(third)) {
            controller.putTheNextMarkAt(pointUsed = points.getSecond());
            return;
         }
      }
      throw new MarkPutter.CannotPutAMark();
   }

   @Override
   public Point getThePointUsed() {
      return pointUsed;
   }

}
