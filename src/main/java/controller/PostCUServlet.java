package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.*;

import bean.Post;
import dao.PostDAO;

public class PostCUServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

			if (request.getParameter("add") != null) {
				Create(request, response, postDAO);
			}
			if (request.getParameter("update") != null) {
				Update(request, response, postDAO);
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

	private void Update(HttpServletRequest request, HttpServletResponse response, PostDAO postDAO)
			throws SQLException, IOException, ServletException {

		Post post = new Post();
		String title = request.getParameter("title");
		String posted_text = request.getParameter("postedText");
		int id = (Integer.parseInt(request.getParameter("update")));

		if (postDAO.updatePost(post, title, posted_text, id)) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			request.setAttribute("message", "更新失敗");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}
	}

	private void Create(HttpServletRequest request, HttpServletResponse response, PostDAO postDAO)
			throws SQLException, IOException, ServletException {

		//Post post = new Post();
		String title = request.getParameter("title");
		String posted_text = request.getParameter("postedText");


		if (postDAO.addPost(title, posted_text)) {
			request.setAttribute("message", "發表成功");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			request.setAttribute("message", "發表失敗");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}

	}


}