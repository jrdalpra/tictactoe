package com.wolkenapps.tictactoe;

public enum Point {

   TOP_LEFT(0, 0),
   TOP_CENTER(0, 1),
   TOP_RIGHT(0, 2),

   MIDDLE_LEFT(1, 0),
   MIDDLE_CENTER(1, 1),
   MIDDLE_RIGHT(1, 2),

   BOTTOM_LEFT(2, 0),
   BOTTOM_CENTER(2, 1),
   BOTTOM_RIGHT(2, 2);

   public static class NotFoundAtRowAndColumn extends RuntimeException {
      private static final long serialVersionUID = 1L;
      private int               row;
      private int               column;

      public NotFoundAtRowAndColumn(int row, int column) {
         this.row = row;
         this.column = column;
      }

      public int getRow() {
         return row;
      }

      public int getColumn() {
         return column;
      }
   }

   private final int row;
   private final int column;

   private Point(int row, int column) {
      this.row = row;
      this.column = column;
   }

   public boolean isAt(int row, int column) {
      return this.row == row && this.column == column;
   }

   public static final Point at(int row, int column) {
      for (Point point : values()) {
         if (point.isAt(row, column)) {
            return point;
         }
      }
      throw new Point.NotFoundAtRowAndColumn(row, column);
   }

}
