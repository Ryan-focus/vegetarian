package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.ForumBean;

public class ForumDAO {
	private static Connection conn;

	public ForumDAO(Connection conn) {
		this.conn = conn;
	}

	public static boolean insertForum(ForumBean forumData) {
		try {
			String sqlString = "insert into forum values('" + forumData.getVgename()
					+ "','" + forumData.getVgetheme() + "','" + forumData.getVgecontent() + "')";

			Statement stmt = conn.createStatement();
			int updatecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (updatecount >= 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			return false;
		}
	}

	public static boolean updateForum(ForumBean foruData) {
		try {
			String sqlString = "UPDATE forum " + "SET vgename = '" + foruData.getVgename() + "' "
					+ ",vgetheme = '" + foruData.getVgetheme() + "',vgecontent = '" + foruData.getVgecontent()
					+ "' " + "WHERE vgeid = '" + foruData.getVgeid()+"'";
			Statement stmt = conn.createStatement();
			System.out.println(sqlString);//檢查
			int updatecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (updatecount >= 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("更新時發生錯誤:" + e);
			return false;
		}
	}
	
	public boolean deleteForum(String vgeid) {
		
		try {
			
			String sqlString = "DELETE FROM forum " + "WHERE vgeid = '" + vgeid+"'";
			Statement stmt = conn.createStatement();
			int deletecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (deletecount >= 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("刪除時發生錯誤: " + e);
			return false;
		}
	}
	public  ForumBean queryForum(String vgename) {
		try {
			ForumBean foru =null;
			String vgeid;
			String vgetheme;
			String vgecontent;

			String sqlString = "SELECT * " + "FROM forum WHERE vgename = ?";
			PreparedStatement ps =conn.prepareStatement(sqlString);
		//	ps.setString(1, vgeid);
			ps.setString(1, vgename);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				vgeid=rs.getString("vgeid");
				vgename=rs.getString("vgename");
				vgetheme=rs.getString("vgetheme"); 
				vgecontent=rs.getString("vgecontent");
				foru=new ForumBean(vgeid,vgename,vgetheme,vgecontent);
				
			}
			
			rs.close();
			ps.close();
			return foru;
		} catch (Exception e) {
			System.err.println("尋找資料時發生錯誤:" + e);
			return null;
		}
	}
	
	
}