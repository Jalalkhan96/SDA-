class SmartLight {
    public void setTimer() {
        System.out.println("Timer for Smart Light set.");
    }

    public void changeState(boolean isOn) {
        if (isOn) {
            System.out.println("Smart Light turned ON.");
        } else {
            System.out.println("Smart Light turned OFF.");
        }
    }

    public void setIntensityLevel(int level) {
        System.out.println("Smart Light intensity set to level: " + level);
    }

    public void setColor(String color) {
        System.out.println("Smart Light color set to: " + color);
    }

    public void showAllFeatures() {
        System.out.println("Smart Light Features: Timer, Change State, Set Intensity, Set Color.");
    }

    public void turnOn() {
        changeState(true);
    }

    public void turnOff() {
        changeState(false);
    }
}
