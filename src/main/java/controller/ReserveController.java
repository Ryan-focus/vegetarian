package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Reserve;
import bean.Restaurant;
import bean.User;

import Interface.IReserveDAO;

import dao.ReserveDAO;

/**
 * Servlet implementation class ReserveController
 */
@WebServlet("/Reserve")
public class ReserveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		IReserveDAO rDao = new ReserveDAO();
		Reserve reserve = new Reserve();
		
		boolean isSuccess = rDao.insert(reserve);
		//英傑借我跳轉一下 測試用
		request.setAttribute("isEmailExist", isSuccess);
		request.setAttribute("result", isSuccess ? "失敗" : "成功");
				
		request.getRequestDispatcher("/WEB-INF/jsp/RegisterResult.jsp").forward(request, response);
	}

}
