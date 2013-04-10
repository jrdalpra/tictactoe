package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Point.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class WinnerPoints implements Iterable<Point> {

   public static class NotFound extends RuntimeException {
      private static final long serialVersionUID = 1L;
   }

   public static WinnerPoints topRow() {
      return new WinnerPoints(TOP_LEFT, TOP_CENTER, TOP_RIGHT);
   }

   public static WinnerPoints middleRow() {
      return new WinnerPoints(MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT);
   }

   public static WinnerPoints bottomRow() {
      return new WinnerPoints(BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT);
   }

   public static WinnerPoints leftColumn() {
      return new WinnerPoints(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT);
   }

   public static WinnerPoints centerColumn() {
      return new WinnerPoints(TOP_CENTER, MIDDLE_CENTER, BOTTOM_CENTER);
   }

   public static WinnerPoints rightColumn() {
      return new WinnerPoints(TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT);
   }

   public static WinnerPoints clockWiseDiagonal() {
      return new WinnerPoints(TOP_LEFT, MIDDLE_CENTER, BOTTOM_RIGHT);
   }

   public static WinnerPoints antiClockWiseDiagonal() {
      return new WinnerPoints(BOTTOM_LEFT, MIDDLE_CENTER, TOP_RIGHT);
   }

   private final Point first;
   private final Point second;
   private final Point third;

   protected WinnerPoints(Point first,
                          Point second,
                          Point third) {
      this.first = first;
      this.second = second;
      this.third = third;
   }

   public Point getFirst() {
      return first;
   }

   public Point getSecond() {
      return second;
   }

   public Point getThird() {
      return third;
   }

   @Override
   public String toString() {
      return this.points().toString();
   }

   private List<Point> points() {
      return Arrays.<Point> asList(first, second, third);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof WinnerPoints))
         return false;
      WinnerPoints other = (WinnerPoints) obj;
      return this.points().containsAll(other.points());
   }

   @Override
   public Iterator<Point> iterator() {
      return points().iterator();
   }

}
