package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import bean.*;

public class ProductDao {

	private Connection conn;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public ProductDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public List<Product> getAllProducts() {

		List<Product> products = new ArrayList<Product>();

		try {
			query = "select * from products";
			pst = this.conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));

				products.add(row);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return products;

	}

	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		List<Cart> products = new ArrayList<Cart>();

		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					query = "select * from products where id =?";
					pst = this.conn.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
						
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}

		return products;

	}
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					query = "select price from products where id=?";	
					pst = this.conn.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					
					while(rs.next()) {
						sum+=rs.getDouble("price")*item.getQuantity();
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return sum;
		
	}
	 public Product getSingleProduct(int id) {
		 Product row = null;
	        try {
	            query = "select * from products where id=? ";

	            pst = this.conn.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	row = new Product();
	                row.setId(rs.getInt("id"));
	                row.setName(rs.getString("name"));
	                row.setCategory(rs.getString("category"));
	                row.setPrice(rs.getDouble("price"));
	                row.setImage(rs.getString("image"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return row;
	    }
	 	//新增商品
	   public boolean addProducts(Product newProduct) {

	        boolean check = false;
	        // 定義處理ＳＱＬ
	        String sqlStr = "insert into products(name,category,price," +
	                "images) values(?,?,?,?,?)";
	        try {
	        	
	            PreparedStatement prepstmt;
	            Product row =newProduct;
	            prepstmt = this.conn.prepareStatement(sqlStr);
	            prepstmt.setString(1, row.getName());
	            prepstmt.setString(2, row.getCategory());
	            prepstmt.setDouble(3, row.getPrice());
	            prepstmt.setString(4, row.getImage());
	           
	           
	         
	            check = prepstmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return check;
	    }
}
