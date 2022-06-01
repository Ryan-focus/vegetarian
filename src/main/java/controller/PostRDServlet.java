package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


import Interface.PostService;
import bean.Post;
import dao.PostHibernateServiceImpl;

public class PostRDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			String action = request.getParameter("action");
			switch (action) {
			case "showPost":
				showPost(request, response);
				break;
			case "deletePost":
				deletePost(request, response);
				break;
			case "editPost":
				editPost(request, response);
				break;
			}
		} catch (SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showPost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		PostService pService = new PostHibernateServiceImpl();
		int id = Integer.parseInt(request.getParameter("id"));

		Post post = pService.findPost(id);
		if (pService.findPost(id) != null) {
			request.setAttribute("post", post);
			request.getRequestDispatcher("/showPost").forward(request, response);

		} else {
			request.setAttribute("message", "失敗");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}
	}

	private void deletePost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		PostService pService = new PostHibernateServiceImpl();
		int id = Integer.parseInt(request.getParameter("id"));

		if (pService.deletePost(id)) {
			request.setAttribute("message", "刪除成功");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			request.setAttribute("message", "刪除失敗");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}
	}

	private void editPost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		PostService pService = new PostHibernateServiceImpl();
		int id = Integer.parseInt(request.getParameter("id"));
		Post post = pService.findPost(id);

		if (pService.findPost(id) != null) {
			request.setAttribute("post", post);
			request.getRequestDispatcher("/editPost").forward(request, response);
		} else {
			request.setAttribute("message", "錯誤");
			request.getRequestDispatcher("showResultForm.jsp").forward(request, response);
		}
	}

}