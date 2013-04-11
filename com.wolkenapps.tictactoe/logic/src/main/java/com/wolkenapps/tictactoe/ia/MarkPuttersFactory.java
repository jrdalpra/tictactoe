package com.wolkenapps.tictactoe.ia;

import java.util.Arrays;

import com.wolkenapps.tictactoe.ia.putters.SideMarkPutter;
import com.wolkenapps.tictactoe.ia.putters.DefensiveMarkPutter;
import com.wolkenapps.tictactoe.ia.putters.MiddleCenterMarkPutter;
import com.wolkenapps.tictactoe.ia.putters.OffensiveMarkPutter;
import com.wolkenapps.tictactoe.ia.putters.CornerMarkPutter;

public class MarkPuttersFactory {

   public static Iterable<MarkPutter> getAllPossibleMarkPutters() {
      return Arrays.<MarkPutter> asList(new MiddleCenterMarkPutter(),
                                        new OffensiveMarkPutter(),
                                        new DefensiveMarkPutter(),
                                        new CornerMarkPutter(),
                                        new SideMarkPutter());
   }

   private MarkPuttersFactory() {
   }

}
