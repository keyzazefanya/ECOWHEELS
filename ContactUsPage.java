import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactUsPage extends JFrame {
    private JTextField whatsappField;
    private JTextField emailField;
    private JButton copyWhatsappButton;
    private JButton copyEmailButton;

    public ContactUsPage() {
        setTitle("Contact Us");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel whatsappLabel = new JLabel("WhatsApp:");
        whatsappLabel.setBounds(10, 20, 80, 25);
        panel.add(whatsappLabel);

        whatsappField = new JTextField("1234567890");
        whatsappField.setBounds(100, 20, 165, 25);
        whatsappField.setEditable(false);
        panel.add(whatsappField);

        copyWhatsappButton = new JButton("Copy");
        copyWhatsappButton.setBounds(10, 50, 80, 25);
        panel.add(copyWhatsappButton);

        copyWhatsappButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                whatsappField.selectAll();
                whatsappField.copy();
                JOptionPane.showMessageDialog(null, "WhatsApp number copied to clipboard");
            }
        });

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 80, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField("contact@ecowheels.com");
        emailField.setBounds(100, 80, 165, 25);
        emailField.setEditable(false);
        panel.add(emailField);

        copyEmailButton = new JButton("Copy");
        copyEmailButton.setBounds(10, 110, 80, 25);
        panel.add(copyEmailButton);

        copyEmailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emailField.selectAll();
                emailField.copy();
                JOptionPane.showMessageDialog(null, "Email address copied to clipboard");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ContactUsPage().setVisible(true);
            }
        });
    }
}
