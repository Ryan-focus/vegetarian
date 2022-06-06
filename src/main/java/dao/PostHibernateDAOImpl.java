package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Interface.PostDAO;
import bean.Post;
import utils.HibernateUtils;

public class PostHibernateDAOImpl implements PostDAO {

	SessionFactory factory;

	// 建構子
	public PostHibernateDAOImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	// 新增文章圖片
	public boolean addPostImage(Post post) {

		Session session = factory.getCurrentSession();
		session.save(post);
		return true;

	}

	// 刪除文章
	public boolean deletePost(int id) {

		Session session = factory.getCurrentSession();
		Post post = new Post();
		post.setPostId(id);
		session.delete(post);
		return true;

	}

	// 更新文章
	public boolean updatePost(Post post) {
		
		Session session = factory.getCurrentSession();
    	String hql ="UPDATE Post  set title= :title, postedText = :postedText, imgurl = :imgurl where postId = :postId ";
    	int result =0;
    	result = session.createQuery(hql)
    			.setParameter("title",post.getTitle())
    			.setParameter("postedText",post.getPostedText())
    			.setParameter("imgurl", post.getImgurl())
    			.setParameter("postId", post.getPostId())
				.executeUpdate();
    	if(result>0) {
			return true;
		}else {
			return false;
		}

	}

	// 搜尋一篇文章
	public Post findPost(int id) {

		Session session = factory.getCurrentSession();
		Post post = session.get(Post.class, id);
		return post;

	}

//搜尋全部
	public List<Post> findallPost() {

		List<Post> posts = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM Post order by postId desc";
		posts = session.createQuery(hql, Post.class).getResultList();
		return posts;

	}

}
