package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Interface.ForumDAO;
import bean.ForumBean;

import utils.HibernateUtils;

public class ForumHibernateDao implements ForumDAO {
	

	SessionFactory factory;
	
	public ForumHibernateDao() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public int save(ForumBean forumBean) {
		Session session = factory.getCurrentSession();
		session.save(forumBean);
		return 1;
	}
	


	@Override
	public List<ForumBean> getAllForums() {
		List<ForumBean> forumBean = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM ForumBean";
		forumBean = session.createQuery(hql, ForumBean.class)
							.getResultList();
		return forumBean;
	}

	@Override
	public int deleteForum(int vgeid) {
		Session session = factory.getCurrentSession();
		ForumBean forumBean = new ForumBean();
		forumBean.setVgeid(vgeid);
		session.delete(forumBean);
		return 1;
	}

	@Override
	public int updateForum(ForumBean forumBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(forumBean);
		return 1;
	}

	@Override
	public List<ForumBean> queryName() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ForumBean";
		List<ForumBean> forumBeans = session.createQuery(hql,ForumBean.class)
									.getResultList();
		return forumBeans;
	}

	@Override
	public List<ForumBean> queryone(String vgename) {
		Session session = factory.getCurrentSession();
		String hql = "from ForumBean where vgename =:vgename";
		List<ForumBean> forumBeans = session.createQuery(hql,ForumBean.class)
				.setParameter("vgename", vgename)
				.getResultList();
		
		return forumBeans;
	}
}
