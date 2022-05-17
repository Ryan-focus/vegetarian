package controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.*;
import javax.sql.*;

import bean.Post;
import dao.PostDao2;

public class PostIndexServlet extends HttpServlet {
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

			
				showPost(request, response, postDao2);
			
	


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

	
	private void showPost(HttpServletRequest request, HttpServletResponse response, PostDao2 postDao2)
			throws SQLException, IOException {
 
        int id = Integer.parseInt(request.getParameter("id"));
         
        Post post  =postDao2.findPost(id);
        //response.sendRedirect("Test2.jsp");
        
        if (postDao2.findPost(id)!=null)
			showForm(response, post);
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

	private void showForm(HttpServletResponse response,Post post) throws IOException {
		
		StringBuffer format = new StringBuffer();
		response.setContentType("text/html; charset=UTF-8");
		

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<title>"+post.getTitle()+"</title>");
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
		out.println("<h2>"+post.getTitle()+"</h2>");
		
		out.println("<p class=\"title\">"+post.getPostedDate()+"</p>");
		out.println("<br>");
		out.println(post.getPostedText());
		
		out.println("<br>");
		out.println("<br>");
		out.println("</form>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
		
		
		
	}

}