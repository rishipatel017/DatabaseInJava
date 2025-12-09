<%
    Cookie cookies[] = request.getCookies();
    String user = null;

    if (cookies != null) {
        for (Cookie c : cookies) {
            if (c.getName().equals("username")) {
                user = c.getValue();
            }
        }
    }

    if (user == null) {
%>
        <h3>No cookie found. Please login.</h3>
        <a href="login.html">Go to Login</a>
<%
    } else {
%>
        <h2>Welcome, <%= user %>!</h2>
        <a href="LogoutCookieServlet">Logout</a>
<%
    }
%>
