package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import javax.naming.*;
import javax.sql.*;

import bean.Post;
import dao.PostDAO;

public class PostServlet extends HttpServlet {
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
			PostDAO postDao2 = new PostDAO(conn);

			// 如果要編碼值為UTF-8
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("add") != null) {
				Create(request, response, postDao2);
			}
			if (request.getParameter("update") != null) {
				Update(request, response, postDao2);
			}

			String action = request.getParameter("action");
			switch (action) {
			case "showPost":
				showPost(request, response, postDao2);
				break;
			case "deletePost":
				deletePost(request, response, postDao2);
				break;
			case "editPost":
				editPost(request, response, postDao2);
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

	private void Update(HttpServletRequest request, HttpServletResponse response, PostDAO postDao2)
			throws SQLException, IOException {

		Post post = new Post();
		String title = request.getParameter("title");
		String posted_text = request.getParameter("postedText");
		int id = (Integer.parseInt(request.getParameter("update")));

		if (postDao2.updatePost(post, title, posted_text, id))
			showForm(response, "更新成功");
		else {
			showError(response, " update failure");
		}
	}

	private void Create(HttpServletRequest request, HttpServletResponse response, PostDAO postDao2)
			throws SQLException, IOException {

		Post post = new Post();
		String title = request.getParameter("title");
		String posted_text = request.getParameter("postedText");
		Date time = post.getPostedDate();

		// boolean isSucess = postDao2.addPost(post,title,posted_text);

		if (postDao2.addPost(title, posted_text))
			showForm(response, "發表成功");
		else {
			showError(response, " failure");
		}

	}

	private void showPost(HttpServletRequest request, HttpServletResponse response, PostDAO postDao2)
			throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		Post post = postDao2.findPost(id);

		if (postDao2.findPost(id) != null)
			showFindForm(response, post);
		else {
			showError(response, " failure");
		}
	}

	private void showFindForm(HttpServletResponse response, Post post) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<title>" + post.getTitle() + "</title>");
		out.println("<style>");
		out.println("body {background-color: antiquewhite;margin-left: 200PX;margin-right: 200PX;}");
		out.println("form {text-align: center;}");
		out.println("h2 {text-align: center;}");
		out.println(".text_title {padding: 2px;width: 500px;}");
		out.println(".title{text-align: center;}");
		out.println("pre{font-size:16px; white-space: pre-warp}");
		out.println("textarea {height: 200px;width: 500px;resize: none;}");
		out.println("</style>");
		out.println("</HEAD>");
		out.println("<body>");
		out.println("<h2>" + post.getTitle() + "</h2>");
		out.println("<p class=\"title\">" + post.getPostedDate() + "</p>");
		out.println("<br>");
		out.println("<pre>" + post.getPostedText() + "</pre>");
		out.println("<br>");
		out.println("<br>");
		out.println("</form>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();

	}

	private void deletePost(HttpServletRequest request, HttpServletResponse response, PostDAO postDao2)
			throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		postDao2.deletePost(id);
		// response.sendRedirect("Test2.jsp");
		if (postDao2.deletePost(id))
			showDeleteForm(response, "刪除成功");
		else {
			showError(response, " failure");
		}
	}

	private void showDeleteForm(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Department Form</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY BGCOLOR='#FDF5E6'>");
		out.println(message);
		out.println("<br>");
		out.println("<br>");
		out.println(
				"<input type=\"button\" onclick=\"javascript:window.location.href='PostsTop.jsp';\" value=\"返回首頁\" />");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();

	}

	private void editPost(HttpServletRequest request, HttpServletResponse response, PostDAO postDao2)
			throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		Post post = postDao2.findPost(id);

		if (postDao2.findPost(id) != null)
			showEditForm(response, post);
		else {
			showError(response, " failure");
		}
	}

	private void showEditForm(HttpServletResponse response, Post post) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<title>編輯文章</title>");
		out.println("<style>");
		out.println("body {background-color: antiquewhite;}");
		out.println("form {text-align: center;}");
		out.println("h2 {text-align: center;}");
		out.println(".text_title {padding: 2px;width: 500px;}");
		out.println("textarea {height: 200px;width: 500px;resize: none;}");
		out.println("</style>");
		out.println("</HEAD>");
		out.println("<body>");
		out.println("<h2>編輯文章</h2>");
		out.println("<form action=\"PostServlet\" method=\"get\">");
		out.println("<h4>文章標題:</h4>");
		out.println("<input class=\"text_title\" type=\"text\" name=\"title\" value=" + post.getTitle()
				+ " +\"required />");
		out.println("<h4>文章內容:</h4>");
		out.println("<textarea class=\"text_title\" name=\"postedText\"  rows=\"10\"  required>");
		out.println(post.getPostedText());
		out.println("</textarea>");
		out.println("<br>");
		out.println("<br>");
		out.println("<input type=\"submit\"  value=\"更新文章\" />");
		out.println("<input type='hidden' name='update' value=" + post.getPostId() + ">");

		out.println("</form>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();

	}

	private void showError(HttpServletResponse response, String message) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Department Form</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY BGCOLOR='#FDF5E6'>");
		out.println("message:" + message);
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}

	private void showForm(HttpServletResponse response, String message) throws IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Department Form</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY BGCOLOR='#FDF5E6'>");
		out.println(message);
		out.println("<br>");
		out.println("<br>");
		out.println(
				"<input type=\"button\" onclick=\"javascript:window.location.href='CreatePost.jsp';\" value=\"繼續發表\" />");
		out.println(
				"<input type=\"button\" onclick=\"javascript:window.location.href='PostsTop.jsp';\" value=\"返回首頁\" />");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();

	}

}