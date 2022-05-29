package main;

import java.util.List;

import bean.Restaurant;
import dao.RestaurantDAO;

public class QueryAllRestaurants {

	//測試用
	public static void main(String[] args) {
		RestaurantDAO rDAO = new RestaurantDAO();
		List<Restaurant> list = rDAO.findAll();
		for(Restaurant restaurants : list) {
			System.out.println(restaurants.getRestaurantNumber());
			System.out.println(restaurants.getRestaurantName());
			
		}
		rDAO.close();
	}

}
