package in.ac.adit.pwj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private final String DBURL = "jdbc:mysql://localhost:3306/student_db";
    private final String DBUSER = "root";
    private final String DBPASSWORD = "";
    private Connection connection;

    public StudentDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean save(Student s) throws SQLException {
        String query = "INSERT INTO student_tbl (name, city, department) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, s.getName());
        pstmt.setString(2, s.getCity());
        pstmt.setString(3, s.getDepartment());
        return pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean remove(long id) throws SQLException {
        String query = "DELETE FROM student_tbl WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setLong(1, id);
        return pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean update(Student s) throws SQLException {
        String query = "UPDATE student_tbl SET name = ?, city = ?, department = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, s.getName());
        pstmt.setString(2, s.getCity());
        pstmt.setString(3, s.getDepartment());
        pstmt.setLong(4, s.getId());
        return pstmt.executeUpdate() > 0;
    }

    @Override
    public List<Student> getAll() throws SQLException {
        List<Student> list = new ArrayList<>();
        String query = "SELECT * FROM student_tbl";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Student s = new Student();
            s.setId(rs.getLong("id"));
            s.setName(rs.getString("name"));
            s.setCity(rs.getString("city"));
            s.setDepartment(rs.getString("department"));
            list.add(s);
        }
        return list;
    }
}
