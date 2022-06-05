package main;

import java.util.List;

import Interface.RestaurantService;
import bean.Restaurant;
import dao.RestaurantHibernateService;

public class QueryAllRestaurants {

	// 測試用
	public static void main(String[] args) {
		// RestaurantDAO rDAO = new RestaurantDAO();
		RestaurantService restauranteService = new RestaurantHibernateService();
		// List<Restaurant> list = rDAO.findAll();
		List<Restaurant> list = restauranteService.getAllRestaurants();
		for (Restaurant restaurants : list) {
			System.out.println(restaurants.getRestaurantNumber());
			System.out.println(restaurants.getRestaurantName());
		}
		// rDAO.close();
	}
}
