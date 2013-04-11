package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Mark.CROSS;
import static com.wolkenapps.tictactoe.Mark.NOUGHT;

import java.util.Arrays;

import com.wolkenapps.tictactoe.winnerverifiers.DiagonalWinnerVerifier;
import com.wolkenapps.tictactoe.winnerverifiers.HorizontalWinnerVerificer;
import com.wolkenapps.tictactoe.winnerverifiers.VerticalWinnerVerifier;

public class TicTacToeGameController {

    private TicTacToeGame game;

    private Mark          mark;

    public boolean theGameHasStarted() {
        return game != null && !game.hasEnded();
    }

    public void startsTheGame() {
        game = new TicTacToeGame(withAllVerifiers());
        mark = Mark.BLANK;
    }

    private Iterable<WinnerVerifier> withAllVerifiers() {
        return Arrays.<WinnerVerifier> asList(new DiagonalWinnerVerifier(),
                                              new HorizontalWinnerVerificer(),
                                              new VerticalWinnerVerifier());
    }

    public boolean theGameHasEnded() {
        return game != null && game.hasEnded();
    }

    public void putTheNextMarkAt(Point location) {
        assertThatGameHasStared();
        game.putAMarkAt(location, getPossibleNextMark());
        mark = game.getMarkAt(location);
    }

    public Mark getPossibleNextMark() {
        return mark.equals(CROSS) ? NOUGHT : CROSS;
    }

    public WinnerPoints getWinnerPoints() {
        return game.getWinnerPoints();
    }

    public boolean theGameHasDrawn() {
        return game != null && game.hasDrawn();
    }

    public Mark getLastMark() {
        return mark;
    }

    public Mark getMarkAt(Point location) {
        assertThatGameHasStared();
        return game.getMarkAt(location);
    }

    private void assertThatGameHasStared() {
        if (!theGameHasStarted())
            throw new TicTacToeGame.NotStarted();
    }

    public void stopTheGame() {
        game = null;
        mark = null;
    }

}
