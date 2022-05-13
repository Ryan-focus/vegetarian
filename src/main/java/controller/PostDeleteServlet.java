package controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.*;
import javax.sql.*;

import dao.PostDao2;

public class PostDeleteServlet extends HttpServlet {
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

			
				deletePost(request, response, postDao2);
			
	


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

	
	private void deletePost(HttpServletRequest request, HttpServletResponse response, PostDao2 postDao2)
			throws SQLException, IOException {
 
        int id = Integer.parseInt(request.getParameter("id"));
         
        postDao2.deletePost(id);
        //response.sendRedirect("Test2.jsp");
        if (postDao2.deletePost(id))
			showForm(response, "刪除成功");
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
		out.println("<input type=\"button\" onclick=\"javascript:window.location.href='PostsTop.jsp';\" value=\"返回首頁\" />");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
		
	}

}