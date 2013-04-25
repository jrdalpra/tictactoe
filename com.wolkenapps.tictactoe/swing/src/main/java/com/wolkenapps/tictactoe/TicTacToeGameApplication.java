package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Mark.NOUGHT;
import static com.wolkenapps.tictactoe.Point.*;
import static java.awt.BorderLayout.*;
import static java.util.Arrays.asList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.wolkenapps.tictactoe.ia.MarkPutter;
import com.wolkenapps.tictactoe.ia.MarkPuttersFactory;
import com.wolkenapps.tictactoe.ia.RobotPlayer;

public class TicTacToeGameApplication {

   @SuppressWarnings("serial")
   public class ButtonNotFound extends RuntimeException {
   }

   private static final String     POINT = Point.class.getName();

   private TicTacToeGameController controller;

   private JFrame                  frame;

   private JPanel                  centerPanel;

   private JPanel                  eastPanel;

   private JPanel                  southPanel;

   private JCheckBox               useComputerAsOpponentCheckBox;

   private JCheckBox               computerMustPlayFirstCheckBox;

   private List<JButton>           pointButtons;

   private JButton                 startGameButton;

   private JButton                 stopGameButton;

   private JLabel                  messageLabel;

   private RobotPlayer             robot;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               TicTacToeGameApplication window = new TicTacToeGameApplication();
               window.frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   public TicTacToeGameApplication() {
      initialize();
   }

   private void initialize() {
      controller = new TicTacToeGameController();
      initializeTheUserInterface();
   }

   private void initializeTheUserInterface() {
      initializeTheFrame();
      initializeTheCentralPanel();
      initializeTheEastPanel();
      initializeTheSouthPanel();
   }

   private void initializeTheEastPanel() {
      createTheEastPanel();
      createTheStartAndStopButtonPanel();
      createOptionsAtEastPanel();
   }

   private void createTheStartAndStopButtonPanel() {
      JPanel startAndStopButtonsPanel;
      eastPanel.add(startAndStopButtonsPanel = new JPanel(new BorderLayout()), SOUTH);
      startAndStopButtonsPanel.add(theStopButton(), NORTH);
      startAndStopButtonsPanel.add(theStartButton(), SOUTH);
   }

   private void createTheEastPanel() {
      frame.getContentPane().add(eastPanel = new JPanel(), EAST);
      eastPanel.setLayout(new BorderLayout());
   }

