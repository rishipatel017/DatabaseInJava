<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert Result</title>
</head>
<body>

<%
    String username = request.getParameter("username");
    String email = request.getParameter("email");

    Connection conn = null;
    PreparedStatement pst = null;

    String url = "jdbc:mysql://localhost:3306/testdb";  // your DB
    String dbUser = "root";
    String dbPass = "password";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, dbUser, dbPass);

        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        pst.setString(2, email);

        int rows = pst.executeUpdate();
        if (rows > 0) {
            out.println("<h3>Data inserted successfully!</h3>");
        } else {
            out.println("<h3>Failed to insert data.</h3>");
        }

    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    } finally {
        if (pst != null) try { pst.close(); } catch (Exception ignored) {}
        if (conn != null) try { conn.close(); } catch (Exception ignored) {}
    }
%>

</body>
</html>
