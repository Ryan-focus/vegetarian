package dao;
import java.sql.*;
<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.List;
=======

import javax.sql.DataSource;
>>>>>>> Stashed changes


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import bean.ForumBean;
import model.HibernateUtils;

public class ForumDAO {
<<<<<<< Updated upstream
	private static Connection conn;

=======
	
	public static Connection conn;
	SessionFactory factory = HibernateUtils.getSessionFactory();
	
	@SuppressWarnings("static-access")
>>>>>>> Stashed changes
	public ForumDAO(Connection conn) {
		this.conn = conn;
	}

//	public static boolean insertForum(ForumBean forumData) {
//		try {
//			String sqlString = "insert into forum values('" + forumData.getVgename()
//					+ "','" + forumData.getVgetheme() + "','" + forumData.getVgecontent() + "')";
//
//			Statement stmt = conn.createStatement();
//			int updatecount = stmt.executeUpdate(sqlString);
//			stmt.close();
//			if (updatecount >= 1)
//				return true;
//			else
//				return false;
//		} catch (Exception e) {
//			System.err.println("錯誤:" + e);
//			return false;
//		}
//	}
	//使用Hibernate
	public Object insertForum (ForumBean forumData) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Object key = null;
		try {
<<<<<<< Updated upstream
			String sqlString = "insert into forum values('" + forumData.getVgeid() + "','" + forumData.getVgename()
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
=======
			tx = session.beginTransaction();
			key = session.save(forumData);
			tx.commit();
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
>>>>>>> Stashed changes
		}
		return key;
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
<<<<<<< Updated upstream
			System.err.println("更新時發生錯誤:" + e);
=======
			System.err.println("錯誤:" + e);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
			System.err.println("刪除時發生錯誤: " + e);
=======
			System.err.println("錯誤: " + e);
>>>>>>> Stashed changes
			return false;
		}
	}
	public  ForumBean queryForum(String vgename) {
		try {
			ForumBean foru =null;
			Integer vgeid;
			String vgetheme;
			String vgecontent;

			String sqlString = "SELECT * " + "FROM forum WHERE vgename = ?";
			PreparedStatement ps =conn.prepareStatement(sqlString);
			ps.setString(1, vgename);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				vgeid=rs.getInt("vgeid");
				vgename=rs.getString("vgename");
				vgetheme=rs.getString("vgetheme"); 
				vgecontent=rs.getString("vgecontent");
				foru=new ForumBean(vgeid,vgename,vgetheme,vgecontent);
				
			}
			
			rs.close();
			ps.close();
			return foru;
		} catch (Exception e) {
<<<<<<< Updated upstream
			System.err.println("尋找資料時發生錯誤:" + e);
=======
			System.err.println("錯誤:" + e);
>>>>>>> Stashed changes
			return null;
		}
	}
	
	
}