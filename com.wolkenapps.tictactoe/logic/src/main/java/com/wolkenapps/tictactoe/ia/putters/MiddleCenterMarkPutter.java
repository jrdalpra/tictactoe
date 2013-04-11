package com.wolkenapps.tictactoe.ia.putters;

import static com.wolkenapps.tictactoe.Point.MIDDLE_CENTER;

import com.wolkenapps.tictactoe.Point;
import com.wolkenapps.tictactoe.TicTacToeGameController;
import com.wolkenapps.tictactoe.ia.MarkPutter;

public class MiddleCenterMarkPutter implements MarkPutter {

   @Override
   public void putAMarkUsing(TicTacToeGameController controller) {
      controller.putTheNextMarkAt(MIDDLE_CENTER);
   }

   @Override
   public Point getThePointUsed() {
      return MIDDLE_CENTER;
   }

}
