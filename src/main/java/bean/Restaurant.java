package bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="restaurant")
public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	// 當使用IDENTITY時，主要鍵的資料型態必須是整數或符點數，不可以為char或String
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer restaurantNumber;
	@Column(columnDefinition="NVARCHAR(20) NOT NULL")  
	private String restaurantName;
	@Column(columnDefinition="NVARCHAR(15)")  
	private String restaurantTel;
	@Column(columnDefinition="NVARCHAR(50) NOT NULL")  
	private String restaurantAddress;
	@Column(columnDefinition="NVARCHAR(20)")  
	private String restaurantCategory;
	@Column(columnDefinition="NVARCHAR(20)")  
	private String restaurantType;
	@Column(columnDefinition="NVARCHAR(200)")  
	private String restaurantBusinessHours;
	@Column(columnDefinition="NVARCHAR(10)")  
	private String restaurantScore;
	@Column(columnDefinition="NVARCHAR(MAX)")  
	private String restaurantMap;
	
	public Restaurant() {};
	public Restaurant(Integer restaurantNumber, String restaurantName, String restaurantTel, String restaurantAddress, String restaurantCategory, String restaurantType,
			String restaurantBusinessHours, String restaurantScore,String restaurantMap) {
		super();
		this.restaurantNumber = restaurantNumber;
		this.restaurantName = restaurantName;
		this.restaurantTel = restaurantTel;
		this.restaurantAddress = restaurantAddress;
		this.restaurantCategory = restaurantCategory;
		this.restaurantType = restaurantType;
		this.restaurantBusinessHours = restaurantBusinessHours;
		this.restaurantScore = restaurantScore;
		this.restaurantMap = restaurantMap;
	}
	
	//新增餐廳的建構子
	public Restaurant(String restaurantName, String restaurantTel, String restaurantAddress, String restaurantCategory, String restaurantType,
			String restaurantBusinessHours, String restaurantScore) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantTel = restaurantTel;
		this.restaurantAddress = restaurantAddress;
		this.restaurantCategory = restaurantCategory;
		this.restaurantType = restaurantType;
		this.restaurantBusinessHours = restaurantBusinessHours;
		this.restaurantScore = restaurantScore;
	}
	
	//各屬性的get&set
	
	public Integer getRestaurantNumber() {
		return restaurantNumber;
	}
	public void setRestaurantNumber(Integer restaurantNumber) {
		this.restaurantNumber = restaurantNumber;
	}
	
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	
	public String getRestaurantTel() {
		return restaurantTel;
	}
	public void setRestaurantTel(String restaurantTel) {
		this.restaurantTel = restaurantTel;
	}
	
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	
	public String getRestaurantCategory() {
		return restaurantCategory;
	}
	public void setRestaurantCategory(String restaurantCategory) {
		this.restaurantCategory = restaurantCategory;
	}
	
	public String getRestaurantType() {
		return restaurantType;
	}
	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}
	
	public String getRestaurantBusinessHours() {
		return restaurantBusinessHours;
	}
	public void setRestaurantBusinessHours(String restaurantBusinessHours) {
		this.restaurantBusinessHours = restaurantBusinessHours;
	}
	
	public String getRestaurantScore() {
		return restaurantScore;
	}
	public void setRestaurantScore(String restaurantScore) {
		this.restaurantScore = restaurantScore;
	}
	public String getRestaurantMap() {
		return restaurantMap;
	}
	public void setRestaurantMap(String restaurantMap) {
		this.restaurantMap = restaurantMap;
	}
	
}
