package com.wolkenapps.tictactoe.ia.putters;

import static com.wolkenapps.tictactoe.Point.*;
import static com.wolkenapps.tictactoe.Point.BOTTOM_LEFT;
import static com.wolkenapps.tictactoe.Point.TOP_LEFT;

import java.util.Arrays;
import java.util.List;

import com.wolkenapps.tictactoe.Point;
import com.wolkenapps.tictactoe.TicTacToeGame;
import com.wolkenapps.tictactoe.TicTacToeGameController;
import com.wolkenapps.tictactoe.ia.MarkPutter;

public class CornerMarkPutter implements MarkPutter {

   private Point lastPoint;

   @Override
   public void putAMarkUsing(TicTacToeGameController controller) {
      for (Point corner : corners()) {
         try {
            controller.putTheNextMarkAt(corner);
            lastPoint = corner;
            return;
         } catch (TicTacToeGame.SpaceAlreadyFilled spaceAlreadyFilled) {
         }
      }
      throw new MarkPutter.CannotPutAMark();
   }

   private List<Point> corners() {
      return Arrays.<Point> asList(TOP_LEFT, BOTTOM_LEFT, TOP_RIGHT, BOTTOM_RIGHT);
   }

   @Override
   public Point getThePointUsed() {
      return lastPoint;
   }

}
