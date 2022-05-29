package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.*;

import bean.Post;
import dao.PostDAO;

public class PostRDServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {

			// 撱箇�ontext Object,���JNDI Server
			ctxt = new InitialContext();

			// 雿輻JNDI API��DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");

			// ��ataSource閬onnection
			conn = ds.getConnection();

			// 撱箇�atabase Access Object,鞎痊Table��ccess
			PostDAO postDAO = new PostDAO(conn);

			// 憒��楊蝣澆�潛UTF-8
			request.setCharacterEncoding("UTF-8");

		

			String action = request.getParameter("action");
			switch (action) {
			case "showPost":
				showPost(request, response, postDAO);
				break;
			case "deletePost":
				deletePost(request, response, postDAO);
				break;
			case "editPost":
				editPost(request, response, postDAO);
				break;
			}

		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}


	private void showPost(HttpServletRequest request, HttpServletResponse response, PostDAO postDAO)
			throws SQLException, IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));

		Post post = postDAO.findPost(id);

		if (postDAO.findPost(id) != null) {
			request.setAttribute("title", post.getTitle());
			request.setAttribute("posted_date", post.getPostedDate());
			request.setAttribute("posted_text", post.getPostedText());
			request.setAttribute("posted_Imgurl", post.getImgurl());
			request.getRequestDispatcher("/showPost").forward(request, response);

		} else {
			request.setAttribute("message", "憭望��");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}
	}

	private void deletePost(HttpServletRequest request, HttpServletResponse response, PostDAO postDAO)
			throws SQLException, IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));

		
		if (postDAO.deletePost(id)) {
			request.setAttribute("message", "������");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			request.setAttribute("message", "��憭望��");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}
	}

	private void editPost(HttpServletRequest request, HttpServletResponse response, PostDAO postDAO)
			throws SQLException, IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));

		Post post = postDAO.findPost(id);

		if (postDAO.findPost(id) != null) {
			request.setAttribute("title", post.getTitle());
			request.setAttribute("post_id", post.getPostId());
			request.setAttribute("posted_date", post.getPostedDate());
			request.setAttribute("posted_text", post.getPostedText());
			request.getRequestDispatcher("/editPost").forward(request, response);
		} else {
			request.setAttribute("message", "憭望��");
			request.getRequestDispatcher("showResultForm.jsp").forward(request, response);
		}
	}

}