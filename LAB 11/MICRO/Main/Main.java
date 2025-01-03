import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080); // Run server on port 8080

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);

        // Register the StudentController servlet
        handler.addServlet(StudentController.class, "/api/students");

        server.start();
        System.out.println("Server started at http://localhost:8080");
        server.join();
    }
}
