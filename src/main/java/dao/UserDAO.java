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
		
		String sql = "select * from users where email ='" + email + "'and password ='" + password +"';";
		Connection conn = ds.getConnection();
		
		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				
				User user = new User();
				user.setUid(rs.getInt("uid"));
				user.setEmail(email);
				user.setPassword(password);
				user.setUsername(rs.getString("username"));
				user.setStatus(rs.getString("status"));
				return user;
				
			}
			rs.close();
			stmt.close();
			
		} finally {
			
			conn.close();
		}
		return null;		
	}
	
	public boolean checkEmail(String email) throws SQLException, NamingException {

		String sql = "select * from users where email = ?";
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

		String sql = "insert into users(email, password, username, status) " + "values('" + email + "','"
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

		String sql = "insert into users(email, password, username, status) " + "values('" + email + "','"
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
