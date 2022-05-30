package dao;
import Interface.IReserveDAO;
import bean.Reserve;
import model.HibernateUtils;

//import java.util.Date;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;

//import這三種方法，就可以使用Hibernate代替JDBC了
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class ReserveDAO implements IReserveDAO{
//private SessionFactory sessionFactory; 
//private DataSource ds = null;
//private final String INSERT = "INSERT INTO RESERVE(reservationDate, reservationCount, orderDate, restaurantNumber, uid) VALUES (?,?,?,?,?)";
//	

SessionFactory factory = HibernateUtils.getSessionFactory();

//	public ReserveDAO() {
//	}
//	
//	public ReserveDAO(SessionFactory sessionFactory) { 
//        this.setSessionFactory(sessionFactory);
//    }
//	
//	public void setSessionFactory(
//            SessionFactory sessionFactory) { 
//	this.sessionFactory = sessionFactory; 
//	} 
//	
	//使用Hibernate改寫
	// 新增一筆Reserve物件到資料庫
	@Override
	public Object insert(Reserve reserve) {
		// 取得Session
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Object key = null;
		try {
			tx = session.beginTransaction();
			key = session.save(reserve);
	        tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		e.printStackTrace();
		}
		return key;
	}

	@Override
	public void close() {
		factory.close();
	}	
	
	
//	@Override
//	public boolean insert(Reserve reserve) {
//		boolean isSuccess = false;
//
//		//java.sql.Date 沒有 時區跟秒的的概念
//		Date uDate= reserve.getDate();
//		java.sql.Date sdate = new java.sql.Date(uDate.getTime());
//        int count = reserve.getCount();
//        //--odate 紀錄伺服器現在時間 轉型存進資料庫
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formatDateTime = now.format(formatter);
//        //--------------------------------------
//        int number = reserve.getRestaurantNumber();
//        int uid  = reserve.getUid();
//        
//        Connection conn = null;
//        PreparedStatement  pstmt = null;
//        
//        try {
//    		setDataSource();
//        	conn = ds.getConnection();
//        	pstmt = conn.prepareStatement(INSERT);
//        	
//        	pstmt.setDate(1, sdate);
//        	pstmt.setInt(2, count);
//        	pstmt.setString(3, formatDateTime);
//        	pstmt.setInt(4, number);
//        	pstmt.setInt(5, uid);
//        	isSuccess = (pstmt.executeUpdate() != 0);
//        	
//        } catch (SQLException e) {
//        	 e.printStackTrace();
//		}
//        finally {
//            if(pstmt != null) {
//                try {
//                	pstmt.close();
//                }   
//                catch(SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(conn != null) {
//                try {
//                    conn.close();
//                }
//                catch(SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//		return isSuccess;
//	}
	
	
//	public void setDataSource() throws SQLException {
//		InitialContext ctxt;
//			try {
//				ctxt = new InitialContext();
//				ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}


}
