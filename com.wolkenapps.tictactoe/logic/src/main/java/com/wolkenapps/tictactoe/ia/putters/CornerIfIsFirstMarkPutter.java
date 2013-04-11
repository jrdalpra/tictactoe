package com.wolkenapps.tictactoe.ia.putters;

import static com.wolkenapps.tictactoe.Point.BOTTOM_LEFT;

import com.wolkenapps.tictactoe.Point;
import com.wolkenapps.tictactoe.TicTacToeGameController;
import com.wolkenapps.tictactoe.ia.MarkPutter;

public class CornerIfIsFirstMarkPutter implements MarkPutter {

   @Override
   public void putAMarkUsing(TicTacToeGameController controller) {
      if (!controller.isFirstMove())
         throw new MarkPutter.CannotPutAMark();
      controller.putTheNextMarkAt(BOTTOM_LEFT);
   }

   @Override
   public Point getThePointUsed() {
      return BOTTOM_LEFT;
   }

}
