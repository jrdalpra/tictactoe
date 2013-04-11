package com.wolkenapps.tictactoe.ia;

import static com.wolkenapps.tictactoe.Point.*;
import static com.wolkenapps.tictactoe.Point.BOTTOM_LEFT;
import static com.wolkenapps.tictactoe.Point.MIDDLE_CENTER;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.wolkenapps.tictactoe.TicTacToeGameController;
import com.wolkenapps.tictactoe.ia.putters.OffensiveMarkPutter;

public class OffensiveMarkPutterTest {

   private OffensiveMarkPutter putter;
   private TicTacToeGameController controller;

   @Before
   public void setup(){
      putter = new OffensiveMarkPutter();
      controller = new TicTacToeGameController();
   }

   @Test
   public void whenThereIsAChangeToWinTheGame_OnALeftColumnWinnerPoints_MustPutAMark_AtTopLeft(){
      controller.startsTheGame();
      controller.putTheNextMarkAt(MIDDLE_CENTER);
      controller.putTheNextMarkAt(BOTTOM_LEFT);
      controller.putTheNextMarkAt(MIDDLE_RIGHT);
      controller.putTheNextMarkAt(MIDDLE_LEFT);
      controller.putTheNextMarkAt(BOTTOM_CENTER);
      putter.putAMarkUsing(controller);
      assertEquals(TOP_LEFT, putter.getThePointUsed());
   }

   @Test
   public void whenThereIsAChangeToWinTheGame_OnALeftColumnWinnerPoints_MustPutAMark_AtBottomLeft(){
      controller.startsTheGame();
      controller.putTheNextMarkAt(MIDDLE_CENTER);
      controller.putTheNextMarkAt(TOP_LEFT);
      controller.putTheNextMarkAt(MIDDLE_RIGHT);
      controller.putTheNextMarkAt(MIDDLE_LEFT);
      controller.putTheNextMarkAt(BOTTOM_CENTER);
      putter.putAMarkUsing(controller);
      assertEquals(BOTTOM_LEFT, putter.getThePointUsed());
   }


}
