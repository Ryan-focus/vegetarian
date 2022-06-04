package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import bean.User;
import utils.HibernateUtils;

public class UserDAO {

	SessionFactory factory;

	public UserDAO() {
		factory = HibernateUtils.getSessionFactory();
	}

	@SuppressWarnings("rawtypes")
	public User login(String email, String password) {

		User user = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
		session.beginTransaction();

		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		user = (User) query.uniqueResult();

		session.getTransaction().commit();
		session.close();
		return user;
	}

	@SuppressWarnings("rawtypes")
	public boolean checkEmail(String email) {
		
		User user = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM User u WHERE u.email = :email";
		session.beginTransaction();

		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		user = (User) query.uniqueResult();
		session.getTransaction().commit();

		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	public void register(User user) {

		Session session = factory.getCurrentSession();
		user.setStatus("user");
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}

	}

	public void businessRegister(User user) {

		Session session = factory.getCurrentSession();
		user.setStatus("business");
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}

	}

}
