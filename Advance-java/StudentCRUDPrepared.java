import java.sql.*;
import java.util.Scanner;

public class StudentCRUDPrepared {

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

                        String insertSQL = "INSERT INTO students(name, grade) VALUES (?, ?)";
                        PreparedStatement pstInsert = con.prepareStatement(insertSQL);
                        pstInsert.setString(1, name);
                        pstInsert.setString(2, grade);
                        int rows = pstInsert.executeUpdate();
                        System.out.println(rows + " student added.");
                        pstInsert.close();
                        break;

                    case 2: // VIEW
                        String selectSQL = "SELECT * FROM students";
                        PreparedStatement pstSelect = con.prepareStatement(selectSQL);
                        ResultSet rs = pstSelect.executeQuery();
                        System.out.println("ID\tName\tGrade");
                        while (rs.next()) {
                            System.out.println(rs.getInt("id") + "\t" 
                                               + rs.getString("name") + "\t" 
                                               + rs.getString("grade"));
                        }
                        pstSelect.close();
                        break;

                    case 3: // UPDATE
                        System.out.print("Enter Student ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Grade: ");
                        String newGrade = sc.nextLine();

                        String updateSQL = "UPDATE students SET name=?, grade=? WHERE id=?";
                        PreparedStatement pstUpdate = con.prepareStatement(updateSQL);
                        pstUpdate.setString(1, newName);
                        pstUpdate.setString(2, newGrade);
                        pstUpdate.setInt(3, updateId);
                        int uRows = pstUpdate.executeUpdate();
                        System.out.println(uRows + " student updated.");
                        pstUpdate.close();
                        break;

                    case 4: // DELETE
                        System.out.print("Enter Student ID to delete: ");
                        int deleteId = sc.nextInt();
                        sc.nextLine();

                        String deleteSQL = "DELETE FROM students WHERE id=?";
                        PreparedStatement pstDelete = con.prepareStatement(deleteSQL);
                        pstDelete.setInt(1, deleteId);
                        int dRows = pstDelete.executeUpdate();
                        System.out.println(dRows + " student deleted.");
                        pstDelete.close();
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
