package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Interface.RestaurantDAO;
import Interface.RestaurantService;
import bean.Restaurant;
import utils.HibernateUtils;

public class RestaurantHibernateService implements RestaurantService{
	
	SessionFactory factory;
	RestaurantDAO restaurantDAO ;
		
	public RestaurantHibernateService() {
		this.factory = HibernateUtils.getSessionFactory();
		this.restaurantDAO = new RestaurantHibernateDAO();
	}

	@Override
	public boolean isDup(String restaurantNumber) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = restaurantDAO.isDup(restaurantNumber);
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
				tx.rollback();	
			}
			throw new RuntimeException();
		}
		return result;
	}	
	

	@Override
	public int save(Restaurant restaurant) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = restaurantDAO.save(restaurant);
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
				tx.rollback();	
			}
			throw new RuntimeException();
		}
		return n;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurants = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			restaurants = restaurantDAO.getAllRestaurants();
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
				tx.rollback();	
			}
			throw new RuntimeException();
		}
		return restaurants;
	}
	
	@Override
	public List<Restaurant> findRestaurant(String restaurantName,String restaurantAddress,String restaurantCategory,String restaurantType) {
		List<Restaurant> restaurants = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			restaurants = restaurantDAO.findRestaurant(restaurantName, restaurantAddress, restaurantCategory, restaurantType);
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
				tx.rollback();	
			}
			throw new RuntimeException();
		}
		return restaurants;
	}

	@Override
	public Restaurant getRestaurant(int restaurantNumber) {
		Restaurant restaurant = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			restaurant = restaurantDAO.getRestaurant(restaurantNumber);
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
				tx.rollback();	
			}
			throw new RuntimeException();
		}
		return restaurant;
	}
	
	@Override
	public Restaurant getRestaurantByName(String restaurantName) {
		Restaurant restaurant = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			restaurant = restaurantDAO.getRestaurantByName(restaurantName);
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
				tx.rollback();	
			}
			throw new RuntimeException();
		}
		return restaurant;
	}

	@Override
	public int deleteRestaurant(int restaurantNumber) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = restaurantDAO.deleteRestaurant(restaurantNumber);
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
				tx.rollback();	
			}
			throw new RuntimeException();
		}
		return n;
	}

	@Override
	public int updateRestaurant(Restaurant restaurant) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = restaurantDAO.updateRestaurant(restaurant);
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
				tx.rollback();	
			}
			throw new RuntimeException();
		}
		return n;
	}

}
