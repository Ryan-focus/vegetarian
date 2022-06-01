package dao;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Interface.PostDAO;
import bean.Post;



public class PostHibernateDAOImpl implements PostDAO{
	
	SessionFactory factory;
	 
//	private Connection conn;

	  //建構子
	  public PostHibernateDAOImpl () {
		  factory = utils.HibernateUtils.getSessionFactory();
	  }

	PreparedStatement pst = null;

	
	LocalDateTime dt = LocalDateTime.now(); // 目前時間&日期
	
	
	//新增文章圖片測試
    public boolean addPostImage(Post post) {
//        String sql = "insert into poststest(title,posted_date,posted_text,posted_Imgurl) values(?,?,?,?)";
//        
//        String newImgurl ="images/PostsPhoto/defaultPostImage.jpg";
//        
//        try {
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, post.getTitle());
//            pst.setObject(2, dt);
//            pst.setObject(3, post.getPostedText());
//            
//            if(post.getImgurl()!=null) {
//            pst.setString(4, post.getImgurl());
//            }else {
//            pst.setString(4, newImgurl);	
//            }
//          
//          System.out.println(title);
// 
//            int count = pst.executeUpdate();
//            pst.close();
//            if (count >= 1) return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
        
        Session session = factory.getCurrentSession();
		session.save(post);
		return true;
		
    } 

//	//新增文章(舊)
//    public boolean addPost(String title,String posted_text) {
//        String sql = "insert into posts(title,posted_date,posted_text) values(?,?,?)";
// 
//        try {
//        	
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, title);
//            pst.setObject(2, dt);
//            pst.setString(3, posted_text);
//          
// 
//            int count = pst.executeUpdate();
//            pst.close();
//            if (count >= 1) return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    
    //刪除文章
    public boolean deletePost(int id) {
//        String sql = "delete from poststest where post_id = ?";
// 
//        try {
//            pst = conn.prepareStatement(sql);
//            pst.setInt(1, id);
//            int count = 0;
//            count = pst.executeUpdate();
//            
//            pst.close();
//           
//            if (count > 0) return true;
//        } catch (SQLException e) {
//            System.out.println(e.getErrorCode() + "刪除失敗");
//        }
//        return false;
    	
    	Session session = factory.getCurrentSession();
    	Post post = new Post();
    	post.setPostId(id);
    	session.delete(post);
    	return true;
    	
    }
    //更新文章
    public boolean updatePost(Post post) {
//        String sql = "update poststest set title = ?, posted_text = ?  where post_id = ?" ;
//              
//        try {
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, post.getTitle());
//            pst.setString(2, post.getPostedText());
//            pst.setInt(3, post.getPostId());
// 
// 
//            int count = pst.executeUpdate();
//            pst.close();
//            if (count > 0) return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    	Session session = factory.getCurrentSession();
		session.saveOrUpdate(post);
		return true;
    	
    }
    
  //搜尋一篇文章
    public Post findPost(int id) {
//        String sql = "select * from poststest where post_id = ?" ;
//              
//        try {
//        	 Post post = null;
//             String title;
//             Date posted_date;
//             String posted_text;
//             String imgurl;
//             
//            pst = conn.prepareStatement(sql);
//           
//            pst.setInt(1, id);
//            ResultSet rs =pst.executeQuery();
//            if (rs.next()) {
//                title  =  rs.getString("title");
//                posted_date =rs.getDate("posted_date");
//                posted_text  =  rs.getString("posted_text");
//                imgurl  =  rs.getString("posted_Imgurl");
//                post = new Post(id, title, posted_date, posted_text,imgurl);
//        		
//            }
//        	  rs.close();
//        	  pst.close();
//        	  return post;
//        
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        
        Session session = factory.getCurrentSession();
		Post post = session.get(Post.class, id);
		return post;
        
    }
    
 
//搜尋全部
    
    public List<Post> findallPost() {
//        String sql = "select * from poststest order by post_id desc;" ;
//              
//        List<Post> postsList = new ArrayList<Post>();
//    
// 
//        try {
//            pst = conn.prepareStatement(sql);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//            	 Post post =new Post();
//                 post.setPostId(rs.getInt("post_id"));
//                 post.setTitle(rs.getString("title"));
//                 post.setPostedDate(rs.getDate("posted_date"));
//                 post.setPostedText(rs.getString("posted_text"));
//                 post.setImgurl(rs.getString("posted_Imgurl"));
//                 postsList.add(post);
//                         
//            }
//            	rs.close();
//            	pst.close();
//				
// 
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    	
    	List<Post> posts= null;
		Session session = factory.getCurrentSession();
		String hql ="FROM Post";
		posts = session.createQuery(hql,Post.class)
				.getResultList();
		return posts;
		
 
    }
    
	
}
