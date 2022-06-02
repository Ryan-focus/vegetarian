package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="forum")
public class ForumBean{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Integer vgeid;
	 String vgename;	
	 String vgetheme;
	 String vgecontent;
	 Integer uid;
	 
	public ForumBean() {
		super();
	}
	public ForumBean(Integer vgeid, String vgename, String vgetheme, String vgecontent, Integer uid) {
		super();
		this.vgeid = vgeid;
		this.vgename = vgename;
		this.vgetheme = vgetheme;
		this.vgecontent = vgecontent;
		this.uid=uid;
	}
	public ForumBean(Integer vgeid, String vgename, String vgetheme, String vgecontent) {
		super();
		this.vgeid = vgeid;
		this.vgename = vgename;
		this.vgetheme = vgetheme;
		this.vgecontent = vgecontent;
	}
	 
	public Integer getVgeid() {
		return vgeid;
	}
	public void setVgeid(Integer vgeid) {
		this.vgeid = vgeid;
	}
	public String getVgename() {
		return vgename;
	}
	public void setVgename(String vgename) {
		this.vgename = vgename;
	}
	public String getVgetheme() {
		return vgetheme;
	}
	public void setVgetheme(String vgetheme) {
		this.vgetheme = vgetheme;
	}
	public String getVgecontent() {
		return vgecontent;
	}
	public void setVgecontent(String vgecontent) {
		this.vgecontent = vgecontent;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	

	

	
}

