package dao;
// DAO: Database Access Object

// 專責與Restaurant Table之新增,修改,刪除與查詢

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.Restaurant;

public class RestaurantDAO {

	private Connection conn;

	// 建構子
	public RestaurantDAO(Connection conn) {
		this.conn = conn;
	}

	// 所有餐廳 select all
	public List<Restaurant> findAllRestaurant() {
		List<Restaurant> list = new ArrayList<Restaurant>();
		String sqlString = "SELECT * FROM restaurant";
		try (PreparedStatement pstmt = conn.prepareStatement(sqlString); ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				Restaurant restaurant = new Restaurant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				list.add(restaurant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 查詢餐廳 by restaurantName&Address&Category&Type
	public List<Restaurant> findRestaurant(String restaurantName, String restaurantAddress, String[] restaurantCategory,
			String restaurantType) {
		List<Restaurant> list = new ArrayList<Restaurant>();
		String sqlString = "SELECT * FROM restaurant WHERE restaurantName like ? and restaurantAddress like ? and restaurantCategory in(?,?,?,?) and restaurantType like ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, "%" + restaurantName + "%");
			pstmt.setString(2, "%" + restaurantAddress + "%");

			for (int i = 0; i < restaurantCategory.length; i++) {

				pstmt.setString(i + 3, restaurantCategory[i]);

				System.out.println(restaurantCategory[i]);
			}
			System.out.println("===========");

			pstmt.setString(7, "%" + restaurantType + "%");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Restaurant restaurant = new Restaurant(rs.getInt("restaurantNumber"), rs.getString("restaurantName"),
						rs.getString("restaurantTel"), rs.getString("restaurantAddress"),
						rs.getString("restaurantCategory"), rs.getString("restaurantType"),
						rs.getString("restaurantBusinessHours"), rs.getString("restaurantScore"));
				list.add(restaurant);
			}
			rs.close();
			pstmt.close();
			return list;

		} catch (Exception e) {
			System.err.println("查詢餐廳資料時發生錯誤:" + e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	// 查詢餐廳 by number
	public Restaurant findRestaurantByNumber(int restaurantNumber) {
		Restaurant restaurant = null;
		String sqlString = "SELECT * FROM restaurant WHERE restaurantNumber = " + restaurantNumber;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				restaurant = new Restaurant(rs.getInt("restaurantNumber"), rs.getString("restaurantName"),
						rs.getString("restaurantTel"), rs.getString("restaurantAddress"),
						rs.getString("restaurantCategory"), rs.getString("restaurantType"),
						rs.getString("restaurantBusinessHours"), rs.getString("restaurantScore"));
			}
			rs.close();
			pstmt.close();
			return restaurant;

		} catch (Exception e) {
			System.err.println("查詢餐廳資料時發生錯誤:" + e);
			e.printStackTrace();
			return null;
		}
	}

	// 新增餐廳
	public boolean createRestaurant(Restaurant restaurant) {
		String sqlString = "Insert into restaurant values(?,?,?,?,?,?,?,?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sqlString);) {
			pstmt.setInt(1, restaurant.getRestaurantNumber());
			pstmt.setString(2, restaurant.getRestaurantName());
			pstmt.setString(3, restaurant.getRestaurantTel());
			pstmt.setString(4, restaurant.getRestaurantAddress());
			pstmt.setString(5, restaurant.getRestaurantCategory());
			pstmt.setString(6, restaurant.getRestaurantType());
			pstmt.setString(7, restaurant.getRestaurantBusinessHours());
			pstmt.setString(8, restaurant.getRestaurantScore());

			int updatecount = pstmt.executeUpdate();
			if (updatecount > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("更新餐廳資料時發生錯誤:" + e);
			e.printStackTrace();
			return false;
		}
	}

	// 刪除餐廳 by number
	public boolean deleteRestaurantByNumber(int restaurantNumber) throws SQLException {

		try {
			String sqlString = "Delete from restaurant where restaurantNumber =" + restaurantNumber;
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			int deletecount = pstmt.executeUpdate();
			pstmt.close();
			if (deletecount >= 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("刪除餐廳時發生錯誤: " + e);
			e.printStackTrace();
			return false;
		}
	}

	// 修改餐廳資料
	public boolean updateRestaurant(Restaurant restaurant) {
		String sqlString = "Update restaurant set restaurantName=? , restaurantTel=? , "
				+ "restaurantAddress=? , restaurantCategory=? , restaurantType=? , restaurantBusinessHours=? , restaurantScore=?"
				+ " where restaurantNumber =? ";
		try (PreparedStatement pstmt = conn.prepareStatement(sqlString);) {
			pstmt.setString(1, restaurant.getRestaurantName());
			pstmt.setString(2, restaurant.getRestaurantTel());
			pstmt.setString(3, restaurant.getRestaurantAddress());
			pstmt.setString(4, restaurant.getRestaurantCategory());
			pstmt.setString(5, restaurant.getRestaurantType());
			pstmt.setString(6, restaurant.getRestaurantBusinessHours());
			pstmt.setString(7, restaurant.getRestaurantScore());
			pstmt.setInt(8, restaurant.getRestaurantNumber());

			int updatecount = pstmt.executeUpdate();
			if (updatecount > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("更新餐廳資料時發生錯誤:" + e);
			e.printStackTrace();
			return false;
		}
	}

}