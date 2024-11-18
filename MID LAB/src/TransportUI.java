import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TransportUI extends JFrame {
    private Student student;
    private FriendObserver friend1;
    private FriendObserver friend2;
    private NotificationService service;
    private JTextArea notificationArea;

    public TransportUI() {
        // Initialize domain objects
        student = new Student("Student");
        friend1 = new FriendObserver("JALAL KHAN", 10); // Friend1 takes 10 minutes to reach
        friend2 = new FriendObserver("HAIDER REHMAN", 15); // Friend2 takes 15 minutes to reach
        service = new NotificationService();

        // Register friends
        service.registerObservers(student, Arrays.asList(friend1, friend2));

        // Setup JFrame
        setTitle("Local Transport Notification System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a main panel with a background color
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));  // Light blue background

        // Create a header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));  // Steel blue header
        JLabel titleLabel = new JLabel("Local Transport Notification System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Create a button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));  // Centered with spacing

        // Buttons
        JButton leaveButton = new JButton("Student Leaves");
        styleButton(leaveButton);  // Apply styling
        buttonPanel.add(leaveButton);

        // TextArea for notifications
        notificationArea = new JTextArea(10, 45);
        notificationArea.setEditable(false);
        notificationArea.setLineWrap(true);
        notificationArea.setWrapStyleWord(true);
        notificationArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(notificationArea);

        // Action listener for "Student Leaves" button
        leaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notificationArea.setText("");  // Clear previous notifications
                student.leave();  // Student leaves and records the time
                String departureTime = student.getDepartureTime();

                // Show notifications for each friend
                notificationArea.append(friend1.getName() + " has been notified that i have  to reach in " + friend1.getEstimatedTimeToReach() + " minutes.\n");
                notificationArea.append(friend2.getName() + " has been notified to reach in " + friend2.getEstimatedTimeToReach() + " minutes.\n");
            }
        });

        // Assemble the main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Add main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
    }

    // Helper method to style buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 149, 237));  // Cornflower blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    public static void main(String[] args) {
        new TransportUI();
    }
}
