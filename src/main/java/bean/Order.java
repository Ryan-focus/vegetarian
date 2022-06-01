package bean;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="orders")
public class Order extends Product{
	
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
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "p_id", referencedColumnName = "id")
	private List<Order> orders = new ArrayList<>();
	
	public Order() {}

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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order(Integer id, String name, String category, Double price, String image, Integer orderId,
			Integer quantity, String date, Integer uid, List<Order> orders) {
		super(id, name, category, price, image);
		this.orderId = orderId;
		this.quantity = quantity;
		this.date = date;
		this.uid = uid;
		this.orders = orders;
	}


}
