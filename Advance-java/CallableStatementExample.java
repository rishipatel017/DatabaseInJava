import java.sql.*;
import java.util.Scanner;
/*
    DELIMITER //
CREATE PROCEDURE AddStudent(
    IN s_name VARCHAR(100),
    IN s_grade VARCHAR(20)
)
BEGIN
    INSERT INTO students(name, grade) VALUES (s_name, s_grade);
END //
DELIMITER ;
   create this in your databse side 
*/
public class CallableStatementExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String pass = "password";

        Scanner sc = new Scanner(System.in);

        try {
            // 1. Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to database
            Connection con = DriverManager.getConnection(url, user, pass);

            // 3. Take input
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Student Grade: ");
            String grade = sc.nextLine();

            // 4. Prepare callable statement
            CallableStatement cs = con.prepareCall("{call AddStudent(?, ?)}");
            cs.setString(1, name);   // Set first parameter
            cs.setString(2, grade);  // Set second parameter

            // 5. Execute
            cs.execute();
            System.out.println("Student added successfully!");

            // 6. Close resources
            cs.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
