package com.wolkenapps.tictactoe;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PointTest {

   private static final int TOP_ROW       = 0;
   private static final int MIDDLE_ROW    = 1;
   private static final int BOTTOM_ROW    = 2;

   private static final int LEFT_COLUMN   = 0;
   private static final int CENTER_COLUMN = 1;
   private static final int RIGHT_COLUMN  = 2;

   @Test
   public void testIfTheNumberOfPointsIsEqualsNine() {
      assertEquals(9, Point.values().length);
   }

   @Test
   public void testAllPossiblePoints() {
      testTopPoints();
      testMiddlePoints();
      testBottomPoints();
   }

   private void testBottomPoints() {
      assertEquals(Point.at(BOTTOM_ROW, LEFT_COLUMN), Point.BOTTOM_LEFT);
      assertEquals(Point.at(BOTTOM_ROW, CENTER_COLUMN), Point.BOTTOM_CENTER);
      assertEquals(Point.at(BOTTOM_ROW, RIGHT_COLUMN), Point.BOTTOM_RIGHT);
   }

   private void testMiddlePoints() {
      assertEquals(Point.at(MIDDLE_ROW, LEFT_COLUMN), Point.MIDDLE_LEFT);
      assertEquals(Point.at(MIDDLE_ROW, CENTER_COLUMN), Point.MIDDLE_CENTER);
      assertEquals(Point.at(MIDDLE_ROW, RIGHT_COLUMN), Point.MIDDLE_RIGHT);
   }

   private void testTopPoints() {
      assertEquals(Point.at(TOP_ROW, LEFT_COLUMN), Point.TOP_LEFT);
      assertEquals(Point.at(TOP_ROW, CENTER_COLUMN), Point.TOP_CENTER);
      assertEquals(Point.at(TOP_ROW, RIGHT_COLUMN), Point.TOP_RIGHT);
   }

   @Test(expected = Point.NotFoundAtRowAndColumn.class)
   public void mustThrowAException_WhenTryingToGetAUnknowPoint() {
      Point.at(0, 3);
   }

}
