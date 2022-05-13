package controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;UTF-8");
		
		UserDAO userDAO = new UserDAO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String result ="";
		
		boolean isAccountExist = false;
		
		try {
			isAccountExist = userDAO.login(email, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			request.setAttribute("result", result = isAccountExist ? "成功~" : "失敗!");
			
		request.getRequestDispatcher("/LoginResult.jsp").forward(request, response);
	}

}