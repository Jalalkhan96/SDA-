public abstract class Observer {
    protected Subject subject;
    protected boolean isActive = true; 

    public abstract void update();

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }
}
