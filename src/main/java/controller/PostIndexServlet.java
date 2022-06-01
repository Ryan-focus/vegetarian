package controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.List;


import Interface.PostService;
import bean.*;
import dao.PostHibernateServiceImpl;

public class PostIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			showAllPost(request, response);
		} catch (SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showAllPost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		PostService pService = new PostHibernateServiceImpl();
		List<Post> findallPost = pService.findallPost();

		if (pService.findallPost() != null) {
			request.setAttribute("postlist", findallPost);
			request.getRequestDispatcher("./postsIndex.jsp").forward(request, response);

		} else {
			request.setAttribute("message", "失敗");
			request.getRequestDispatcher("showResultForm.jsp").forward(request, response);
		}
	}
}