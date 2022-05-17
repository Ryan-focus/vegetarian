package dao;
import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.User;

public class UserDAO {

	  DataSource ds = null;
	Connection conn = null;
	
	{
		try {
			InitialContext ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User login(String email, String password) throws SQLException {
		
		String sql = "SELECT * FROM users WHERE EMAIL ='" + email + "'AND PASSWORD ='" + password +"';";
		Connection conn = ds.getConnection();
		
		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				
				User u = new User();
				u.setEmail(email);
				u.setPassword(password);
				u.setUsername(rs.getString("customer_name"));
				u.setStatus(rs.getString("status"));
				return u;
				
			}
			rs.close();
			stmt.close();
			
		} finally {
			
			conn.close();
		}
		return null;		
	}
	
	public boolean checkEmail(String email) throws SQLException, NamingException {

		String sql = "SELECT * FROM users WHERE EMAIL = ?";
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
	
	public void register(String email, String password, String username)
			throws SQLException, NamingException {

		String sql = "INSERT INTO CUSTOMER(EMAIL, PASSWORD, CUSTOMER_NAME, status) " + "VALUES('" + email + "','"
				+ password + "','" + username + "','user')";
		Connection conn = ds.getConnection();

		try {
			
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			
		} finally {
			conn.close();
		}

	}
	
	public void businessRegister(String email, String password, String username)
			throws SQLException, NamingException {

		String sql = "INSERT INTO users(EMAIL, PASSWORD, CUSTOMER_NAME, status) " + "VALUES('" + email + "','"
				+ password + "','" + username + "','business')";
		Connection conn = ds.getConnection();

		try {
			
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			
		} finally {
			conn.close();
		}

	}

}
