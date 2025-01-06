class SmartHomeHub {
    private boolean connectionEstablished;

    public SmartHomeHub() {
        this.connectionEstablished = false;
    }

    public void establishConnection() {
        this.connectionEstablished = true;
        System.out.println("Connection with Smart Home Hub established.");
    }

    public boolean isConnectionEstablished() {
        return this.connectionEstablished;
    }
}