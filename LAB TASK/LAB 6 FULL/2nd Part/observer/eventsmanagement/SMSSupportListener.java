package observer.eventsmanagement;

import java.io.File;

import RefactringGURU.EventListener;

public class SMSSupportListener implements EventListener {
    private static final int SMS_MAX_LENGTH = 160;
    private String phoneNumber;
    private String defaultSMS;

    public SMSSupportListener(String phoneNumber, String defaultSMS) {
        this.phoneNumber = phoneNumber;
        this.defaultSMS = defaultSMS;
    }

    @Override
    public void update(String eventType, File file) {
        // Assuming we want to handle SMS notification during the "save" event.
        if (eventType.equals("save")) {
            onSMSNotify();
        }
    }

    private void onSMSNotify() {
        if (defaultSMS.length() > SMS_MAX_LENGTH) {
            System.out.println("Warning: SMS exceeds the maximum length of " + SMS_MAX_LENGTH + " characters.");
        } else {
            sendSMS();
        }
    }

    private void sendSMS() {
        System.out.println("Sending SMS to " + phoneNumber + ": " + defaultSMS);
    }

    public void setDefaultSMS(String sms) {
        this.defaultSMS = sms;
    }

    public String getDefaultSMS() {
        return defaultSMS;
    }
}
