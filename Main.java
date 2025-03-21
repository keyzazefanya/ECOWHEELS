import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InitialScreen initialScreen = new InitialScreen();
            initialScreen.setVisible(true);
        });
    }
}
