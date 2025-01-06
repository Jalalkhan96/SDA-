import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User user = new User("jalalkhan", "111");
        System.out.println("Enter username:");
        String inputUsername = scanner.nextLine();
        System.out.println("Enter password:");
        String inputPassword = scanner.nextLine();

        if (user.authenticate(inputUsername, inputPassword)) {
            System.out.println("User authenticated successfully.");
        } else {
            System.out.println("Authentication failed.");
            return;
        }

        SmartHomeHub smartHomeHub = new SmartHomeHub();
        smartHomeHub.establishConnection();

        if (smartHomeHub.isConnectionEstablished()) {
            Connection connection = new Connection("Living Room Light", "Sensor123");
            connection.connectToSensor();

            SmartLight smartLight = new SmartLight();
            MobileApp mobileApp = new MobileApp(smartLight);

            System.out.println("Would you like to turn the light ON or OFF? (Type 'ON' or 'OFF')");
            String userAction = scanner.nextLine().trim().toUpperCase();

            if ("ON".equals(userAction)) {
                mobileApp.setState(true);

                System.out.println("Set the intensity level of the light (1 to 10):");
                int intensityLevel = scanner.nextInt();
                if (intensityLevel >= 1 && intensityLevel <= 10) {
                    mobileApp.setIntensityLevel(intensityLevel);
                } else {
                    System.out.println("Invalid intensity level. Please choose a value between 1 and 10.");
                }

                System.out.println("Enter the color for the light (e.g. White, Blue, Red, Green):");
                scanner.nextLine();
                String color = scanner.nextLine();
                mobileApp.setColor(color);

            } else if ("OFF".equals(userAction)) {
                mobileApp.setState(false);
            } else {
                System.out.println("Invalid input. Please type 'ON' or 'OFF'.");
            }

            smartLight.showAllFeatures();
        }

        scanner.close();
    }
}
