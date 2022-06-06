package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Interface.RestaurantDAO;
import bean.Restaurant;
import utils.HibernateUtils;

public class RestaurantHibernateDAO implements RestaurantDAO{
	SessionFactory factory;

	public RestaurantHibernateDAO() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public boolean isDup(String restaurantNumber) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Restaurant restaurant WHERE restaurant.restaurantNumber = :restaurantNumber";
		List<Restaurant> Restaurants= session.createQuery(hql,Restaurant.class)
												.setParameter("restaurantNumber", restaurantNumber)
												.getResultList();
		if(Restaurants.size()>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int save(Restaurant restaurant) {
		Session session = factory.getCurrentSession();
		int pk = (int) session.save(restaurant);
		return pk;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurants = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM Restaurant";
		restaurants = session.createQuery(hql,Restaurant.class).getResultList();
		return restaurants;
	}
	
	@Override
	public List<Restaurant> findRestaurant(String restaurantName,String restaurantAddress,String restaurantCategory,String restaurantType) {
		List<Restaurant> restaurants = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM Restaurant restaurant WHERE restaurant.restaurantName like :restaurantName and restaurant.restaurantAddress like :restaurantAddress and restaurant.restaurantCategory like :restaurantCategory and restaurant.restaurantType like :restaurantType";
		restaurants = session.createQuery(hql, Restaurant.class)
										.setParameter("restaurantName", "%"+restaurantName+"%")
										.setParameter("restaurantAddress", "%"+restaurantAddress+"%")
										.setParameter("restaurantCategory", "%"+restaurantCategory+"%")
										.setParameter("restaurantType", "%"+restaurantType+"%")
										.getResultList();
		return restaurants;
	}

	@Override
	public Restaurant getRestaurant(int restaurantNumber) {
		Session session = factory.getCurrentSession();
		Restaurant restaurant = session.get(Restaurant.class, restaurantNumber);
		return restaurant;
	}
	
	@Override
	public Restaurant getRestaurantByName(String restaurantName) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Restaurant restaurant WHERE restaurant.restaurantName like :restaurantName";
		Restaurant restaurant = session.createQuery(hql, Restaurant.class)
										.setParameter("restaurantName", "%"+restaurantName+"%")
										.getSingleResult();
		return restaurant;
	}

	@Override
	public int deleteRestaurant(int restaurantNumber) {
		Session session = factory.getCurrentSession();
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantNumber(restaurantNumber);
		session.delete(restaurant);
		return 1;
	}

	@Override
	public int updateRestaurant(Restaurant restaurant) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(restaurant);
		return 1;
	}

}