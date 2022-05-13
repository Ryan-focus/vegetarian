package dao;
import java.sql.*;

public class UserDAO {

	private static final String CONNURL = "jdbc:sqlserver://localhost:1433;databaseName=MyDB";
	private static final String USER = "sa";
	private static final String PASSWORD = "passw0rd";

	public boolean login(String email, String password) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "SELECT * FROM Customer WHERE EMAIL = ? AND PASSWORD = ?";
		conn = DriverManager.getConnection(CONNURL, USER, PASSWORD);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet result = pstmt.executeQuery();
			if (result.next()) {
				return true;
			} else {
				return false;
			}
		} // catch(java.sql.SQLException e){}
		finally {
			conn.close();
		}

	}
	
	public boolean checkEmail(String email) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "SELECT * FROM Customer WHERE EMAIL = ?";
		conn = DriverManager.getConnection(CONNURL, USER, PASSWORD);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			ResultSet result = pstmt.executeQuery();
			if (result.next()) {
				return true;
			} else {
				return false;
			}
		}
		finally {
			conn.close();
		}
	}
	
	public void register(String email, String password, String username) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "INSERT INTO CUSTOMER (EMAIL, PASSWORD, CUSTOMER_NAME) VALUES (?, ?, ?)";
		conn = DriverManager.getConnection(CONNURL, USER, PASSWORD);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, username);
			
			pstmt.executeUpdate();
		}
		finally {
			conn.close();
		}
		
	}

}
