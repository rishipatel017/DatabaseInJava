import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LogoutCookieServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Cookie ck = new Cookie("username", "");
        ck.setMaxAge(0); // Delete cookie
        response.addCookie(ck);

        response.sendRedirect("login.html");
    }
}
