import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);  
        if (session != null) {
            session.invalidate(); // Destroy session
        }

        response.sendRedirect("login.html");
    }
}
