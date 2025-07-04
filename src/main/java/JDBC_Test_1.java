import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBC_Test_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String URL="jdbc:mysql://localhost:3306/Demo_db";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(URL,"root","");
			Statement st=(Statement) connection.createStatement();
			String query="insert into student_tbl (name,city) values('Naman','Tarapur')";
			st.execute(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
