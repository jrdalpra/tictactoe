package com.wolkenapps.tictactoe.ia;

import static com.wolkenapps.tictactoe.Point.*;
import static com.wolkenapps.tictactoe.Point.BOTTOM_LEFT;
import static com.wolkenapps.tictactoe.Point.MIDDLE_CENTER;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.wolkenapps.tictactoe.TicTacToeGameController;
import com.wolkenapps.tictactoe.ia.putters.DefensiveMarkPutter;

public class DefensiveMarkPutterTest {

   private DefensiveMarkPutter putter;
   private TicTacToeGameController controller;

   @Before
   public void setup(){
      putter = new DefensiveMarkPutter();
      controller = new TicTacToeGameController();
   }

   @Test
   public void whenThereIsAPossibleThreatAtClockwiseDiagonal_MustDefendTheGameAtTopLeft(){
      makeAClockwisePointsThreatGameWithATopLeftBlankPoint();
      putter.putAMarkUsing(controller);
      assertEquals(TOP_LEFT, putter.getThePointUsed());
   }

   private void makeAClockwisePointsThreatGameWithATopLeftBlankPoint() {
      controller.startsTheGame();
      controller.putTheNextMarkAt(MIDDLE_CENTER);
      controller.putTheNextMarkAt(BOTTOM_LEFT);
      controller.putTheNextMarkAt(BOTTOM_RIGHT);
   }

   @Test
   public void whenThereIsAPossibleThreatAtClockwiseDiagonal_MustDefendTheGameAtMiddleCenter(){
      makeAClockwisePointsThreatGameWithAMiddleCenterBlankPoint();
      putter.putAMarkUsing(controller);
      assertEquals(MIDDLE_CENTER, putter.getThePointUsed());
   }

   private void makeAClockwisePointsThreatGameWithAMiddleCenterBlankPoint() {
      controller.startsTheGame();
      controller.putTheNextMarkAt(TOP_LEFT);
      controller.putTheNextMarkAt(BOTTOM_CENTER);
      controller.putTheNextMarkAt(BOTTOM_RIGHT);
   }

   @Test
   public void whenThereIsAPossibleThreatAtClockwiseDiagonal_MustDefendTheGameAtBottomRight(){
      makeAClockwisePointsThreatGameWithABottomRightBlankPoint();
      putter.putAMarkUsing(controller);
      assertEquals(BOTTOM_RIGHT, putter.getThePointUsed());
   }

   private void makeAClockwisePointsThreatGameWithABottomRightBlankPoint() {
      controller.startsTheGame();
      controller.putTheNextMarkAt(MIDDLE_CENTER);
      controller.putTheNextMarkAt(BOTTOM_CENTER);
      controller.putTheNextMarkAt(TOP_LEFT);
   }

   @Test
   public void whenThereIsAPossibleThreatAtAntiClockwiseDiagonal_MustDefendTheGameAtBottomLeft(){
      makeAClockwisePointsThreatGameWithABottomLeftBlankPoint();
      putter.putAMarkUsing(controller);
      assertEquals(BOTTOM_LEFT, putter.getThePointUsed());
   }

   private void makeAClockwisePointsThreatGameWithABottomLeftBlankPoint() {
      controller.startsTheGame();
      controller.putTheNextMarkAt(MIDDLE_CENTER);
      controller.putTheNextMarkAt(MIDDLE_LEFT);
      controller.putTheNextMarkAt(TOP_RIGHT);
   }


}
