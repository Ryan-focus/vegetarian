package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import bean.User;

import utils.HibernateUtils;

public class MemberManagementDAO {
	
	SessionFactory factory;
	
	public MemberManagementDAO() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	public void addUser(User user) {
		
		Session session = factory.getCurrentSession();
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
	
	public void deleteUser(int uid) {
		
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		User user = new User();
		user.setUid(uid);
		
		try {
			
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
			
		} catch (Exception ex) {
			
			if (tx != null) {
				tx.rollback();				
			}
			throw new RuntimeException(ex);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public void updateUser(User user){
		
		Session session = factory.getCurrentSession();
		String hql = "UPDATE User u SET u.username = :username, u.status = :status WHERE u.uid = :uid";
		Transaction tx = null;
		
		try {
			
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("username", user.getUsername());
			query.setParameter("status", user.getStatus());
			query.setParameter("uid", user.getUid());
			query.executeUpdate();
			tx.commit();
			
		} catch (Exception ex) {
			
			if (tx != null) {
				tx.rollback();				
			}
			throw new RuntimeException(ex);
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getAllUsers(int jtStartIndex, int jtPageSize){
			
		List<User> users = null;
		String hql = "FROM User u ORDER BY u.uid";
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery(hql);
		query.setFirstResult(jtStartIndex);
		query.setMaxResults(jtPageSize);
		users = query.getResultList();
		
		session.getTransaction().commit();
		
		return users;
	}
	
	public User getUserById(int uid) {
		
		User user = new User();
		
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		user = session.get(User.class, uid);
		session.getTransaction().commit();
		session.close();
		
		return user;
	}
	
	@SuppressWarnings("rawtypes")
	public int getUserCount() {
		
		int count = 0;
		String hql = "select count(*) from User";
		
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery( hql );	
		count = ((Long) query.uniqueResult()).intValue();
		session.getTransaction().commit();
				
		return count;		
	}
	
}
