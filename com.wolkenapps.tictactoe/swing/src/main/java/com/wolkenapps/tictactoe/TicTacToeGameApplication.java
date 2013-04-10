package com.wolkenapps.tictactoe;

import static com.wolkenapps.tictactoe.Mark.NOUGHT;
import static com.wolkenapps.tictactoe.Point.*;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TicTacToeGameApplication {

   private static final String     POINT = Point.class.getName();
   private JFrame                  frame;
   private List<JButton>           pointButtons;
   private JPanel                  centerPanel;
   private TicTacToeGameController controller;
   private Mark                    putted;
   private JPanel                  eastPanel;
   private JCheckBox useComputerAOpponent;
   private JPanel panel;
   private JCheckBox computerMustPlayFirst;

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

   /**
    * Create the application.
    */
   public TicTacToeGameApplication() {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      initializeTheUserInterface();
      setEnabledOnCenterPanelAndPointButtons(false);
      controller = new TicTacToeGameController();
   }

   private void initializeTheUserInterface() {
      initializeTheFrame();
      initializeTheCentralPanel();
      initializeTheEastPanel();
   }

   private void initializeTheEastPanel() {
      frame.getContentPane().add(eastPanel = new JPanel(), BorderLayout.EAST);
      createTheStartButton();
   }

   private void createTheStartButton() {
      JButton startGameButton = new JButton("Start Game");
      startGameButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            startTheGame();
         }
      });
      eastPanel.setLayout(new BorderLayout(0, 0));
      eastPanel.add(startGameButton, BorderLayout.SOUTH);
      {
         panel = new JPanel();
         eastPanel.add(panel, BorderLayout.WEST);
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         {
            useComputerAOpponent = new JCheckBox("Computer as Opponent");
            panel.add(useComputerAOpponent);
         }
         {
            computerMustPlayFirst = new JCheckBox("Computer Play First");
            panel.add(computerMustPlayFirst);
         }
      }
   }

   private JButton theBottomRightButton() {
      return createAButtonAt(BOTTOM_RIGHT);
   }

   private JButton theBottomCenterButton() {
      return createAButtonAt(BOTTOM_CENTER);
   }

   private JButton theBottomLeftButton() {
      return createAButtonAt(BOTTOM_LEFT);
   }

   private JButton theMiddleRightButton() {
      return createAButtonAt(MIDDLE_RIGHT);
   }

   private JButton theMiddleCenterButton() {
      return createAButtonAt(MIDDLE_CENTER);
   }

   private JButton theMiddleLeftButton() {
      return createAButtonAt(MIDDLE_LEFT);
   }

   private JButton theTopRightButton() {
      return createAButtonAt(TOP_RIGHT);
   }

   private JButton theTopCenterButton() {
      return createAButtonAt(TOP_CENTER);
   }

   private JButton theTopLeftButton() {
      return createAButtonAt(TOP_LEFT);
   }

   private JButton createAButtonAt(final Point point) {
      final JButton button = new JButton("");
      button.putClientProperty(POINT, point);
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            playAt(point, button);
         }
      });
      centerPanel.add(button);
      return button;
   }

   private void initializeTheCentralPanel() {
      frame.getContentPane().add(centerPanel = new JPanel(), BorderLayout.CENTER);
      centerPanel.setLayout(new GridLayout(3, 3, 0, 0));
      pointButtons = Arrays.asList(theTopLeftButton(),
                                   theTopCenterButton(),
                                   theTopRightButton(),
                                   theMiddleLeftButton(),
                                   theMiddleCenterButton(),
                                   theMiddleRightButton(),
                                   theBottomLeftButton(),
                                   theBottomCenterButton(),
                                   theBottomRightButton());
   }

   private void initializeTheFrame() {
      frame = new JFrame();
      frame.setBounds(100, 100, 450, 300);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(new BorderLayout(0, 0));
   }

   private void startTheGame() {
      controller.startsTheGame();
      setEnabledOnCenterPanelAndPointButtons(controller.theGameHasStarted());
      clearAllPointButtons();
   }

   private void clearAllPointButtons() {
      for (JButton button : pointButtons) {
         button.setText("");
         button.setBackground(null);
      }
   }

   private void setEnabledOnCenterPanelAndPointButtons(boolean enabled) {
      for (JButton button : pointButtons)
         button.setEnabled(enabled);
      centerPanel.setEnabled(enabled);
   }

   private void playAt(Point location, JButton pressed) {
      putTheMarkAt(location);
      printTheMarkAt(pressed);
      checkIfTheGameHasEnded();
   }

   private void checkIfTheGameHasEnded() {
      if (controller.theGameHasEnded()) {
         showToTheUsersTheWinnerPath();
         showToTheUsersIfThereIsADraw();
         endTheGame();
      }
   }

   private void endTheGame() {
      setEnabledOnCenterPanelAndPointButtons(false);
   }

   private void showToTheUsersIfThereIsADraw() {
      if (!controller.theGameHasDrawn())
         return;
      setEnabledOnCenterPanelAndPointButtons(false);
      showMessage("The Game Has Drawn");
   }

   private void showMessage(String message) {
      showMessageDialog(frame, message);
   }

   private void showToTheUsersTheWinnerPath() {
      if (controller.theGameHasDrawn())
         return;
      try {
         for (Point point : controller.getWinnerPoints())
            for (JButton button : pointButtons)
               if (point.equals(button.getClientProperty(POINT)))
                  button.setBackground(Color.RED);
      } catch (TicTacToeGame.HasNoWinner noWinner) {
      }
   }

   private void printTheMarkAt(JButton pressed) {
      pressed.setText(putted.equals(NOUGHT) ? "0" : "X");
      pressed.setEnabled(false);
   }

   private void putTheMarkAt(Point location) {
      try {
         putted = controller.putTheNextMarkAt(location);
      } catch (TicTacToeGame.CannotPlayTwiceInARow e) {
         showMessage("You cannot play twice in a row");
      } catch (TicTacToeGame.SpaceAlreadyFilled e) {
         showMessage("This point is already filled");
      }
   }

}
