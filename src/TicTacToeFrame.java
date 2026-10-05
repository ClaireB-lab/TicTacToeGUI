import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {
    private final int ROW = 3, COL = 3;
    private TicTacToeTile[][] boardButtons = new TicTacToeTile[ROW][COL];
    private String player = "X";
    private int moveCnt = 0;

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

    private void endGame(String msg){
        int choice = JOptionPane.showConfirmDialog(this, msg + "\nPlay again?", "Game over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            clearBoard();
        } else {
            System.exit(0);
        }
    }

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
