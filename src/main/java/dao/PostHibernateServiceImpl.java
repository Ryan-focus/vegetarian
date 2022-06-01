package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Interface.PostDAO;
import Interface.PostService;
import bean.Post;
import utils.HibernateUtils;

public class PostHibernateServiceImpl implements PostService {

	SessionFactory factory;
	PostDAO postDAO;
	
	public PostHibernateServiceImpl() {
		this.factory = HibernateUtils.getSessionFactory();
		this.postDAO = new PostHibernateDAOImpl();
		
	}

	public boolean addPostImage(Post post) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = postDAO.addPostImage(post);
			tx.commit();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return result;
	
	
	
}
	
	public boolean deletePost(int id) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = postDAO.deletePost(id);
			tx.commit();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return result;
		
	}
	
	public boolean updatePost(Post post) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = postDAO.updatePost(post);
			tx.commit();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return result;
		
	}
	
	public Post findPost(int id) {
		Post post = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			post = postDAO.findPost(id);
			tx.commit();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return post;
	}
	
	public List<Post> findallPost(){
		List<Post> posts = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			posts = postDAO.findallPost();
			tx.commit();
		} catch (Exception ex) {
			if(tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return posts;
		
	}
	
	
}
