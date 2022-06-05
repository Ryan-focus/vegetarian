////package dao;
////// DAO: Database Access Object
////
////// 專責與Restaurant Table之新增,修改,刪除與查詢
////
////import java.sql.*;
////import java.util.ArrayList;
////import java.util.List;
////
////import javax.naming.InitialContext;
////import javax.naming.NamingException;
////import javax.sql.DataSource;
////
////import org.hibernate.Session;
////import org.hibernate.SessionFactory;
////import org.hibernate.Transaction;
////
////import bean.Restaurant;
////import model.HibernateUtils;
////
////public class RestaurantDAO {
////	private DataSource ds = null;
////	private Connection conn = null;
////	
////	SessionFactory factory = HibernateUtils.getSessionFactory();
////	
////	public RestaurantDAO() {
////		super();
////	}
////	
////	
////	
////	// 查詢所有紀錄
////	public List<Restaurant> findAll()  {
////		List<Restaurant> restaurantList = new ArrayList<Restaurant>();
////		Session session = factory.getCurrentSession();
////		Transaction tx = null;
////
//////		String hql = "FROM restaurant "; 
////		try {
////		tx = session.beginTransaction();
////		String hql = "from Restaurant";
////		restaurantList = session.createQuery(hql, Restaurant.class)
////				            .getResultList();
////		tx.commit();
////		} catch (Exception e) {
////			if(tx != null)
////				tx.rollback();
////			throw new RuntimeException(e);
////		}
////		return restaurantList;
////	}
////	public void close() {
////		factory.close();
////	}	
////	
////	// 查詢餐廳 by restaurantName&Address&Category&Type-前台搜尋
//////		public List<Restaurant> findRestaurant(String restaurantName, String restaurantAddress, String restaurantCategory,
//////				String restaurantType) {
//////			List<Restaurant> list = new ArrayList<Restaurant>();
//////			String sqlString = "SELECT * FROM restaurant WHERE restaurantName like ? and restaurantAddress like ? "
//////					+ "and restaurantCategory like ?  and restaurantType like ?";
//////				System.out.printf("%s,%s,%s,%s%n",restaurantName,restaurantAddress,restaurantCategory,restaurantType);
////
//////			try {
//////	    		setDataSource();
//////				PreparedStatement pstmt = conn.prepareStatement(sqlString);
//////				pstmt.setString(1, "%" + restaurantName + "%");
//////				pstmt.setString(2, "%" + restaurantAddress + "%");
//////				pstmt.setString(3, "%" + restaurantCategory + "%");
//////				pstmt.setString(4, "%" + restaurantType + "%");
//////				
//////				ResultSet rs = pstmt.executeQuery();
//////				while (rs.next()) {
//////					Restaurant restaurant = new Restaurant(rs.getInt("restaurantNumber"), rs.getString("restaurantName"),
//////							rs.getString("restaurantTel"), rs.getString("restaurantAddress"),
//////							rs.getString("restaurantCategory"), rs.getString("restaurantType"),
//////							rs.getString("restaurantBusinessHours"), rs.getString("restaurantScore"),rs.getString("restaurantMap"));
//////					list.add(restaurant);
//////				}
//////				rs.close();
//////				pstmt.close();
//////				return list;
//////
//////			} catch (Exception e) {
//////				System.err.println("查詢餐廳資料時發生錯誤:" + e);
//////				e.printStackTrace();
//////				return null;
//////			}
//////		} 
////
////
////	 /* 查詢餐廳 by restaurantName&Address&Category&Type
////	public List<Restaurant> findRestaurant(String restaurantName, String restaurantAddress, String[] restaurantCategory,
////			String restaurantType) {
////		List<Restaurant> list = new ArrayList<Restaurant>();
////		String sqlString = "SELECT * FROM restaurant WHERE restaurantName like ? and restaurantAddress like ? "
////				+ "and restaurantCategory in(?,?,?,?) and restaurantType like ?";
////
////		try {
////			PreparedStatement pstmt = conn.prepareStatement(sqlString);
////			pstmt.setString(1, "%" + restaurantName + "%");
////			pstmt.setString(2, "%" + restaurantAddress + "%");
////
////			for (int i = 0; i < restaurantCategory.length; i++) {
////
////				pstmt.setString(i + 3, restaurantCategory[i]);
////
////				System.out.println(restaurantCategory[i]);
////			}
////
////			pstmt.setString(7, "%" + restaurantType + "%");
////
////			ResultSet rs = pstmt.executeQuery();
////			while (rs.next()) {
////				Restaurant restaurant = new Restaurant(rs.getInt("restaurantNumber"), rs.getString("restaurantName"),
////						rs.getString("restaurantTel"), rs.getString("restaurantAddress"),
////						rs.getString("restaurantCategory"), rs.getString("restaurantType"),
////						rs.getString("restaurantBusinessHours"), rs.getString("restaurantScore"));
////				list.add(restaurant);
////			}
////			rs.close();
////			pstmt.close();
////			return list;
////
////		} catch (Exception e) {
////			System.err.println("查詢餐廳資料時發生錯誤:" + e);
////			e.printStackTrace();
////			return null;
////		}
////	} */
////		
////	
////		// 所有餐廳 select all-後台
////		public List<Restaurant> findAllRestaurant() {
////			List<Restaurant> list = new ArrayList<Restaurant>();
////			String sqlString = "SELECT * FROM restaurant";
////
////			try (    		
////				PreparedStatement pstmt = conn.prepareStatement(sqlString); ResultSet rs = pstmt.executeQuery();
////				){					
////				setDataSource();
////	        	
////				while (rs.next()) {
////					Restaurant restaurant = new Restaurant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
////							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9));
////					list.add(restaurant);
////				}
////			} catch (SQLException e) {
////				e.printStackTrace();
////			}
////			return list;
////		}
////		
////	// 查詢餐廳 by number
////	//改寫為Hibernate
////	public Restaurant findRestaurantByNumber(int restaurantNumber) {
////		Restaurant restaurant = null;
////		Session session = factory.getCurrentSession();
////		Transaction tx = null;
////		
////		try {
////			tx = session.beginTransaction();
////			
////			restaurant = (Restaurant) session.get(Restaurant.class, restaurantNumber);
////			
////		    tx.commit();
////		} catch (Exception e) {
////			if (tx != null) {
////				tx.rollback();
////			}
////			e.printStackTrace();
////		}
////		return restaurant;
//////		String sqlString = "SELECT * FROM restaurant WHERE restaurantNumber = " + restaurantNumber;
//////
//////		try {
//////			setDataSource();
//////			PreparedStatement pstmt = conn.prepareStatement(sqlString);
//////			ResultSet rs = pstmt.executeQuery();
//////			while (rs.next()) {
//////				restaurant = new Restaurant(rs.getInt("restaurantNumber"), rs.getString("restaurantName"),
//////						rs.getString("restaurantTel"), rs.getString("restaurantAddress"),
//////						rs.getString("restaurantCategory"), rs.getString("restaurantType"),
//////						rs.getString("restaurantBusinessHours"), rs.getString("restaurantScore"),rs.getString("restaurantMap"));
//////			}
//////			rs.close();
//////			pstmt.close();
//////			return restaurant;
//////
//////		} catch (Exception e) {
//////			System.err.println("查詢餐廳資料時發生錯誤:" + e);
//////			e.printStackTrace();
//////			return null;
//////		}
////	}
////	
////	// 新增餐廳-後台
////	public boolean createRestaurant(Restaurant restaurant) {
////		String sqlString = "Insert into restaurant values(?,?,?,?,?,?,?)";
////		try (PreparedStatement pstmt = conn.prepareStatement(sqlString);) {
////			setDataSource();
////			pstmt.setString(1, restaurant.getRestaurantName());
////			pstmt.setString(2, restaurant.getRestaurantTel());
////			pstmt.setString(3, restaurant.getRestaurantAddress());
////			pstmt.setString(4, restaurant.getRestaurantCategory());
////			pstmt.setString(5, restaurant.getRestaurantType());
////			pstmt.setString(6, restaurant.getRestaurantBusinessHours());
////			pstmt.setString(7, restaurant.getRestaurantScore());
////
////			int updatecount = pstmt.executeUpdate();
////			if (updatecount > 0)
////				return true;
////			else
////				return false;
////		} catch (Exception e) {
////			System.err.println("更新餐廳資料時發生錯誤:" + e);
////			e.printStackTrace();
////			return false;
////		}
////	}
////
////	// 刪除餐廳 by number-後台
////	public boolean deleteRestaurantByNumber(int restaurantNumber) throws SQLException {
////
////		try {
////			String sqlString = "Delete from restaurant where restaurantNumber =" + restaurantNumber;
////			setDataSource();
////			PreparedStatement pstmt = conn.prepareStatement(sqlString);
////			int deletecount = pstmt.executeUpdate();
////			pstmt.close();
////			if (deletecount >= 1)
////				return true;
////			else
////				return false;
////		} catch (Exception e) {
////			System.err.println("刪除餐廳時發生錯誤: " + e);
////			e.printStackTrace();
////			return false;
////		}
////	}
////
////	// 修改餐廳資料-後台
////	public boolean updateRestaurant(Restaurant restaurant) {
////		String sqlString = "Update restaurant set restaurantName=? , restaurantTel=? , "
////				+ "restaurantAddress=? , restaurantCategory=? , restaurantType=? , restaurantBusinessHours=? , restaurantScore=?"
////				+ " where restaurantNumber =? ";
////		try (PreparedStatement pstmt = conn.prepareStatement(sqlString);) {
////			setDataSource();
////			pstmt.setString(1, restaurant.getRestaurantName());
////			pstmt.setString(2, restaurant.getRestaurantTel());
////			pstmt.setString(3, restaurant.getRestaurantAddress());
////			pstmt.setString(4, restaurant.getRestaurantCategory());
////			pstmt.setString(5, restaurant.getRestaurantType());
////			pstmt.setString(6, restaurant.getRestaurantBusinessHours());
////			pstmt.setString(7, restaurant.getRestaurantScore());
////			pstmt.setInt(8, restaurant.getRestaurantNumber());
////
////			int updatecount = pstmt.executeUpdate();
////			if (updatecount > 0)
////				return true;
////			else
////				return false;
////		} catch (Exception e) {
////			System.err.println("更新餐廳資料時發生錯誤:" + e);
////			e.printStackTrace();
////			return false;
////		}
////	}
////	
////	public void setDataSource() throws SQLException {
////		InitialContext ctxt;
////			try {
////				ctxt = new InitialContext();
////				ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
////				conn = ds.getConnection();
////			} catch (NamingException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////		}
////
//}