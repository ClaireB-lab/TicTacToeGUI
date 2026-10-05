import javax.swing.SwingUtilities;

/// Claire Battelle
/// October 5, 2026
/// Runs the tic tac toe program
public class TicTacToeRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeFrame frame = new TicTacToeFrame();
            frame.setVisible(true);
        });
    }
}
