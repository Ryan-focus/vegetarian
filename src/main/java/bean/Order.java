package bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="orders")
public class Order implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="o_id")
	private Integer orderId;
	@Column(name="o_quantity" ,columnDefinition="INT NOT NULL")
	private Integer quantity;
	@Column(name="o_date" ,columnDefinition="VARCHAR(450) NOT NULL")
	private String date;
	@Column(name="u_id" ,columnDefinition="INT NOT NULL")
	private Integer uid;
	@Column(name="p_id" ,columnDefinition="INT NOT NULL")
	private Integer pid;
	

	
	public Order(Integer orderId, Integer quantity, String date, Integer uid, Integer pid) {
		super();
		this.orderId = orderId;
		this.quantity = quantity;
		this.date = date;
		this.uid = uid;
		this.pid = pid;
	}

	
	public Order() {
		super();
	}


	public Order(int orderId2) {
		// TODO Auto-generated constructor stub
	}


	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}


}
