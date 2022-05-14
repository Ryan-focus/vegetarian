package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;



/**
 * Servlet implementation class BusinessRegisterServlet
 */
@WebServlet("/BusinessRegisterServlet")
public class BusinessRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;UTF-8");
		
		UserDAO userDAO = new UserDAO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		boolean isEmailExist = false;
		
		try {
			isEmailExist = userDAO.checkEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (!isEmailExist) userDAO.businessRegister(email, password, username);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", isEmailExist ? "失敗!" : "成功~");
		
		request.getRequestDispatcher("/RegisterResult.jsp").forward(request, response);
	}

}
