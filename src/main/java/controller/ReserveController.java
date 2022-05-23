package controller;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Reserve;

import Interface.IReserveDAO;

import dao.ReserveDAO;

/**
 * Servlet implementation class ReserveController
 */
@WebServlet("/reserve")
public class ReserveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IReserveDAO rDao = new ReserveDAO();
	Reserve reserve = new Reserve();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		if(request.getParameter("oRestaurant") != null) {
			goToForm(request,response);
		}
		else {
			try {
				order(request,response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void goToForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurantNumber = request.getParameter("restaurantNumber").toString();
		String restauranName = request.getParameter("restaurantName").toString();
		request.setAttribute("restaurantNumber", restaurantNumber);
		request.setAttribute("restauranName", restauranName);
		if (restauranName != null) {
			request.getRequestDispatcher("reservationIndex.jsp").forward(request, response);
		}
	}
	
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String oDate =request.getParameter("orderdate");
		int count = Integer.valueOf(request.getParameter("memberCount").toString());
		int restaurantNumber = Integer.valueOf(request.getParameter("restaurantNumber").toString());
		int uid = Integer.valueOf(request.getParameter("userID").toString());
		
		Date orderDate=new SimpleDateFormat("yyyy-MM-dd").parse(oDate);
		
        reserve.setDate(orderDate);
        reserve.setCount(count);
        reserve.setRestaurantNumber(restaurantNumber);
        reserve.setUid(uid);

		boolean isSuccess = rDao.insert(reserve);
		//英傑借我跳轉一下 測試用
		request.setAttribute("isEmailExist", isSuccess);
		request.setAttribute("result", isSuccess ? "成功" : "失敗");
		
		request.getRequestDispatcher("/WEB-INF/jsp/RegisterResult.jsp").forward(request, response);
	}
	
}
