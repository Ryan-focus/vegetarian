package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



public class Order extends Product{
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="o_id")
	private Integer orderId;
	@Column(name="p_id" ,columnDefinition="INT NOT NULL")
	private Integer uid;
	@Column(name="o_quantity" ,columnDefinition="INT NOT NULL")
	private Integer quantity;
	@Column(name="o_date" ,columnDefinition="VARCHAR(450) NOT NULL")
	private String date;
	
	public Order() {};

	public Order(int id, String name, String category, double price, String image, int orderId, int uid, int quantity,
			String date) {
		super(id, name, category, price, image);
		this.orderId = orderId;
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
	}

	public Order(int id, String name, String category, double price, String image, int uid, int quantity, String date) {
		super(id, name, category, price, image);
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", uid=" + uid + ", quantity=" + quantity + ", date=" + date + "]";
	}
	
	
	
}
