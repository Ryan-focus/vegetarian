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
			String sqlString = "insert into VGE_FORUM values('" + forumData.getVgeid() + "','" + forumData.getVgename()
					+ "','" + forumData.getVgetheme() + "','" + forumData.getVgecontent() + "')";

			Statement stmt = conn.createStatement();
			System.out.println(sqlString);
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

	public boolean updateForum(ForumBean fromData) {
		try {
			String sqlString = "UPDATE vge_FORUM " + "SET vgename = '" + fromData.getVgename() + "' "
					+ "set vgetheme = '" + fromData.getVgetheme() + "set vgecontent = '" + fromData.getVgecontent()
					+ "' " + "WHERE vgeid = " + fromData.getVgeid();
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
	
	public boolean deleteForum(int vgeid) {
		try {
			String sqlString = "DELETE FROM vge_FORUM " + "WHERE vgeid = " + vgeid;
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
	public  ForumBean queryForum(String vgeid) {
		try {
			ForumBean foru =null;
			String vgename;
			String vgetheme;
			String vgecontent;

//			Statement stmt = conn.createStatement();
			String sqlString = "SELECT * " + "FROM vge_FORUM WHERE vgeid = ?";
			PreparedStatement ps =conn.prepareStatement(sqlString);
			ps.setString(1, vgeid);
//			ResultSet rs = stmt.executeQuery(sqlString);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
//				ForumBean fb = new ForumBean();
//				fb.setVgeid(rs.getString("vgeid"));
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
