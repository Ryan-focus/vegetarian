package bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "reserve")
public class Reserve implements Serializable{
private static final long serialVersionUID = 1L;
	/** @author Raven
	 * 
	 */
@Id
// 當使用IDENTITY時，主要鍵的資料型態必須是整數或符點數，不可以為char或String
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//方便測試沒有設定任何的notNull限制
@Column(name = "reservationDate" ,columnDefinition="DATE")
// @Temporal只能用在java.util.Date, @Temporal(TemporalType.DATE)表示刪除時分秒，僅保留年月日。
@Temporal(TemporalType.DATE)  
	private Date date;
@Column(name = "reservationCount" ,columnDefinition="INT")
	private Integer count;
@Column(name = "orderDate" ,columnDefinition="NVARCHAR(20)")
	private String orderDate;
@Column(name = "restaurantNumber" ,columnDefinition="INT")
	private Integer restaurantNumber;
@Column(name = "uid" ,columnDefinition="INT")
	private Integer uid;
	
	public Reserve() {
		super();
	}
	
	
	public Reserve(Integer id, Date date, Integer count, String orderDate, Integer restaurantNumber, Integer uid) {
		super();
		this.id = id;
		this.date = date;
		this.count = count;
		this.orderDate = orderDate;
		this.restaurantNumber = restaurantNumber;
		this.uid = uid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public Integer getRestaurantNumber() {
		return restaurantNumber;
	}
	public void setRestaurantNumber(Integer restaurantNumber) {
		this.restaurantNumber = restaurantNumber;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}




}
