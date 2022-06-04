package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Cart;
import bean.Order;
import bean.User;
import dao.OrderDao;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			// retrive all cart products
			@SuppressWarnings("unchecked")
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cartList");
			// user authentication
			User user = (User) request.getSession().getAttribute("user");

			
			if (cart_list != null &&  user!=null) {

				for (Cart c : cart_list) {
					// prepare the order object
					Order order = new Order();
					order.setPid(c.getId());
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
				response.sendRedirect("ShoppingCartServlet?action=show-all-orders");

			} else {
					response.sendRedirect("/vegetarian/Login");
				response.sendRedirect("/vegetarian/cart");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
