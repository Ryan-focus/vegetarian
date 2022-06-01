package bean;
import java.io.Serializable;
import java.sql.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post implements Serializable{
<<<<<<< Updated upstream
	  
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int postId;
=======
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer postId;
>>>>>>> Stashed changes
	    private String title;
	    private Date postedDate;
	    private String postedText;
	    private String imgurl;
	    
	    
	    
	    public Post(Integer postId,String title,Date postedDate,String postedText,String imgurl) {
	    	
	    	this.postId =postId;
	    	this.title = title;
	    	this.postedDate = postedDate;
	    	this.postedText = postedText;
	    	this.imgurl = imgurl;
	    	
	    }
	    
		public Post() {
			
		}
		
		public Post(Integer postId) {
			this.postId =postId;
		}

		public Integer getPostId() {
			return postId;
		}
		public void setPostId(Integer postId) {
			this.postId = postId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Date getPostedDate() {
			return postedDate;
		}
		public void setPostedDate(Date postedDate) {
			this.postedDate = postedDate;
		}
		public String getPostedText() {
			return postedText;
		}
		public void setPostedText(String postedText) {
			this.postedText = postedText;
		}
		public String getImgurl() {
			return imgurl;
		}
		public void setImgurl(String imgurl) {
			this.imgurl = imgurl;
		}
	    
		
	    
	    
	    
	    
}
