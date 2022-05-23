package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtility {
	
	private static Connection connection = null;
	private static DataSource ds = null;
			
	public static Connection getConnection() {
		
		if (connection != null)
			return connection;
		
		else {
			
			try {
				
				InitialContext ctxt = new InitialContext();
				ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
				connection = ds.getConnection();
				
			} catch (NamingException ne) {
			      System.out.println("Naming Service Lookup Exception");  
		    } catch (SQLException e) {
		      System.out.println("Database Connection Error"); 
		    } finally {
		      try {
		        if (connection != null) connection.close();
		      } catch (Exception e) {
		        System.out.println("Connection Pool Error!");
		      }
		    }
			return connection;
		}
	}

}
