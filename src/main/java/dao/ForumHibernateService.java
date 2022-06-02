package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Interface.ForumDAO;
import Interface.ForumService;
import bean.ForumBean;
import utils.HibernateUtils;

public class ForumHibernateService implements ForumService {
	
	SessionFactory factory;
	ForumDAO forumDAO;
	
	public ForumHibernateService() {
		this.factory = HibernateUtils.getSessionFactory();
		this.forumDAO = new ForumHibernateDao();
	}

	@Override
	public int save(ForumBean forumBean) {
		int n = 0 ;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = forumDAO.save(forumBean);
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
			   tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return n;
	}

	@Override
	public List<ForumBean> getAllForums() {
		List<ForumBean> forumBeans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			forumBeans = forumDAO.getAllForums();
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
			   tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return forumBeans;
	}

	@Override
	public int deleteForum(int vgeid) {
		int n = 0 ;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = forumDAO.deleteForum(vgeid);
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
			   tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return n;
	}

	@Override
	public int updateForum(ForumBean forumBean) {
		int n = 0 ;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = forumDAO.updateForum(forumBean);
			tx.commit();
		}catch (Exception ex) {
			if(tx != null) {
			   tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return n;
	}
}
