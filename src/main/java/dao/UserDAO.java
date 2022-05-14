package dao;
import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {

	  DataSource ds = null;
	Connection conn = null;
	  
	public boolean login(String email, String password) throws SQLException, ClassNotFoundException, NamingException {

		String sql = "SELECT * FROM Customer WHERE EMAIL = ? AND PASSWORD = ?";
	    InitialContext ctxt = new InitialContext();
	    ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/UserDB");
	    Connection conn = ds.getConnection();

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
	
	public boolean checkEmail(String email) throws ClassNotFoundException, SQLException, NamingException {

		String sql = "SELECT * FROM Customer WHERE EMAIL = ?";
	    InitialContext ctxt = new InitialContext();
	    ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/UserDB");
	    Connection conn = ds.getConnection();

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
	
	public void register(String email, String password, String username) throws ClassNotFoundException, SQLException, NamingException {

		String sql = "INSERT INTO CUSTOMER (EMAIL, PASSWORD, CUSTOMER_NAME) VALUES (?, ?, ?)";
	    InitialContext ctxt = new InitialContext();
	    ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/UserDB");
	    Connection conn = ds.getConnection();
	    
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
