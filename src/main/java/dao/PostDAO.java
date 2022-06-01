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

import bean.Post;





public class PostDAO implements Serializable{


	private static final long serialVersionUID = 1L;

	SessionFactory factory;
	 private Connection conn;


	  //撱箸���
	  public PostDAO (Connection conn) {
			this.conn = conn;
	  }

	PreparedStatement pst = null;
	
	LocalDateTime dt = LocalDateTime.now(); // ������&����
	
	
	//�憓����葫閰�
    public boolean addPostImage(String title,String posted_text, String Imgurl) throws IOException {

        String sql = "insert into posts(title,posted_date,posted_text,posted_Imgurl) values(?,?,?,?)";
         
        String newImgurl ="images/PostsPhoto/defaultPostImage.jpg";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, title);
            pst.setObject(2, dt);
            pst.setObject(3, posted_text);
            
            if(Imgurl!=null) {
            pst.setString(4, Imgurl);
            }else {
            pst.setString(4, newImgurl);	
            }
          
          System.out.println(title);
 
            int count = pst.executeUpdate();
            pst.close();
            if (count >= 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    } 

	//�憓���
    public boolean addPost(String title,String posted_text) {
        String sql = "insert into posts(title,posted_date,posted_text) values(?,?,?)";
 
        try {
        	
            pst = conn.prepareStatement(sql);
            pst.setString(1, title);
            pst.setObject(2, dt);
            pst.setString(3, posted_text);
          
 
            int count = pst.executeUpdate();
            pst.close();
            if (count >= 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //������
    public boolean deletePost(int id) {

        String sql = "delete from posts where post_id = ?";
 
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            int count = 0;
            count = pst.executeUpdate();
            
            pst.close();
           
            if (count > 0) return true;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "��憭望��");
        }
        return false;

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
    	
    
 
    }
    //������
    public boolean updatePost(Post post,String title ,String  posted_text, int id) {
        String sql = "update posts set title = ?, posted_text = ?  where post_id = ?" ;
              
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, title);
            pst.setString(2, posted_text);
            pst.setInt(3, id);
 
 
            int count = pst.executeUpdate();
            pst.close();
            if (count > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
  //����蝭���
    public Post findPost(int id) {
        String sql = "select * from posts where post_id = ?" ;
              
        try {
        	 Post post = null;
             String title;
             Date posted_date;
             String posted_text;
             String imgurl;
             
            pst = conn.prepareStatement(sql);
           
            pst.setInt(1, id);
            ResultSet rs =pst.executeQuery();
            if (rs.next()) {
                title  =  rs.getString("title");
                posted_date =rs.getDate("posted_date");
                posted_text  =  rs.getString("posted_text");
                imgurl  =  rs.getString("posted_Imgurl");
                post = new Post(id, title, posted_date, posted_text,imgurl);
        		
            }
        	  rs.close();
        	  pst.close();
        	  return post;
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
 
//����
    
    public List<Post> findallPost() {
        String sql = "select * from posts order by post_id desc;" ;
              
        List<Post> postsList = new ArrayList<Post>();
    
 
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            	 Post post =new Post();
                 post.setPostId(rs.getInt("post_id"));
                 post.setTitle(rs.getString("title"));
                 post.setPostedDate(rs.getDate("posted_date"));
                 post.setPostedText(rs.getString("posted_text"));
                 post.setImgurl(rs.getString("posted_Imgurl"));
                 postsList.add(post);
                         
            }
            	rs.close();
            	pst.close();
				
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return postsList;
		
 
    }
    
	
}
