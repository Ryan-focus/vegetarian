package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bean.Cart;
import bean.Order;
import bean.Product;
import bean.User;
import dao.OrderDao;
import dao.ProductDao;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource ds = null;
	Connection conn = null;

	{
		try {
			InitialContext ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	ProductDao pd =new ProductDao();
	OrderDao od = new OrderDao();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		switch (action) {

		case "cart-check-out": {
			checkOut(request, response);
		}
			break;
	
		case "cancel-order": {
			cancelOrder(request, response);
		}
			break;
		case "add-to-cart": {
			addToCart(request, response);
		}
			break;
		case "remove-from-cart": {
			removeFromCart(request, response);
		}
			break;
		case "show-all-products": {
			showAllProduct(request, response);
		}
		break;
		case "show-all-orders": {
			showAllOrder(request, response);
		}
		break;
		case "delete-product": {
			deleteProduct(request, response);
		}
		break;
	
		default:{
			response.sendRedirect("/vegetarian/shoppingcartIndex");
		}
		}
	}
	
	private void showAllOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user!=null&&user.getUid()!=null) {
			
			List<Order> orders = od.getAllOrders();
			List<Product> products = new ArrayList<Product>();
			
			for (Order order : orders) {
				Product product = new Product();
				product = pd.getSingleProduct(order.getPid());
				products.add(product);
			}
			request.getSession().setAttribute("productlist", products);
			request.getSession().setAttribute("orders", orders);
			response.sendRedirect("/vegetarian/order");
		}else {
			response.sendRedirect("/vegetarian/Login");
		}
		
		
	}

	private void showAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Optional<Object> allproducts = Optional.ofNullable(session.getAttribute("products"));
		if(!allproducts.isPresent()) {
			List<Product> products = pd.getAllProducts();
			request.getSession().setAttribute("products", products);
		}
		response.sendRedirect("/vegetarian/shoppingcartIndex");
	}

	private void checkOut(HttpServletRequest request, HttpServletResponse response) {
		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			// retrive all cart products
			@SuppressWarnings("unchecked")
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cartList");
			// user authentication
			User user = (User) request.getSession().getAttribute("user");

			
			if (cart_list != null && user!=null) {

				for (Cart c : cart_list) {
					// prepare the order object
					Order order = new Order();
					order.setPid(Integer.parseInt((String) request.getSession().getAttribute("id")));
					order.setUid(user.getUid());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));

					// instantiate the dao class
					OrderDao oDao = new OrderDao();
					// calling the insert method
					boolean result = oDao.insertOrder(order);
					if (!result)
						break;
				}

				cart_list.clear();
				response.sendRedirect("/vegetarian/order");

			} else {
				if (user.getUid() == 0)
					response.sendRedirect("/vegetarian/Login");
				response.sendRedirect("/vegetarian/cart");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
//
	private void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try (PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if (id != null) {
				od.cancelOrder(Integer.parseInt(id));
			}
			response.sendRedirect("ShoppingCartServlet?action=show-all-orders");
		}

	}
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try (PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if (id != null) {
				
				ProductDao productDao = new ProductDao();
				productDao.delProducts(Integer.parseInt(id));
				
			}
			response.sendRedirect("/vegetarian/backend.jspf");
		}
		
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			ArrayList<Cart> cartList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));
			Product product = pd.getSingleProduct(id);
			Cart cart = new Cart();
			cart.setId(id);
			cart.setQuantity(1);
			cart.setName(product.getName());
			cart.setCategory(product.getCategory());
			cart.setImage(product.getImage());
			System.out.println(product.getName());

			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cartList");
			System.out.println(cart.getCategory());

			if (cart_list == null) {
				cartList.add(cart);
				session.setAttribute("cartList", cartList);
				response.sendRedirect("/vegetarian/shoppingcartIndex");
			} else {
				cartList = cart_list;
				boolean exist = false;

				for (Cart c : cart_list) {
					if (c.getId() == id) {
						exist = true;
						out.println(
								"<h3 style='color:crimson; text-align:center'>此商品已加入購物車<a href='/vegetarian/cart'>點此跳轉</a></h3>");
					}
				}
				if (!exist) {
					cartList.add(cart);
					response.sendRedirect("/vegetarian/shoppingcartIndex");
				}
			}

		}

	}
	@SuppressWarnings("unchecked")
	private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String bookid = request.getParameter("id");
			if (bookid != null) {
				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if (cart_list != null) {
					for (Cart c : cart_list) {
						if (c.getId() == Integer.parseInt(bookid)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}
				}
				response.sendRedirect("/vegetarian/cart");
			} else {
				response.sendRedirect("/vegetarian/cart");
			}
		}

	}

}
