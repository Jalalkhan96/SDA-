class MobileApp {
    private SmartLight smartLight;

    public MobileApp(SmartLight smartLight) {
        this.smartLight = smartLight;
    }

    public void setTimer() {
        smartLight.setTimer();
    }

    public void setState(boolean isOn) {
        smartLight.changeState(isOn);
    }

    public void setIntensityLevel(int level) {
        smartLight.setIntensityLevel(level);
    }

    public void setColor(String color) {
        smartLight.setColor(color);
    }
}