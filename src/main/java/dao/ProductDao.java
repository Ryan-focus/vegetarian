package dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import bean.Cart;
import bean.Product;
import model.HibernateUtils;

public class ProductDao {

//	private Connection conn;
//	private String query;
//	private PreparedStatement pst;
//	private ResultSet rs;
	
	
	SessionFactory factory = HibernateUtils.getSessionFactory();
	

	
	Transaction tx =null;

	public List<Product> getAllProducts(){

		List<Product> products = new ArrayList<Product>();
		Session session = factory.getCurrentSession();
		try {
			tx = session.beginTransaction();
			String hql = "from Product";
			products = session.createQuery(hql, Product.class).getResultList();
			tx.commit();
			} catch (Exception e) {
				if(tx != null)
					tx.rollback();
				throw new RuntimeException(e);
			}
			return products;
		}

		public void close() {
			factory.close();
		}	
//		try {
//			query = "select * from products";
//			pst = this.conn.prepareStatement(query);
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				Product row = new Product();
//				row.setId(rs.getInt("id"));
//				row.setName(rs.getString("name"));
//				row.setCategory(rs.getString("category"));
//				row.setPrice(rs.getDouble("price"));
//				row.setImage(rs.getString("image"));
//
//				products.add(row);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return products;
		


	

	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		Session session = factory.getCurrentSession();
		List<Cart> products = new ArrayList<Cart>();

		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					getSingleProduct(item.getId());
//					query = "select * from products where id =?";
//					pst = this.conn.prepareStatement(query);
//					pst.setInt(1, item.getId());
//					rs = pst.executeQuery();
//					while (rs.next()) {
//						Cart row = new Cart();
//						row.setId(rs.getInt("id"));
//						row.setName(rs.getString("name"));
//						row.setCategory(rs.getString("category"));
//						row.setPrice(rs.getDouble("price")*item.getQuantity());
//						row.setQuantity(item.getQuantity());
//						products.add(row);
//						
//					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}

		return products;

	}
//	
//	public double getTotalCartPrice(ArrayList<Cart> cartList) {
//		double sum = 0;
//		
//		try {
//			if(cartList.size()>0) {
//				for(Cart item:cartList) {
//					query = "select price from products where id=?";	
//					pst = this.conn.prepareStatement(query);
//					pst.setInt(1, item.getId());
//					rs=pst.executeQuery();
//					
//					while(rs.next()) {
//						sum+=rs.getDouble("price")*item.getQuantity();
//					}
//				}
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	
//		return sum;
//		
//	}
	 public Product getSingleProduct(int id) {
		 
		 Product row = null;
		 Session session = factory.getCurrentSession();
		
	        try {
	        	tx = session.beginTransaction();
				
				row = (Product) session.get(Product.class, id);
				
			    tx.commit();
	            
	        } catch (Exception e) {
	        	if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
	        }

	        return row;
//		 Product row = null;
//		 Session session = factory.getCurrentSession();
//	        try {
//	            query = "select * from products where id=? ";
//
//	            pst = this.conn.prepareStatement(query);
//	            pst.setInt(1, id);
//	            ResultSet rs = pst.executeQuery();
//
//	            while (rs.next()) {
//	            	row = new Product();
//	                row.setId(rs.getInt("id"));
//	                row.setName(rs.getString("name"));
//	                row.setCategory(rs.getString("category"));
//	                row.setPrice(rs.getDouble("price"));
//	                row.setImage(rs.getString("image"));
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            System.out.println(e.getMessage());
//	        }
//
//	        return row;
	    }

	   
	   // 删除商品
	    public void delProducts(int id) {
	    	
	    	 Session session = factory.getCurrentSession();
	 		Transaction tx = null;
	 		try {
	 			tx = session.beginTransaction();
	 			Product product = new Product();
	 			product.setId(id);
	 			session.delete(product);	 			
	 			tx.commit();
	 			
	 		}catch (Exception ex) {
	 			if(tx!=null) {
	 				tx.rollback();
	 			}
	 			throw new RuntimeException(ex);
	 		}	
	       
	    }
}
