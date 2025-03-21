import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentPage extends JFrame {
    private JComboBox<String> paymentMethodComboBox;
    private JTextField proofField;
    private JButton finishPaymentButton;
    private String bicycleModel, ordererName, date, time, duration;

    public PaymentPage(String bicycleModel, String ordererName, String date, String time, String duration) {
        this.bicycleModel = bicycleModel;
        this.ordererName = ordererName;
        this.date = date;
        this.time = time;
        this.duration = duration;

        setTitle("Payment");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        finishPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finishPayment();
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel paymentMethodLabel = new JLabel("Payment Method:");
        paymentMethodLabel.setBounds(10, 20, 120, 25);
        panel.add(paymentMethodLabel);

        paymentMethodComboBox = new JComboBox<>(new String[]{"Credit Card", "m-Banking", "Digital Wallet"});
        paymentMethodComboBox.setBounds(140, 20, 200, 25);
        panel.add(paymentMethodComboBox);

        JLabel proofLabel = new JLabel("Upload Proof:");
        proofLabel.setBounds(10, 60, 120, 25);
        panel.add(proofLabel);

        proofField = new JTextField(20);
        proofField.setBounds(140, 60, 200, 25);
        panel.add(proofField);

        finishPaymentButton = new JButton("Finish Payment");
        finishPaymentButton.setBounds(10, 100, 150, 25);
        panel.add(finishPaymentButton);
    }

    private void finishPayment() {
        String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();
        String proof = proofField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE Bookings SET payment_method = ?, proof_of_payment = ?, payment_status = 'Paid' WHERE bicycle_model = ? AND orderer_name = ? AND date = ? AND time = ? AND duration = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, paymentMethod);
            preparedStatement.setString(2, proof);
            preparedStatement.setString(3, bicycleModel);
            preparedStatement.setString(4, ordererName);
            preparedStatement.setString(5, date);
            preparedStatement.setString(6, time);
            preparedStatement.setString(7, duration);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Payment successful! Here is your booking code: 12345");
            new ProfilePage().setVisible(true);
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error processing payment.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PaymentPage("Sample Bicycle", "John Doe", "2024-06-28", "07:00", "2 hours").setVisible(true);
            }
        });
    }
}
