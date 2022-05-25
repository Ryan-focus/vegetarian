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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {

			// 建立Context Object,連到JNDI Server
			ctxt = new InitialContext();

			// 使用JNDI API找到DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");

			// 向DataSource要Connection
			conn = ds.getConnection();

			// 建立Database Access Object,負責Table的Access
			PostDAO postDAO = new PostDAO(conn);

			// 如果要編碼值為UTF-8
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
			request.setAttribute("message", "失敗");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}
	}

	private void deletePost(HttpServletRequest request, HttpServletResponse response, PostDAO postDAO)
			throws SQLException, IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));

		
		if (postDAO.deletePost(id)) {
			request.setAttribute("message", "刪除成功");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			request.setAttribute("message", "刪除失敗");
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
			request.setAttribute("message", "失敗");
			request.getRequestDispatcher("showResultForm.jsp").forward(request, response);
		}
	}

}