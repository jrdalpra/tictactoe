package com.wolkenapps.tictactoe.ia.putters;

import static com.wolkenapps.tictactoe.Point.*;

import java.util.Arrays;
import java.util.List;

import com.wolkenapps.tictactoe.Point;
import com.wolkenapps.tictactoe.TicTacToeGame;
import com.wolkenapps.tictactoe.TicTacToeGameController;
import com.wolkenapps.tictactoe.ia.MarkPutter;

public class SideMarkPutter implements MarkPutter {

   private Point lastPoint;

   @Override
   public void putAMarkUsing(TicTacToeGameController controller) {
      for (Point side : sides()) {
         try {
            controller.putTheNextMarkAt(side);
            lastPoint = side;
            return;
         } catch (TicTacToeGame.SpaceAlreadyFilled spaceAlreadyFilled) {
         }
      }
      throw new MarkPutter.CannotPutAMark();
   }

   private List<Point> sides() {
      return Arrays.<Point> asList(MIDDLE_LEFT, TOP_CENTER, MIDDLE_RIGHT, BOTTOM_CENTER);
   }

   @Override
   public Point getThePointUsed() {
      return lastPoint;
   }

}
