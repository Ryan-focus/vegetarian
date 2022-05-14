package controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


import javax.naming.*;
import javax.sql.*;

import bean.Post;
import dao.PostDao2;

public class PostCUServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {

			// 建立Context Object,連到JNDI Server
			ctxt = new InitialContext();

			// 使用JNDI API找到DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/PostsDB");

			// 向DataSource要Connection
			conn = ds.getConnection();

			// 建立Database Access Object,負責Table的Access
			PostDao2 postDao2 = new PostDao2(conn);

			// 如果要編碼值為UTF-8
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("add") != null) {
				Create(request, response, postDao2);
			}
			if (request.getParameter("update") != null) {
				Update(request, response, postDao2);
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

  private void Update(HttpServletRequest request,
                             HttpServletResponse response,
                             PostDao2 postDao2) throws SQLException, IOException {
   
    
    Post post = new Post();
	String title = request.getParameter("title");
	String posted_text = request.getParameter("postedText");
	int id=(Integer.parseInt(request.getParameter("update")));
	
	
      if (postDao2.updatePost(post,title, posted_text, id))
    	  showForm(response,"更新成功");
      else {showError(response," update failure");
    }
  }

	private void Create(HttpServletRequest request, HttpServletResponse response, PostDao2 postDao2)
			throws SQLException, IOException {
		
		Post post = new Post();
		String title = request.getParameter("title");
		String posted_text = request.getParameter("postedText");
		Date time = post.getPostedDate();
		
	//	boolean isSucess = postDao2.addPost(post,title,posted_text);


		if (postDao2.addPost(title, posted_text))
			showForm(response, "發表成功");
		else {
			showError(response, " failure");
		}

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

	private void showForm(HttpServletResponse response,String message) throws IOException {

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
		out.println("<input type=\"button\" onclick=\"javascript:window.location.href='CreatePost.html';\" value=\"繼續發表\" />");
		out.println("<input type=\"button\" onclick=\"javascript:window.location.href='PostsTop.jsp';\" value=\"返回首頁\" />");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
		
	}

}