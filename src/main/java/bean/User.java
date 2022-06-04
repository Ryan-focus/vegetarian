package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
    private String email;
    private String password;
    private String username;
    private String status;
    
    public User() {}
    
    public User(String email, String password) {
    	this.email = email;
    	this.password = password;
    }
    
    public User(String email) {
    	this.email = email;
    }
    
    public User(String email, String password, String username, String status) {
    	this.email = email;
    	this.password = password;
    	this.username = username;
    	this.status = status;
    }
    
    public User(Integer uid, String email, String password, String username, String status) {
    	this.uid = uid;
    	this.email = email;
    	this.password = password;
    	this.username = username;
    	this.status = status;
    }
    

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
    
	@Override
	public String toString() {
		return "User[uid=" + uid + ",email=" + email + ",password=" + password + 
				",username=" + username + ",status=" + status + "]";  
	}
	
}
