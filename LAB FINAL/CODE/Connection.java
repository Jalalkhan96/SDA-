class Connection {
    private String sensorName;
    private String sensorID;

    public Connection(String sensorName, String sensorID) {
        this.sensorName = sensorName;
        this.sensorID = sensorID;
    }

    public void connectToSensor() {
        System.out.println("Connected to sensor: " + sensorName + " (ID: " + sensorID + ")");
    }
}
