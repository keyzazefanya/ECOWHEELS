import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingPage extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField durationField;
    private JButton proceedToPaymentButton;
    private String bicycleModel;

    public BookingPage(String bicycleModel) {
        this.bicycleModel = bicycleModel;
        setTitle("Booking");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        proceedToPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                proceedToPayment();
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Orderer's Name:");
        nameLabel.setBounds(10, 20, 120, 25);
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(140, 20, 200, 25);
        panel.add(nameField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(10, 50, 120, 25);
        panel.add(phoneLabel);

        phoneField = new JTextField(20);
        phoneField.setBounds(140, 50, 200, 25);
        panel.add(phoneField);

        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setBounds(10, 80, 120, 25);
        panel.add(dateLabel);

        dateField = new JTextField(20);
        dateField.setBounds(140, 80, 200, 25);
        panel.add(dateField);

        JLabel timeLabel = new JLabel("Select Time:");
        timeLabel.setBounds(10, 110, 120, 25);
        panel.add(timeLabel);

        timeField = new JTextField(20);
        timeField.setBounds(140, 110, 200, 25);
        panel.add(timeField);

        JLabel durationLabel = new JLabel("Select Duration:");
        durationLabel.setBounds(10, 140, 120, 25);
        panel.add(durationLabel);

        durationField = new JTextField(20);
        durationField.setBounds(140, 140, 200, 25);
        panel.add(durationField);

        proceedToPaymentButton = new JButton("Proceed to Payment");
        proceedToPaymentButton.setBounds(10, 180, 160, 25);
        panel.add(proceedToPaymentButton);
    }

    private void proceedToPayment() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        String duration = durationField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Bookings (bicycle_model, orderer_name, phone_number, date, time, duration) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bicycleModel);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, time);
            preparedStatement.setString(6, duration);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Booking successful! Proceed to payment.");
            new PaymentPage(bicycleModel, name, date, time, duration).setVisible(true);
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error processing booking.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookingPage("Sample Bicycle").setVisible(true);
            }
        });
    }
}
