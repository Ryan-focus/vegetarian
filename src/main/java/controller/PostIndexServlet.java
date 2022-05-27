package controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

import bean.*;
import dao.PostDAO;

public class PostIndexServlet extends HttpServlet {
	
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
			PostDAO postDAO= new PostDAO(conn);

			// 憒��楊蝣澆�潛UTF-8
			request.setCharacterEncoding("UTF-8");
			
			
			showAllPost(request, response, postDAO);
			

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

	private void showAllPost(HttpServletRequest request, HttpServletResponse response, PostDAO postDAO)
			throws SQLException, IOException, ServletException {


		List<Post> findallPost = postDAO.findallPost();

		if (postDAO.findallPost() != null) {
			request.setAttribute("postlist",findallPost);
			request.getRequestDispatcher("./postsIndex.jsp").forward(request, response);

		} else {
			request.setAttribute("message", "憭望��");
			request.getRequestDispatcher("showResultForm.jsp").forward(request, response);
		}
	}
}