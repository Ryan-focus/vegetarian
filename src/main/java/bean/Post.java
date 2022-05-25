package bean;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;


public class Post implements Serializable{
	  
		private int postId;
	    private String title;
	    private Date postedDate;
	    private String postedText;
	    private String imgurl;
	    
	    
	    
	    public Post(int postId,String title,Date postedDate,String postedText,String imgurl) {
	    	
	    	this.postId =postId;
	    	this.title = title;
	    	this.postedDate = postedDate;
	    	this.postedText = postedText;
	    	this.imgurl = imgurl;
	    	
	    }
	    
		public Post() {
			
		}

		public int getPostId() {
			return postId;
		}
		public void setPostId(int postId) {
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
