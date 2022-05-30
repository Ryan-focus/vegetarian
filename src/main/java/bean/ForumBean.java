package bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="forum")
public class ForumBean implements Serializable {
<<<<<<< Updated upstream

	private String vgeid;
=======
	private static final long serialVersionUID = 1L;
	
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vgeid;
	
	@Column(name = "vgename" ,columnDefinition="NVARCHAR(20)")
>>>>>>> Stashed changes
	private String vgename;
	
	@Column(name = "vgetheme" ,columnDefinition = "nvarchar(50)")
	private String vgetheme;
	
	@Column(name = "vgecontent" ,columnDefinition = "nvarchar(500)")
	private String vgecontent;
<<<<<<< Updated upstream

	public ForumBean(){
	}
=======
	
	@Column(name = "uid" ,columnDefinition="INT")
	private Integer uid;
	
	public ForumBean(){};
>>>>>>> Stashed changes

	public ForumBean(Integer vgeid, String vgename, String vgetheme, String vgecontent) {
		super();
		this.vgeid = vgeid;
		this.vgename = vgename;
		this.vgetheme = vgetheme;
		this.vgecontent = vgecontent;
	}
	public ForumBean(Integer vgeid) {
	}
<<<<<<< Updated upstream
	public String getVgeid() {
=======


	public Integer getVgeid() {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}

>>>>>>> Stashed changes
	
}

