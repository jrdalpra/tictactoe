package com.wolkenapps.tictactoe.ia;

import java.util.Arrays;

import com.wolkenapps.tictactoe.ia.putters.BottomLeftMarkPutter;
import com.wolkenapps.tictactoe.ia.putters.MiddleCenterMarkPutter;

public class MarkPuttersFactory {

    public static Iterable<MarkPutter> getAllPossibleMarkPutters() {
        return Arrays.<MarkPutter> asList(new MiddleCenterMarkPutter(),
                                          new BottomLeftMarkPutter());
    }

    private MarkPuttersFactory() {
    }

}
