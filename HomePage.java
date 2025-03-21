import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePage extends JFrame {
    private JLabel profileLabel;
    private JButton profileButton;
    private JButton contactUsButton;
    private JButton notificationButton;
    private JComboBox<String> locationComboBox;
    private JButton manualBicycleButton;
    private JButton electricBicycleButton;

    public HomePage() {
        setTitle("Home");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        profileButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              new ProfilePage().setVisible(true);
              dispose();
          }
      });

        contactUsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ContactUsPage().setVisible(true);
                dispose();
            }
        });

        notificationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NotificationPage().setVisible(true);
                dispose();
            }
        });

        manualBicycleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManualBicyclePage((String) locationComboBox.getSelectedItem()).setVisible(true);
                dispose();
            }
        });

        electricBicycleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ElectricBicyclePage((String) locationComboBox.getSelectedItem()).setVisible(true);
                dispose();
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        profileLabel = new JLabel("Profile: User");
        profileLabel.setBounds(10, 20, 200, 25);
        panel.add(profileLabel);

        profileButton = new JButton("Profile");
        profileButton.setBounds(10, 60, 150, 25);
        panel.add(profileButton);

        contactUsButton = new JButton("Contact Us");
        contactUsButton.setBounds(10, 60, 150, 25);
        panel.add(contactUsButton);

        notificationButton = new JButton("Notification");
        notificationButton.setBounds(200, 60, 150, 25);
        panel.add(notificationButton);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(10, 100, 80, 25);
        panel.add(locationLabel);

        locationComboBox = new JComboBox<>(new String[]{"Area 1", "Area 2", "Area 3"});
        locationComboBox.setBounds(100, 100, 165, 25);
        panel.add(locationComboBox);

        manualBicycleButton = new JButton("Manual Bicycle");
        manualBicycleButton.setBounds(10, 140, 150, 25);
        panel.add(manualBicycleButton);

        electricBicycleButton = new JButton("Electric Bicycle");
        electricBicycleButton.setBounds(200, 140, 150, 25);
        panel.add(electricBicycleButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }
}
