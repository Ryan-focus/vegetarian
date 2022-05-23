package dao;
import Interface.IReserveDAO;
import bean.Reserve;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ReserveDAO implements IReserveDAO{
private DataSource ds = null;
private final String INSERT = "INSERT INTO RESERVE(reservationDate, reservationCount, orderDate, restaurantNumber, uid) VALUES (?,?,?,?,?)";
	@Override
	public boolean insert(Reserve reserve) {
		boolean isSuccess = false;

		//java.sql.Date 沒有 時區跟秒的的概念
		Date uDate= reserve.getDate();
		java.sql.Date sdate = new java.sql.Date(uDate.getTime());
        int count = reserve.getCount();
        //--odate 紀錄伺服器現在時間 轉型存進資料庫
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		@SuppressWarnings("deprecation")
		Date tDate = new Date(timeStamp);
        java.sql.Date odate = new java.sql.Date(tDate.getTime());
        //--------------------------------------
        int number = reserve.getRestaurantNumber();
        int uid  = reserve.getUid();
        
        Connection conn = null;
        PreparedStatement  pstmt = null;
        
        try {
    		setDataSource();
        	conn = ds.getConnection();
        	pstmt = conn.prepareStatement(INSERT);
        	
        	pstmt.setDate(1, sdate);
        	pstmt.setInt(2, count);
        	pstmt.setDate(3, odate);
        	pstmt.setInt(4, number);
        	pstmt.setInt(5, uid);
        	isSuccess = (pstmt.executeUpdate() != 0);
        	
        } catch (SQLException e) {
        	 e.printStackTrace();
		}
        finally {
            if(pstmt != null) {
                try {
                	pstmt.close();
                }   
                catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                }
                catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		return isSuccess;
	}
	
	
	public void setDataSource() throws SQLException {
		InitialContext ctxt;
			try {
				ctxt = new InitialContext();
				ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
