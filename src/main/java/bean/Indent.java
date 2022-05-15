package bean;

import java.io.Serializable;

public class Indent implements Serializable{

    private int indentId;
    private User user;
    private float totalPrice;

    public Indent() {
    }

    public int getIndentId() {
        return indentId;
    }

    public void setIndentId(int indentId) {
        this.indentId = indentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

	public Indent(int indentId, User user, float totalPrice) {
		super();
		this.indentId = indentId;
		this.user = user;
		this.totalPrice = totalPrice;
	}
    
}