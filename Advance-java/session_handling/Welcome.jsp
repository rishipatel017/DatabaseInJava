<%@ page import="jakarta.servlet.http.*,jakarta.servlet.*" %>
<%
    HttpSession session = request.getSession(false); // Don't create if not exists
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("login.html"); // No session → send to login
    } else {
        String user = (String) session.getAttribute("username");
%>

<h2>Welcome, <%= user %>!</h2>
<a href="LogoutServlet">Logout</a>

<%
    }
%>
