package controller;
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import bean.User;
import dao.UserDAO;

import java.sql.*;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;UTF-8");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String identity = "";
		
		UserDAO userDAO = new UserDAO();

		try {
			
			User user = userDAO.login(email, password);
				
		if (user != null) {
		switch (user.getStatus()) {
		
		case "administrator":
			identity = "管理員";
			request.setAttribute("urlStatus", 0 );
			request.getSession().setAttribute("Auth", user);
			break;
			
		case "business":
			identity = "商家";
			request.setAttribute("urlStatus", 1 );
			request.getSession().setAttribute("Auth", user);
			break;
			
		case "user":
			identity = "會員";
			request.setAttribute("urlStatus", 2 );
			request.getSession().setAttribute("Auth", user);
			break;
			
		default:
			request.setAttribute("urlStatus", 3 );
			break;
			
		}
	  } else {
		    request.setAttribute("urlStatus", 3 );
	  }
		
		if (user != null) {
			
			request.setAttribute("result", identity + user.getUsername() + "登入成功~" );
			
		} else {
			
			request.setAttribute("result", "登入失敗!");
			
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/LoginResult.jsp").forward(request, response);
		
	}

}