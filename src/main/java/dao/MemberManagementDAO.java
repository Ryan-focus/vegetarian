package dao;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.User;

public class MemberManagementDAO {
	
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
	
	public void addUser(User user) {
		
		try {
			
			Connection conn = ds.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(
					"set identity_insert users on " +
					"insert into users (uid, email, password, username, status) values (?,?,?,?,?) " +
					"set identity_insert users off");
			
			preparedStatement.setInt(1, user.getUid());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getUsername());
			preparedStatement.setString(5, user.getStatus());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteUser(int uid) {
		
		try {
			
			Connection conn = ds.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(
					"delete users where uid = ?");
			
			preparedStatement.setInt(1, uid);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateUser(User user) throws ParseException{
		
		try {
			
			Connection conn = ds.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(
					"update users set email = ?, password = ?, username = ?, status = ? " +
			"where uid = ?");
			
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getStatus());
			preparedStatement.setInt(5, user.getUid());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<User> getAllUsers(int jtStartIndex, int jtPageSize){
		
		List<User> users = new ArrayList<User>();
		String startIndex = Integer.toString(jtStartIndex);
		String pageSize = Integer.toString(jtPageSize);
		String query = "select * from users order by uid offset " + startIndex + " rows fetch next " + pageSize + " rows only";
		
		try {
			
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				
				User user = new User();
				user.setUid(rs.getInt("uid"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setStatus(rs.getString("status"));
				users.add(user);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User getUserById(int uid) {
		
		User user = new User();
		
		try {
			
			Connection conn = ds.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(
					"select * from users where uid = ?");
			
			preparedStatement.setInt(1, uid);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				
				user.setUid(rs.getInt("uid"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setStatus(rs.getString("status"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public int getUserCount() {
		
		int count = 0;
		
		try {
			
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select count(*) as count from users");
			
			while (rs.next()) {
						
				count = rs.getInt("count");
						
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;		
	}
	
}
