import java.io.IOException;
import java.io.PrintWriter;

public class StudentController extends HttpServlet {
    private final StudentService studentService = new StudentService();
    private final Gson gson = new Gson(); // For JSON conversion

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(studentService.getAllStudents()));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String rollNo = req.getParameter("rollNo");

        if (name != null && rollNo != null) {
            studentService.addStudent(name, rollNo);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Student added successfully!");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid input. Name and Roll No are required.");
        }
    }
}
