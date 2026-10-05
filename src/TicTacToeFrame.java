import javax.swing.*;
import java.awt.*;

/// Claire Battelle
/// October 5, 2026
/// Creates the GUI for the Tic Tac Toe game
public class TicTacToeFrame extends JFrame {
    private final int ROW = 3, COL = 3;
    private TicTacToeTile[][] boardButtons = new TicTacToeTile[ROW][COL];
    private String player = "X";
    private int moveCnt = 0;

    /// sets the title
    /// creates the 9 tiles that make up the board
    /// adds a quit button
    /// sets the size of the board
    /// makes sure board is clear when game starts
    public TicTacToeFrame(){
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(ROW, COL));
        for (int r = 0; r < ROW; r++){
            for (int c = 0; c < COL; c++){
                TicTacToeTile tile = new TicTacToeTile(r, c);
                tile.setFont(new Font("Arial", Font.BOLD, 48));
                tile.addActionListener(e -> handleMove((TicTacToeTile) e.getSource()));
                boardButtons[r][c] = tile;
                boardPanel.add(tile);
            }
        }

        JButton quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        quitBtn.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Quit game?", "Quit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });

        add(boardPanel, BorderLayout.CENTER);
        add(quitBtn, BorderLayout.SOUTH);
        setSize(400, 450);
        setLocationRelativeTo(null);
        clearBoard();
    }

    /// tells what happens when a player makes a move ie invalid move, good move etc
    /// tells when player wins
    private void handleMove(TicTacToeTile tile) {
        int r = tile.getRow(), c = tile.getCol();

        if (!TicTacToe.isValidMove(r, c)) {
            JOptionPane.showMessageDialog(this, "Illegal move!", "Invalid", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TicTacToe.board[r][c] = player;
        tile.setText(player);
        moveCnt++;

        if (moveCnt >= 5 && TicTacToe.isWin(player)) {
            endGame("Player " + player + " wins!");
        } else if (moveCnt >= 7 && TicTacToe.isTie()) {
            endGame("It's a tie!");
        } else {
            player = player.equals("X") ? "O" : "X";
        }
    }

    /// When game is over, prompts user to play another game or quit
    private void endGame(String msg){
        int choice = JOptionPane.showConfirmDialog(this, msg + "\nPlay again?", "Game over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            clearBoard();
        } else {
            System.exit(0);
        }
    }

    /// it says what a clear board is aka it has no moves on the tiles
    /// this is used for when the game starts
    private void clearBoard(){
        moveCnt = 0;
        player = "X";
        TicTacToe.clearBoard();
        for (int r = 0; r < ROW; r++){
            for (int c = 0; c < COL; c++){
                boardButtons[r][c].setText(" ");
            }
        }
    }


}