   private JButton theStartButton() {
      startGameButton = new JButton("Start Game");
      startGameButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            startTheGame();
         }
      });
      return startGameButton;
   }

   private JButton theStopButton() {
      stopGameButton = new JButton("Stop Game");
      stopGameButton.setEnabled(false);
      stopGameButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            stopTheGame();
         }
      });
      return stopGameButton;
   }

   protected void stopTheGame() {
      controller.stopTheGame();
      setEnabledOptions(true);
      setEnabledAllPointButtons(false);
      enableDisableStartAndStopButton();
   }

   private void createOptionsAtEastPanel() {
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      panel.add(theUseComputerAsOpponentCheckBox());
      panel.add(theComputerMustPlayFirstCheckBox());
      eastPanel.add(panel, WEST);
   }

   private JCheckBox theUseComputerAsOpponentCheckBox() {
      useComputerAsOpponentCheckBox = new JCheckBox("Computer as Opponent");
      useComputerAsOpponentCheckBox.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            handleSelectionOfUseComputerAsOpponentCheckBox();
         }
      });
      return useComputerAsOpponentCheckBox;
   }

   private JCheckBox theComputerMustPlayFirstCheckBox() {
      computerMustPlayFirstCheckBox = new JCheckBox("Computer Play First");
      computerMustPlayFirstCheckBox.setEnabled(false);
      return computerMustPlayFirstCheckBox;
   }

   private void initializeTheCentralPanel() {
      frame.getContentPane().add(centerPanel = new JPanel(new GridLayout(3, 3, 0, 0)), CENTER);
      centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      pointButtons = asList(aButtonAt(TOP_LEFT),
                            aButtonAt(TOP_CENTER),
                            aButtonAt(TOP_RIGHT),
                            aButtonAt(MIDDLE_LEFT),
                            aButtonAt(MIDDLE_CENTER),
                            aButtonAt(MIDDLE_RIGHT),
                            aButtonAt(BOTTOM_LEFT),
                            aButtonAt(BOTTOM_CENTER),
                            aButtonAt(BOTTOM_RIGHT));
   }

   private JButton aButtonAt(final Point point) {
      final JButton button = new JButton("");
      button.setEnabled(false);
      button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      button.putClientProperty(POINT, point);
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            playAt(point, button);
         }
      });
      centerPanel.add(button);
      return button;
   }

   private void initializeTheFrame() {
      frame = new JFrame();
      frame.setBounds(100, 100, 450, 300);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(new BorderLayout(0, 0));

   }

   private void initializeTheSouthPanel() {
      frame.getContentPane().add(southPanel = new JPanel(), BorderLayout.SOUTH);
      southPanel.add(messageLabel = new JLabel(""));
   }

   private void startTheGame() {
      controller.startsTheGame();
      setEnabledOptionsAndCenterPanel(controller.theGameHasStarted());
      enableDisableStartAndStopButton();
      clearAllPointButtons();
      showMessage(null);
      createTheRobot();
      tellToRobotMakeTheFirstMove();
   }

   private void createTheRobot() {
      robot = new RobotPlayer(controller, allAvaiableMarkPutters());
   }

   private Iterable<MarkPutter> allAvaiableMarkPutters() {
      return MarkPuttersFactory.getAllPossibleMarkPutters();
   }

   private void tellToRobotMakeTheFirstMove() {
      if (!theRobotMustMakeTheFirstMove())
         return;
      tellToRobotMakeSomeMove();
   }

   private void tellToRobotMakeSomeMove() {
      if (!isTheRobotTheOpponent() || controller.theGameHasEnded())
         return;
      robot.putSomeMarkInTheGame();
      printTheMarkAt(findAButtonAt(robot.getLastPoint()));
   }

   private boolean theRobotMustMakeTheFirstMove() {
      return computerMustPlayFirstCheckBox.isSelected();
   }

   private boolean isTheRobotTheOpponent() {
      return useComputerAsOpponentCheckBox.isSelected();
   }

   private void setEnabledOptionsAndCenterPanel(boolean theGameHasStarted) {
      setEnabledAllPointButtons(theGameHasStarted);
      setEnabledOptions(!theGameHasStarted);
   }

   private void enableDisableStartAndStopButton() {
      startGameButton.setEnabled(!controller.theGameHasStarted());
      stopGameButton.setEnabled(controller.theGameHasStarted());
   }

   private void setEnabledOptions(boolean enabled) {
      useComputerAsOpponentCheckBox.setEnabled(enabled);
      computerMustPlayFirstCheckBox.setEnabled(enabled && useComputerAsOpponentCheckBox.isSelected());
   }

   private void clearAllPointButtons() {
      for (JButton button : pointButtons)
         clearOnPointButton(button);
   }

   private void clearOnPointButton(JButton button) {
      button.setText("");
      button.setBackground(null);
   }

   private void setEnabledAllPointButtons(boolean enabled) {
      for (JButton button : pointButtons)
         button.setEnabled(enabled);
   }

   private void playAt(Point location, JButton pressed) {
      putTheMarkAt(location);
      printTheMarkAt(pressed);
      tellToRobotMakeSomeMove();
      ifIsPossibleShowTheEndedGameInfoAndRestoreUI();
   }

   private void ifIsPossibleShowTheEndedGameInfoAndRestoreUI() {
      if (!controller.theGameHasEnded())
         return;
      showToTheUsersTheWinnerPath();
      showToTheUsersIfThereIsADraw();
      endTheGame();
   }

   private void endTheGame() {
      setEnabledAllPointButtons(false);
      setEnabledOptions(true);
      enableDisableStartAndStopButton();
   }

   private void showToTheUsersIfThereIsADraw() {
      if (!controller.theGameHasDrawn())
         return;
      setEnabledAllPointButtons(false);
      showMessage("The Game Has Drawn");
   }

   private void showToTheUsersTheWinnerPath() {
      if (controller.theGameHasDrawn())
         return;
      try {
         for (Point point : controller.getWinnerPoints())
            findAButtonAt(point).setBackground(Color.RED);
         showMessage("The GAME has a winner");
         aa
      } catch (TicTacToeGame.HasNoWinner noWinner) {
      } catch (TicTacToeGameApplication.ButtonNotFound notFound) {
      }
   }

   public JButton findAButtonAt(Point point) {
      for (JButton button : pointButtons)
         if (point.equals(button.getClientProperty(POINT)))
            return button;
      throw new TicTacToeGameApplication.ButtonNotFound();
   }

   private void printTheMarkAt(JButton pressed) {
      pressed.setText(controller.getLastMark().equals(NOUGHT) ? "0" : "X");
      pressed.setBackground(controller.getLastMark().equals(NOUGHT) ? Color.GREEN : Color.YELLOW);
   }

   private void putTheMarkAt(Point location) {
      try {
         controller.putTheNextMarkAt(location);
      } catch (TicTacToeGame.CannotPlayTwiceInARow e) {
         showMessage("You cannot play twice in a row");
      } catch (TicTacToeGame.SpaceAlreadyFilled e) {
         showMessage("This point is already marked");
      }
   }

   private void showMessage(String message) {
      messageLabel.setText(message);
   }

   private void handleSelectionOfUseComputerAsOpponentCheckBox() {
      computerMustPlayFirstCheckBox.setSelected(false);
      computerMustPlayFirstCheckBox.setEnabled(useComputerAsOpponentCheckBox.isSelected());
   }
}
