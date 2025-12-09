import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Dummy validation (replace with DB check)
        if(username.equals("admin") && password.equals("1234")) {

            HttpSession session = request.getSession();  // Create session
            session.setAttribute("username", username);  // Store data in session
            session.setMaxInactiveInterval(300);         // Session timeout: 5 min

            response.sendRedirect("welcome.jsp");        // Go to welcome page
        } else {
            out.println("<h3>Invalid login! Try again.</h3>");
        }
    }
}
