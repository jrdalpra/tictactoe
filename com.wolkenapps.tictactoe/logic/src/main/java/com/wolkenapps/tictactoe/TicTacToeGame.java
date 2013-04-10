package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Mark.BLANK;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TicTacToeGame {

   @SuppressWarnings("serial")
   public static class NotStarted extends RuntimeException {
   }

   public class HasNoWinner extends RuntimeException {
      private static final long serialVersionUID = 1L;
   }

   public class HasNoVerifiers extends RuntimeException {
      private static final long serialVersionUID = 1L;
   }

   public static class SpaceAlreadyFilled extends RuntimeException {
      private static final long serialVersionUID = 1L;
   }

   public static class CannotPlayTwiceInARow extends RuntimeException {
      private static final long serialVersionUID = 1L;
   }

   public static class AlreadHasAWinner extends RuntimeException {
      private static final long serialVersionUID = 1L;
   }

   private Map<Point, Mark>         spaces         = new HashMap<Point, Mark>();
   private Mark                     lastMarkPlaced = Mark.BLANK;
   private boolean                  hasAWinner     = false;
   private Iterable<WinnerVerifier> verifiers;

   public TicTacToeGame(Iterable<WinnerVerifier> verifiers) {
      this.verifiers = verifiers;
      fillAllSpacesWithDefaultMark();
   }

   private void fillAllSpacesWithDefaultMark() {
      for (int row = 0; row < 3; row++)
         for (int column = 0; column < 3; column++)
            spaces.put(Point.at(row, column), Mark.BLANK);
   }

   public int getSpacesNumber() {
      return spaces.size();
   }

   public int getSpacesNumberWithMark(Mark markToCount) {
      int count = 0;
      for (Entry<Point, Mark> entry : spaces.entrySet())
         if (entry.getValue().equals(markToCount))
            count++;
      return count;
   }

   public void putAMarkAt(Point atLocation, Mark markToPut) {
      assertThatIsNotAlreadyMarked(atLocation);
      assertThatIsNotTryingToPlayTwiceInARow(markToPut);
      assertThatTheresNoWinner();
      assertThatHasVerifiers();
      spaces.put(atLocation, markToPut);
      lastMarkPlaced = markToPut;
      confirmsIfThereIsAWinner();
   }

   public Mark getMarkAt(Point atLocation) {
      return spaces.get(atLocation);
   }

   public boolean hasAWinner() {
      return hasAWinner;
   }

   private void assertThatHasVerifiers() {
      if (verifiers == null || !verifiers.iterator().hasNext())
         throw new TicTacToeGame.HasNoVerifiers();
   }

   private void assertThatTheresNoWinner() {
      if (hasAWinner())
         throw new AlreadHasAWinner();
   }

   private void confirmsIfThereIsAWinner() {
      for (WinnerVerifier verifier : verifiers)
         if (verifier.confirmsThatThereIsWinner(this)) {
            this.hasAWinner = true;
            return;
         }
   }

   private void assertThatIsNotTryingToPlayTwiceInARow(Mark mark) {
      if (lastMarkPlaced.equals(mark))
         throw new CannotPlayTwiceInARow();
   }

   private void assertThatIsNotAlreadyMarked(Point atLocation) {
      if (!spaces.get(atLocation).equals(Mark.BLANK))
         throw new SpaceAlreadyFilled();
   }

   public WinnerPoints getWinnerPoints() {
      assertThatTheresAWinner();
      for (WinnerVerifier verifier : verifiers) {
         try {
            return verifier.getWinnerPointsAt(this);
         } catch (WinnerPoints.NotFound notFound) {
         }
      }
      throw new WinnerPoints.NotFound();
   }

   private void assertThatTheresAWinner() {
      if (!hasAWinner())
         throw new TicTacToeGame.HasNoWinner();
   }

   public boolean hasEnded() {
      return hasAWinner() || hasDrawn();
   }

   public boolean hasDrawn() {
      return !hasAWinner() && getSpacesNumberWithMark(BLANK) == 0;
   }

}
