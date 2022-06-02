package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import bean.Order;
import bean.Product;
import bean.Reserve;
import model.HibernateUtils;

public class OrderDao {
	private Connection conn;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
//	public OrderDao(Connection conn) {
//		super();
//		this.conn = conn;
//	}
	SessionFactory factory = HibernateUtils.getSessionFactory();
	Transaction tx =null;
	
	
	
	public List<Order> getAllOrders() {
		
		List<Order> orders = new ArrayList<>();
		try {
			Session session = factory.getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from Order";
			orders = session.createQuery(hql, Order.class).getResultList();
			tx.commit();
			} catch (Exception e) {
				if(tx != null)
					tx.rollback();
				throw new RuntimeException(e);
			}
			return orders;
		}
	

	
	
	public boolean insertOrder(Order model) {
		
		boolean result=false;
		
		try {
			
//			query="insert into orders(p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
			
//			pst = this.conn.prepareStatement(query);
//			pst.setInt(1, model.getPid());
//			pst.setInt(2, model.getUid());
//			pst.setInt(3, model.getQuantity());
//			pst.setString(4, model.getDate());
//			pst.executeUpdate();
			
			Session session = factory.getCurrentSession();
			Transaction tx = null;
			Object key = null;
			try {
				tx = session.beginTransaction();
				key = session.save(model);
		        tx.commit();
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
			e.printStackTrace();
			}
			
			result = true;
			
		}catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		
		return result;
		
	}
//	
//	public Object insert(Order order) {
//		// 取得Session
//		Session session = factory.getCurrentSession();
//		
//		Object key = null;
//		try {
//			tx = session.beginTransaction();
//			key = session.save(order);
//	        tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//		e.printStackTrace();
//		}
//		return key;
//	}
	
	
	
	
	
	
//	
//	public List<Order> userOrders(int id){
//		List<Order> list = new ArrayList<>();
//		
//		try {
//			query="select * from orders where u_id=? order by orders.o_id desc";
//			pst = this.conn.prepareStatement(query);
//			pst.setInt(1, id);
//			rs = pst.executeQuery();
//			
//			while(rs.next()) {
//				Order order = new Order();
//				ProductDao productDao = new ProductDao(this.conn);
//				int pId = rs.getInt("p_id");
//				
//				Product product = productDao.getSingleProduct(pId);
//				order.setOrderId(rs.getInt("o_id"));
//				order.setId(pId);
//				order.setName(product.getName());
//				order.setCategory(product.getCategory());
//				order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
//				order.setQuantity(rs.getInt("o_quantity"));
//				order.setDate(rs.getString("o_date"));
//				list.add(order);
//				
//			}
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		return list;
//		
//	}
	   public void cancelOrder(int pk) {
		   
		   Session session = factory.getCurrentSession();
		   try {
			tx = session.beginTransaction();
			Order order = new Order();
			order.setOrderId(pk);
			session.delete(order);
			tx.commit();
			
		} catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	        //boolean result = false;
//	        try {
//	            query = "delete from orders where o_id=?";
//	            pst = this.conn.prepareStatement(query);
//	            pst.setInt(1, id);
//	            pst.execute();
//	            //result = true;
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	            System.out.print(e.getMessage());
//	        }
	        //return result;
	    }
	
}
