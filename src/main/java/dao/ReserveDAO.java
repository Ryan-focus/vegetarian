package dao;
import Interface.IReserveDAO;
import bean.Reserve;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;


public class ReserveDAO implements IReserveDAO{
private DataSource ds;

private final String INSERT = "INSERT INTO RESERVE(reservationDate, reservationCount, orderDate, restaurantNumber, uid) VALUES (?,?,?,?,?)";

public void setDataSource(DataSource dataSource) {
this.ds = dataSource;


}
	@Override
	public boolean insert(Reserve reserve) {
		boolean isSuccess = false;
		//java.sql.Date 沒有 時區跟秒的的概念
		java.sql.Date date = java.sql.Date.valueOf(reserve.getDate().toString());
        int count = reserve.getCount();
        //--odate 紀錄伺服器現在時間 轉型存進資料庫
        Date localdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formattedDate = sdf.format(localdate);
        java.sql.Date odate = java.sql.Date.valueOf(formattedDate);
        //--------------------------------------
        int number = reserve.getRestaurantNumber();
        int uid  = reserve.getUid();
        
        Connection conn = null;
        PreparedStatement  pstmt = null;
        
        try {
        	conn = ds.getConnection();
        	pstmt = conn.prepareStatement(INSERT);
        	
        	pstmt.setDate(1, date);
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
}
