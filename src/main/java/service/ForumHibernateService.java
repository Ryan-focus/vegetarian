package service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import bean.ForumBean;
import dao.ForumDAO;
import dao.ForumHibernateDao;
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
	public List<ForumBean> getAllMembers() {
		List<ForumBean> forumBeans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			forumBeans = forumDAO.getAllMembers();
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
	public int deleteMember(int vgeid) {
		int n = 0 ;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = forumDAO.deleteMember(vgeid);
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
	public int updateMember(ForumBean forumBean) {
		int n = 0 ;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = forumDAO.updateMember(forumBean);
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
