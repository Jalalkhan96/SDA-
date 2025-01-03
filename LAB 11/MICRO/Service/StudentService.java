package MICRO.Service;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students = new ArrayList<>();

    public List<Student> getAllStudents() {
        return students;
    }

    public void addStudent(String name, String rollNo) {
        students.add(new Student(name, rollNo));
    }
}
