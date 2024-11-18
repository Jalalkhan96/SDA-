import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject interface
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}

// Student class that implements Subject
class Student implements Subject {
    private String name;
    private List<Observer> observers;
    private String departureTime;

    public Student(String name) {
        this.name = name;
        this.observers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // Method to simulate the student leaving
    public void leave() {
        // Record the departure time
        departureTime = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(name + " is leaving at " + departureTime + ".");
        notifyObservers(name + " has left at " + departureTime);
    }

    public String getDepartureTime() {
        return departureTime;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// FriendObserver class that implements Observer
class FriendObserver implements Observer {
    private String name;
    private int estimatedTimeToReach;

    // Constructor
    public FriendObserver(String name, int estimatedTimeToReach) {
        this.name = name;
        this.estimatedTimeToReach = estimatedTimeToReach;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " has been notified: " + message + " and will reach in " + estimatedTimeToReach + " minutes.");
    }

    public String getName() {
        return name;
    }

    public int getEstimatedTimeToReach() {
        return estimatedTimeToReach;
    }
}
