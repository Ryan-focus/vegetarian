package bean;


public class Cart {
	
	private int quantity;
	private int id;
	private String name;
	private String category;
	private String image;
	private String price;
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Cart() {
		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Cart(int quantity, int id, String name, String category, String image, String price) {
		super();
		this.quantity = quantity;
		this.id = id;
		this.name = name;
		this.category = category;
		this.image = image;
		this.price = price;
	}

	
	
}
