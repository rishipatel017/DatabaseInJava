import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import in.ac.adit.pwj.*;

public class Main {
    public static void main(String[] args) {
        StudentDao dao = new StudentDaoImpl();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n----------- Operation Menu --------------");
            System.out.println("1. Insert Data");
            System.out.println("2. Get all records");
            System.out.println("3. Remove Data");
            System.out.println("4. Update record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        Student s = new Student();
                        System.out.print("Enter Name: ");
                        s.setName(sc.nextLine());
                        System.out.print("Enter City: ");
                        s.setCity(sc.nextLine());
                        System.out.print("Enter Department: ");
                        s.setDepartment(sc.nextLine());
                        if (dao.save(s)) System.out.println("Student inserted successfully!");
                        else System.out.println("Insert failed.");
                        break;
                    case 2:
                        List<Student> students = dao.getAll();
                        if (students.isEmpty()) {
                            System.out.println("No records found.");
                        } else {
                            for (Student st : students)
                                System.out.println(st);
                        }
                        break;
                    case 3:
                        System.out.print("Enter Student ID to delete: ");
                        long id = sc.nextLong();
                        if (dao.remove(id)) System.out.println("Student deleted successfully!");
                        else System.out.println("Deletion failed. ID not found.");
                        break;
                    case 4:
                        Student s2 = new Student();
                        System.out.print("Enter ID of student to update: ");
                        s2.setId(sc.nextLong());
                        sc.nextLine(); // consume newline
                        System.out.print("Enter New Name: ");
                        s2.setName(sc.nextLine());
                        System.out.print("Enter New City: ");
                        s2.setCity(sc.nextLine());
                        System.out.print("Enter New Department: ");
                        s2.setDepartment(sc.nextLine());
                        if (dao.update(s2)) System.out.println("Student updated successfully!");
                        else System.out.println("Update failed. ID not found.");
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
