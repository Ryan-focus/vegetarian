package Interface;

import java.util.List;

import bean.Restaurant;

public interface RestaurantDAO {

	boolean isDup(String restaurantNumber);

	int save(Restaurant restaurant);

	List<Restaurant> getAllRestaurants();

	Restaurant getRestaurant(int restaurantNumber);

	Restaurant getRestaurantByName(String restaurantName) ;
	
	int deleteRestaurant(int restaurantNumber);

	int updateRestaurant(Restaurant restaurant);

	}