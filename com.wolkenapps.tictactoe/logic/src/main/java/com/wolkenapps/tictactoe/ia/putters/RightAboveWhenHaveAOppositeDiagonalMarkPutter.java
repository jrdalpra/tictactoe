package com.wolkenapps.tictactoe.ia.putters;

import static com.wolkenapps.tictactoe.Mark.BLANK;
import static com.wolkenapps.tictactoe.Point.MIDDLE_LEFT;
import static com.wolkenapps.tictactoe.Point.MIDDLE_RIGHT;

import com.wolkenapps.tictactoe.Mark;
import com.wolkenapps.tictactoe.Point;
import com.wolkenapps.tictactoe.TicTacToeGameController;
import com.wolkenapps.tictactoe.WinnerPoints;
import com.wolkenapps.tictactoe.ia.MarkPutter;

public class RightAboveWhenHaveAOppositeDiagonalMarkPutter implements MarkPutter {

   private Point lastPoint;

   @Override
   public void putAMarkUsing(TicTacToeGameController controller) {
      if (theIsOppositesMarksAtClockwiseDiagonal(controller)) {
         controller.putTheNextMarkAt(lastPoint = MIDDLE_LEFT);
         return;
      }
      if (theIsOppositesMarksAtAntiClockwiseDiagonal(controller)) {
         controller.putTheNextMarkAt(lastPoint = MIDDLE_RIGHT);
         return;
      }
      throw new MarkPutter.CannotPutAMark();
   }

   private boolean theIsOppositesMarksAtAntiClockwiseDiagonal(TicTacToeGameController controller) {
      WinnerPoints clockwise = WinnerPoints.antiClockWiseDiagonal();
      Mark one = controller.getMarkAt(clockwise.getFirst());
      Mark opposite = controller.getMarkAt(clockwise.getThird());
      Mark mine = controller.getPossibleNextMark();
      return !mine.equals(one) && !one.equals(BLANK) && one.equals(opposite);
   }

   private boolean theIsOppositesMarksAtClockwiseDiagonal(TicTacToeGameController controller) {
      WinnerPoints clockwise = WinnerPoints.clockWiseDiagonal();
      Mark one = controller.getMarkAt(clockwise.getFirst());
      Mark opposite = controller.getMarkAt(clockwise.getThird());
      Mark mine = controller.getPossibleNextMark();
      return !mine.equals(one) && !one.equals(BLANK) && one.equals(opposite);
   }

   @Override
   public Point getThePointUsed() {
      return lastPoint;
   }

}
