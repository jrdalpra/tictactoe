package com.wolkenapps.tictactoe.ia;

import com.wolkenapps.tictactoe.Point;
import com.wolkenapps.tictactoe.TicTacToeGameController;

public interface MarkPutter {

    void putAMarkUsing(TicTacToeGameController controller);

    Point getThePointUsed();

}
