package bean;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;


public class Post implements Serializable{
	  
		private int postId;
	    private String title;
	    private Date postedDate;
	    private String postedText;
	    
	    
	    
	    public Post(int postId,String title,Date postedDate,String postedText) {
	    	
	    	this.postId =postId;
	    	this.title = title;
	    	this.postedDate = postedDate;
	    	this.postedText = postedText;
	    	
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
	    
	    
		
	    
	    
	    
	    
}
