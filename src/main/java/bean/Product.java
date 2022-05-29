package bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.coyote.http11.filters.SavedRequestInputFilter;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name",columnDefinition="VARCHAR(450)")
	private String name;
	@Column(name="category",columnDefinition="VARCHAR(450)")
	private String category;
	@Column(name="price",columnDefinition="DOUBLE")
	private double price;
	@Column(name="image",columnDefinition="VARCHAR(450)")
	private String image;
	public Product(int id, String name, String category, double price, String image) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.image = image;
	}
	public Product() {
		super();
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", image="
				+ image + "]";
	}
	

}
