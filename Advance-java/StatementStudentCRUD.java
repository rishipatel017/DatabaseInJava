import java.sql.*;
import java.util.Scanner;

public class StatementStudentCRUD {

    // Database configuration
    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = con.createStatement();

            while (true) {
                System.out.println("\n----- Student CRUD Menu -----");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Choose: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1: // ADD
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Grade: ");
                        String grade = sc.nextLine();

                        String insertSQL = "INSERT INTO students(name, grade) VALUES('" 
                                            + name + "','" + grade + "')";
                        int rows = stmt.executeUpdate(insertSQL);
                        System.out.println(rows + " student added.");
                        break;

                    case 2: // VIEW
                        String selectSQL = "SELECT * FROM students";
                        ResultSet rs = stmt.executeQuery(selectSQL);
                        System.out.println("ID\tName\tGrade");
                        while (rs.next()) {
                            System.out.println(rs.getInt("id") + "\t" 
                                               + rs.getString("name") + "\t" 
                                               + rs.getString("grade"));
                        }
                        break;

                    case 3: // UPDATE
                        System.out.print("Enter Student ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Grade: ");
                        String newGrade = sc.nextLine();

                        String updateSQL = "UPDATE students SET name='" + newName 
                                           + "', grade='" + newGrade 
                                           + "' WHERE id=" + updateId;
                        int uRows = stmt.executeUpdate(updateSQL);
                        System.out.println(uRows + " student updated.");
                        break;

                    case 4: // DELETE
                        System.out.print("Enter Student ID to delete: ");
                        int deleteId = sc.nextInt();
                        sc.nextLine();

                        String deleteSQL = "DELETE FROM students WHERE id=" + deleteId;
                        int dRows = stmt.executeUpdate(deleteSQL);
                        System.out.println(dRows + " student deleted.");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        con.close();
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
