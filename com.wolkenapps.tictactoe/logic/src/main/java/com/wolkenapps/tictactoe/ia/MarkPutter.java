package com.wolkenapps.tictactoe.ia;

import com.wolkenapps.tictactoe.Point;
import com.wolkenapps.tictactoe.TicTacToeGameController;

public interface MarkPutter {

   @SuppressWarnings("serial")
   public static class CannotPutAMark extends RuntimeException {
   }

   void putAMarkUsing(TicTacToeGameController controller);

   Point getThePointUsed();

}
