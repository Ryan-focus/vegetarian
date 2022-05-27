package bean;

import java.io.Serializable;

public class ForumBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vgeid;
	private String vgename;
	private String vgetheme;
	private String vgecontent;
	private int uid;

	public ForumBean(){
	}

	public ForumBean(String vgeid, String vgename, String vgetheme, String vgecontent) {
		
		this.vgeid = vgeid;
		this.vgename = vgename;
		this.vgetheme = vgetheme;
		this.vgecontent = vgecontent;
	}
	public ForumBean(String vgeid) {
	}


	public String getVgeid() {
		return vgeid;
	}

	public void setVgeid(String vgeid) {
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
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	
}

