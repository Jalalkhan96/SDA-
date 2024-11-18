// Service Layer
import java.util.List;

class NotificationService {
    // Register observers (friends) to the subject (student)
    public void registerObservers(Student student, List<Observer> friends) {
        for (Observer friend : friends) {
            student.addObserver(friend);
        }
    }

    // Notify all observers when the student leaves
    public void notifyAllObservers(Student student) {
        student.leave();
    }
}
