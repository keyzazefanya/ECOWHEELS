import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class ElectricBicyclePage extends JFrame {
    public ElectricBicyclePage(String location) {
        setTitle("Electric Bicycle");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT model, available, image_url FROM Bicycles WHERE type='Electric' AND location_area=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, location);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String model = rs.getString("model");
                boolean available = rs.getBoolean("available");
                String imageUrl = rs.getString("image_url");

                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

                JLabel imageLabel = new JLabel();
                ImageIcon imageIcon = new ImageIcon(imageUrl);
                Image image = imageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(image));
                card.add(imageLabel);

                JLabel modelLabel = new JLabel("Model: " + model);
                card.add(modelLabel);

                JLabel statusLabel = new JLabel("Available: " + (available ? "Yes" : "No"));
                card.add(statusLabel);

                if (available) {
                    JButton bookButton = new JButton("Book Now");
                    bookButton.addActionListener(e -> {
                        int option = JOptionPane.showConfirmDialog(null, "Want To Book This Bicycle?", "Book Bicycle", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            new BookingPage(model).setVisible(true);
                            dispose();
                        }
                    });
                    card.add(bookButton);
                }

                add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManualBicyclePage("Area 1").setVisible(true));
    }
}
