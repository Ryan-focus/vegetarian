package bean;

import java.io.Serializable;
import java.util.Date;

public class Reserve implements Serializable{
private static final long serialVersionUID = 1L;
	/** @author Raven
	 * 
	 */
	
	private Integer id;
	private Date date;
	private int count;
	private String orderDate;
	private int restaurantNumber;
	private int uid;



	public Reserve() {
		super();
	}
	

	public Reserve(Integer id, Date date, int count, String orderDate,int restaurantNumber ,int uid) {
		super();
		this.date = date;
		this.count = count;
		this.orderDate = orderDate;
		this.restaurantNumber = restaurantNumber;
		this.uid = uid;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getRestaurantNumber() {
		return restaurantNumber;
	}

	public void setRestaurantNumber(int restaurantNumber) {
		this.restaurantNumber = restaurantNumber;
	}
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
