import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame
{
    private TicTacToeButton[][] buttons;
    private char currentPlayer;

    public TicTacToeFrame()
    {
        setTitle("Tic Tac Toe");
        setSize(800, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        buttons = new TicTacToeButton[3][3];
        currentPlayer = 'X';


        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                buttons[row][col] = new TicTacToeButton(row, col);
                buttons[row][col].addActionListener(new ButtonClickListener());
                boardPanel.add(buttons[row][col]);
            }
        }


        add(boardPanel, BorderLayout.CENTER);


        JPanel quitPanel = new JPanel();
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        quitPanel.add(quitButton);


        add(quitPanel, BorderLayout.SOUTH);
    }

    private class ButtonClickListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            TicTacToeButton button = (TicTacToeButton) e.getSource();
            if (button.isEmpty())
            {
                button.setPlayer(currentPlayer);
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                checkGameState();
            } else
            {
                JOptionPane.showMessageDialog(TicTacToeFrame.this, "Invalid move!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void checkGameState() {
        if (checkForWin()) {
            JOptionPane.showMessageDialog(this, "Player " + (currentPlayer == 'X' ? 'O' : 'X') + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
            return;
        }


        if (checkForTie()) {
            JOptionPane.showMessageDialog(this, "The game is a tie!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }
    }

    private boolean checkForWin()
    {

        for (int i = 0; i < 3; i++)
        {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][1].getText().equals(buttons[i][2].getText()) && !buttons[i][0].isEmpty())
            {
                return true;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && buttons[1][i].getText().equals(buttons[2][i].getText()) && !buttons[0][i].isEmpty())
            {
                return true;
            }
        }


        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].isEmpty())
        {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText()) && !buttons[0][2].isEmpty())
        {
            return true;
        }

        return false;
    }

    private boolean checkForTie()
    {
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                if (buttons[row][col].isEmpty())
                {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame()
    {
        currentPlayer = 'X';
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                buttons[row][col].setPlayer(' ');
            }
        }
    }

}
