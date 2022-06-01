package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
	public List<ForumBean> getAllMembers() {
		List<ForumBean> forumBean = null;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM ForumBean";
		forumBean = session.createQuery(hql, ForumBean.class)
							.getResultList();
		return forumBean;
	}

	@Override
	public int deleteMember(int vgeid) {//在hibernate int都改void新作法
		Session session = factory.getCurrentSession();
		ForumBean forumBean = new ForumBean();
		forumBean.setVgeid(vgeid);
		session.delete(forumBean);
		return 1;
	}

	@Override
	public int updateMember(ForumBean forumBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(forumBean);
		return 1;
	}
}
