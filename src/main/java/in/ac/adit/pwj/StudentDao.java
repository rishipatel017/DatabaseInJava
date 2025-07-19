package in.ac.adit.pwj;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    boolean save(Student s) throws SQLException;
    boolean remove(long id) throws SQLException;
    boolean update(Student s) throws SQLException;
    List<Student> getAll() throws SQLException;
}
