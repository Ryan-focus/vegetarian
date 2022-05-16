package com.search;

public class Restaurant {

	private Integer restaurantNumber;
	private String restaurantName;
	private String restaurantTel;
	private String restaurantAddress;
	private String restaurantCategory;
	private String restaurantType;
	private String restaurantBusinessHours;
	private String restaurantScore;
	
	public Restaurant() {};
	public Restaurant(Integer restaurantNumber, String restaurantName, String restaurantTel, String restaurantAddress, String restaurantCategory, String restaurantType,
			String restaurantBusinessHours, String restaurantScore) {
		super();
		this.restaurantNumber = restaurantNumber;
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
	
}
