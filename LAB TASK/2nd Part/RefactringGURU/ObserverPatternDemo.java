package RefactringGURU;

import observer.eventsmanagement.SMSSupportListener;

public class ObserverPatternDemo {
    public static void main(String[] args) throws Exception {
        Editor editor = new Editor();
        
        // Add listeners for file operations
        editor.events.subscribe("open", new LogOpenListener("log.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));
        
        // Add SMSSupportListener to handle SMS notifications
        SMSSupportListener smsListener = new SMSSupportListener("+1234567890", "This is a test SMS.");
        editor.events.subscribe("save", smsListener);
        
        // Perform operations
        editor.openFile("test.txt");
        editor.saveFile();

        // Try with an SMS longer than 160 characters
        smsListener.setDefaultSMS("This is a very long SMS that exceeds the 160-character limit and will therefore trigger a warning to ensure that the message is shortened.");
        editor.saveFile();
    }
}
