import observer.eventsmanagement.SMSSupportListener;
import RefactringGURU.Editor;
import RefactringGURU.LogOpenListener; 
import RefactringGURU.EmailNotificationListener; 
public class ObserverPatternDemo {
    public static void main(String[] args) {
        try {
            
            Editor editor = new Editor();
            
            
            editor.events.subscribe("open", new LogOpenListener("log.txt"));
            editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));
            
            
            SMSSupportListener smsListener = new SMSSupportListener("+1234567890", "This is a test SMS.");
            editor.events.subscribe("save", smsListener);
            
            
            editor.openFile("test.txt");
            editor.saveFile();

            smsListener.setDefaultSMS("This is a very long SMS that exceeds the 160-character limit and will therefore trigger a warning to ensure that the message is shortened.");
            editor.saveFile();  
            
        } catch (Exception e) {
            
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
