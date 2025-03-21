import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfilePage extends JFrame {
    private JLabel profileLabel;
    private JTextArea bookingInfoArea;

    public ProfilePage() {
        setTitle("Profile");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
        loadProfileInfo();
        loadBookingInfo();
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        profileLabel = new JLabel("Profile: User");
        profileLabel.setBounds(10, 20, 200, 25);
        panel.add(profileLabel);

        bookingInfoArea = new JTextArea();
        bookingInfoArea.setBounds(10, 60, 360, 300);
        bookingInfoArea.setEditable(false);
        panel.add(bookingInfoArea);
    }

    private void loadProfileInfo() {
        // For now, we will assume a static user profile
        profileLabel.setText("Profile: John Doe, john.doe@example.com");
    }

    private void loadBookingInfo() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Bookings WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);  // Assuming user_id is 1 for now
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookingInfoArea.append("Area: " + resultSet.getString("location_area") + "\n");
                bookingInfoArea.append("Bicycle Type: " + resultSet.getString("bicycle_type") + "\n");
                bookingInfoArea.append("Bicycle Model: " + resultSet.getString("bicycle_model") + "\n");
                bookingInfoArea.append("Booking Time: " + resultSet.getString("time") + "\n");
                bookingInfoArea.append("Booking Date: " + resultSet.getString("date") + "\n");
                bookingInfoArea.append("Duration: " + resultSet.getString("duration") + "\n");
                bookingInfoArea.append("Booking Code: " + resultSet.getString("booking_code") + "\n");
                bookingInfoArea.append("Payment Status: " + resultSet.getString("payment_status") + "\n");
                bookingInfoArea.append("\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading booking information.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProfilePage().setVisible(true);
            }
        });
    }
}
