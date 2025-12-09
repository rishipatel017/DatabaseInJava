<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student CRUD - Single Page</title>
</head>
<body>
<h2>Student CRUD Application (Single JSP Page)</h2>

<%
    // DB CONFIG
    String url = "jdbc:mysql://localhost:3306/testdb";
    String dbUser = "root";
    String dbPass = "password";

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, dbUser, dbPass);

    String action = request.getParameter("action");

    // ADD STUDENT
    if ("add".equals(action)) {
        String name = request.getParameter("name");
        String grade = request.getParameter("grade");

        PreparedStatement pst = con.prepareStatement("INSERT INTO students(name, grade) VALUES (?, ?)");
        pst.setString(1, name);
        pst.setString(2, grade);
        pst.executeUpdate();
        out.println("<p style='color:green'>Student Added Successfully!</p>");
    }

    // UPDATE STUDENT
    if ("update".equals(action)) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String grade = request.getParameter("grade");

        PreparedStatement pst = con.prepareStatement("UPDATE students SET name=?, grade=? WHERE id=?");
        pst.setString(1, name);
        pst.setString(2, grade);
        pst.setInt(3, id);
        pst.executeUpdate();
        out.println("<p style='color:green'>Student Updated Successfully!</p>");
    }

    // DELETE STUDENT
    if ("delete".equals(action)) {
        int id = Integer.parseInt(request.getParameter("id"));
        PreparedStatement pst = con.prepareStatement("DELETE FROM students WHERE id=?");
        pst.setInt(1, id);
        pst.executeUpdate();
        out.println("<p style='color:red'>Student Deleted!</p>");
    }

    // EDIT MODE (Load student for editing)
    ResultSet editData = null;
    if ("edit".equals(action)) {
        int id = Integer.parseInt(request.getParameter("id"));
        PreparedStatement pst = con.prepareStatement("SELECT * FROM students WHERE id=?");
        pst.setInt(1, id);
        editData = pst.executeQuery();
        editData.next();
    }
%>

<hr>

<!-- ADD / UPDATE FORM -->
<h3><%= ("edit".equals(action)) ? "Edit Student" : "Add Student" %></h3>

<form method="post" action="student.jsp">
    <%
        if ("edit".equals(action)) {
    %>
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= editData.getInt("id") %>">
    <% } else { %>
        <input type="hidden" name="action" value="add">
    <% } %>

    Name: <input type="text" name="name" required 
           value="<%= ("edit".equals(action)) ? editData.getString("name") : "" %>"><br><br>

    Grade: <input type="text" name="grade" required
            value="<%= ("edit".equals(action)) ? editData.getString("grade") : "" %>"><br><br>

    <button type="submit"><%= ("edit".equals(action)) ? "Update" : "Add" %></button>
</form>

<hr>

<!-- SHOW STUDENT TABLE -->
<h3>All Students</h3>

<table border="1" cellpadding="6">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Grade</th>
        <th>Action</th>
    </tr>

<%
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM students");

    while (rs.next()) {
%>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("name") %></td>
        <td><%= rs.getString("grade") %></td>
        <td>
            <a href="student.jsp?action=edit&id=<%= rs.getInt("id") %>">Edit</a> |
            <a href="student.jsp?action=delete&id=<%= rs.getInt("id") %>"
               onclick="return confirm('Delete this student?')">Delete</a>
        </td>
    </tr>
<%
    }
    con.close();
%>
</table>

</body>
</html>
