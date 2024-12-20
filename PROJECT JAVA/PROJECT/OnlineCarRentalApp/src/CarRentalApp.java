import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarRentalApp {
    private CarService carService;
    private JComboBox<String> makeComboBox;
    private JComboBox<String> modelComboBox;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> priceComboBox;
    private DefaultListModel<Car> bookedListModel;
    private DefaultListModel<Car> unbookedListModel;
    private JList<Car> bookedCarList;
    private JList<Car> unbookedCarList;

    public CarRentalApp() {
        carService = new CarService();

        JFrame frame = new JFrame("Car Rental Service");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        // Set background color
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 248, 255)); // Alice Blue
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);

        // Filter panel with light background
        JPanel filterPanel = new JPanel(new GridLayout(2, 2));
        filterPanel.setBackground(new Color(173, 216, 230)); // Light Blue
        makeComboBox = new JComboBox<>(new String[]{"Any", "Toyota", "Honda", "Ford", "Chevrolet", "Nissan"});
        modelComboBox = new JComboBox<>(new String[]{"Any", "Camry", "Civic", "Focus", "Malibu", "Altima"});
        yearComboBox = new JComboBox<>(new String[]{"Any", "2017", "2018", "2019", "2020", "2021", "2022"});
        priceComboBox = new JComboBox<>(new String[]{"Any", "40", "45", "50", "55", "60"});

        filterPanel.add(new JLabel("Make:"));
        filterPanel.add(makeComboBox);
        filterPanel.add(new JLabel("Model:"));
        filterPanel.add(modelComboBox);
        filterPanel.add(new JLabel("Year:"));
        filterPanel.add(yearComboBox);
        filterPanel.add(new JLabel("Price (<=):"));
        filterPanel.add(priceComboBox);

        mainPanel.add(filterPanel, BorderLayout.NORTH);

        bookedListModel = new DefaultListModel<>();
        unbookedListModel = new DefaultListModel<>();
        bookedCarList = new JList<>(bookedListModel);
        unbookedCarList = new JList<>(unbookedListModel);
        bookedCarList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        unbookedCarList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create list panels with titles and light background
        JPanel listPanel = new JPanel(new GridLayout(1, 2));
        listPanel.setBackground(new Color(240, 248, 255)); // Alice Blue

        // Booked Cars Panel
        JPanel bookedPanel = new JPanel(new BorderLayout());
        bookedPanel.setBackground(new Color(224, 255, 255)); // Light Cyan
        JLabel bookedLabel = new JLabel("Booked Cars", SwingConstants.CENTER);
        bookedLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size
        bookedLabel.setForeground(new Color(0, 102, 204)); // Darker Blue
        bookedPanel.add(bookedLabel, BorderLayout.NORTH);
        bookedCarList.setBackground(new Color(255, 228, 225)); // Misty Rose for booked cars
        bookedPanel.add(new JScrollPane(bookedCarList), BorderLayout.CENTER);
        listPanel.add(bookedPanel);

        // Available Cars Panel
        JPanel unbookedPanel = new JPanel(new BorderLayout());
        unbookedPanel.setBackground(new Color(224, 255, 255)); // Light Cyan
        JLabel unbookedLabel = new JLabel("Available Cars", SwingConstants.CENTER);
        unbookedLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size
        unbookedLabel.setForeground(new Color(0, 102, 204)); // Darker Blue
        unbookedPanel.add(unbookedLabel, BorderLayout.NORTH);
        unbookedCarList.setBackground(new Color(240, 255, 240)); // Honeydew for available cars
        unbookedPanel.add(new JScrollPane(unbookedCarList), BorderLayout.CENTER);
        listPanel.add(unbookedPanel);

        mainPanel.add(listPanel, BorderLayout.CENTER);

        // Button panel with custom styles
        JPanel buttonPanel = new JPanel();
        JButton searchButton = new JButton("Search Cars");
        JButton bookButton = new JButton("Book Car");
        JButton unbookButton = new JButton("Unbook Car");

        // Button styles
        styleButton(searchButton);
        styleButton(bookButton);
        styleButton(unbookButton);

        buttonPanel.add(searchButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(unbookButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Search button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMake = (String) makeComboBox.getSelectedItem();
                String selectedModel = (String) modelComboBox.getSelectedItem();
                Integer selectedYear = yearComboBox.getSelectedIndex() == 0 ? null : Integer.valueOf((String) yearComboBox.getSelectedItem());
                Double selectedPrice = priceComboBox.getSelectedIndex() == 0 ? null : Double.valueOf((String) priceComboBox.getSelectedItem());

                List<Car> availableCars = carService.searchCars(
                        selectedMake.equals("Any") ? null : selectedMake,
                        selectedModel.equals("Any") ? null : selectedModel,
                        selectedYear, selectedPrice);

                unbookedListModel.clear();
                for (Car car : availableCars) {
                    unbookedListModel.addElement(car);
                }
            }
        });

        // Book button action
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = unbookedCarList.getSelectedValue();
                if (selectedCar != null) {
                    String message = carService.bookCar(selectedCar);
                    if (!message.contains("The car is already booked")) {
                        bookedListModel.addElement(selectedCar);
                    }
                    JOptionPane.showMessageDialog(frame, message);
                }
            }
        });

        // Unbook button action
        unbookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = bookedCarList.getSelectedValue();
                if (selectedCar != null) {
                    String message = carService.unbookCar(selectedCar);
                    if (!message.contains("The car is not booked")) {
                        bookedListModel.removeElement(selectedCar);
                    }
                    JOptionPane.showMessageDialog(frame, message);
                }
            }
        });

        frame.setVisible(true);
    }

    // Style method for buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public static void main(String[] args) {
        new CarRentalApp();
    }
}
