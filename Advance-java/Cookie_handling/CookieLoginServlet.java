import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class CookieLoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");

        // Create cookie
        Cookie ck = new Cookie("username", username);
        ck.setMaxAge(60 * 5); // Expires after 5 minutes
        response.addCookie(ck);

        response.sendRedirect("welcome.jsp");
    }
}
