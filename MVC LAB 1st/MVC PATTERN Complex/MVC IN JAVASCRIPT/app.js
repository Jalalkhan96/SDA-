// Model
class Student {
    constructor(name, rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
}

// View
class StudentView {
    constructor() {
        this.studentsList = document.getElementById("studentsList");
    }

    //all students
    displayStudents(students) {
        this.studentsList.innerHTML = "";
        students.forEach(student => {
            const li = document.createElement("li");
            li.textContent = `Name: ${student.name}, Roll No: ${student.rollNo}`;
            this.studentsList.appendChild(li);
        });
    }

    // Display a message
    displayMessage(message) {
        alert(message);
    }
}

// Controller
class StudentController {
    constructor(view) {
        this.students = [];
        this.view = view;

       
        document.getElementById("addStudentBtn").addEventListener("click", () => {
            const name = document.getElementById("studentName").value;
            const rollNo = document.getElementById("studentRollNo").value;
            if (name && rollNo) {
                this.addStudent(name, rollNo);
            } else {
                this.view.displayMessage("Please fill in all fields.");
            }
        });
    }

    // Add a new student
    addStudent(name, rollNo) {
        const student = new Student(name, rollNo);
        this.students.push(student);
        this.view.displayMessage("Student added successfully!");
        this.view.displayStudents(this.students);
    }
}

// Initialize MVC
const view = new StudentView();
const controller = new StudentController(view);
