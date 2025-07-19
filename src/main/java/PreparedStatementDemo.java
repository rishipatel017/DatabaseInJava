import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementDemo {
	public static void main(String args[]) {
		
		final String URL="jdbc:mysql://localhost:3306/demo_db";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(URL,"root","");
			//String query="insert into student_tbl (name,city) values (?,?)";
			String query="update student_tbl set name=? where id =?";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			//preparedStatement.setString(1,"Aatish");
			preparedStatement.setInt(2,14);
			
			preparedStatement.execute();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
