import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationPage extends JFrame {
    private JTextArea notificationArea;

    public NotificationPage() {
        setTitle("Notifications");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
        loadNotifications();
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        notificationArea = new JTextArea();
        notificationArea.setBounds(10, 10, 360, 240);
        notificationArea.setEditable(false);
        panel.add(notificationArea);
    }

    private void loadNotifications() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT message FROM Notifications WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                notificationArea.append(resultSet.getString("message") + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading notifications.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NotificationPage().setVisible(true);
            }
        });
    }
}
