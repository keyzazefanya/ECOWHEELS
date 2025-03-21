import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialScreen extends JFrame {
    private JButton getStartedButton;
    private JButton signInButton;

    public InitialScreen() {
        setTitle("Bicycle Rental App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getStartedButton = new JButton("Get Started");
        signInButton = new JButton("Sign In");

        getStartedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpForm().setVisible(true);
                dispose();
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignInForm().setVisible(true);
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(getStartedButton);
        panel.add(signInButton);
        add(panel);
    }
}
