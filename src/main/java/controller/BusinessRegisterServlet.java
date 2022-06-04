package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDAO;

@WebServlet("/BusinessRegisterServlet")
public class BusinessRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;UTF-8");
		
		UserDAO userDAO = new UserDAO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		boolean isEmailExist = false;
		
			isEmailExist = userDAO.checkEmail(email);

			if (!isEmailExist) {
				User user = new User();
				user.setEmail(email);
				user.setPassword(password);
				user.setUsername(username);
				userDAO.businessRegister(user);
			}
		
		request.setAttribute("isEmailExist", isEmailExist);
		request.setAttribute("result", isEmailExist ? "失敗" : "成功~");
		
		request.getRequestDispatcher("/WEB-INF/jsp/RegisterResult.jsp").forward(request, response);
	}

}
