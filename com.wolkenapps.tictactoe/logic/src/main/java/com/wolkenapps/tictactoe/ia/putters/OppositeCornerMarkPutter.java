package com.wolkenapps.tictactoe.ia.putters;

import static com.wolkenapps.tictactoe.Mark.BLANK;
import static com.wolkenapps.tictactoe.Point.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.wolkenapps.tictactoe.Mark;
import com.wolkenapps.tictactoe.Point;
import com.wolkenapps.tictactoe.TicTacToeGameController;
import com.wolkenapps.tictactoe.ia.MarkPutter;

public class OppositeCornerMarkPutter implements MarkPutter {

   private static Map<Point, Point> opposites = new HashMap<Point, Point>();
   private Point lastPoint;

   public OppositeCornerMarkPutter() {
      opposites.put(TOP_LEFT, BOTTOM_RIGHT);
      opposites.put(BOTTOM_RIGHT, TOP_LEFT);
      opposites.put(TOP_RIGHT, BOTTOM_LEFT);
      opposites.put(BOTTOM_LEFT, TOP_RIGHT);
   }

   @Override
   public void putAMarkUsing(TicTacToeGameController controller) {
      for (Entry<Point, Point> opposite : opposites.entrySet()) {
         if (isCandidate(opposite, controller)) {
            controller.putTheNextMarkAt(lastPoint = opposite.getValue());
            return;
         }
      }
      throw new MarkPutter.CannotPutAMark();
   }

   private boolean isCandidate(Entry<Point, Point> opposite, TicTacToeGameController controller) {
      Mark mine = controller.getPossibleNextMark();
      return mine.equals(controller.getMarkAt(opposite.getKey()));
   }

   @Override
   public Point getThePointUsed() {
      return lastPoint;
   }

}
