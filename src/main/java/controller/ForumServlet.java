package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.*;
import dao.ForumDAO;;

@WebServlet("/ForumServlet")
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;
		try {

			ctxt = new InitialContext();

			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DBForum");

			conn = ds.getConnection();

			ForumDAO forumDAO = new ForumDAO(conn);

			if (request.getParameter("Query") != null)
				processQuery(request, response,forumDAO);

			if (request.getParameter("Create") != null)
				processCreate(request, response);

			
			if (request.getParameter("confirm") != null)
				processConfirm(request, response);
		}

		catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}

	protected void processQuery(HttpServletRequest request, HttpServletResponse response, ForumDAO forumDAO)
			throws ServletException, IOException {
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;	
		try {
			String vgeid = request.getParameter("vgeid");
			// �إ�Context Object,�s��JNDI Server
			ctxt = new InitialContext();

			// �ϥ�JNDI API���DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DBForum");
			
			// �VDataSource�nConnection
			conn = ds.getConnection();

			// �إ�Database Access Object,�t�dTable��Access
			forumDAO = new ForumDAO(conn); // STUDENTDAO���@�ӫغc�l�Ǧ^
			ForumBean forumBean = forumDAO.queryForum(vgeid);
				String id = request.getParameter("id");
				String vgename = request.getParameter("vgename");
				String vgetheme = request.getParameter("vgetheme");
				String vgecontent = request.getParameter("vgecontent");
				ForumBean vge = new ForumBean(vgeid,vgename,vgetheme,vgecontent);
				request.getSession(true).setAttribute("vge",vge); // ����SESSION
				request.getRequestDispatcher("/QueryResult.jsp").forward(request, response);

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

	protected void processCreate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;	
		try {
			String vgeid = request.getParameter("vgeid");
			// �إ�Context Object,�s��JNDI Server
			ctxt = new InitialContext();

			// �ϥ�JNDI API���DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DBForum");
			
			// �VDataSource�nConnection
			conn = ds.getConnection();

			// �إ�Database Access Object,�t�dTable��Access
			ForumDAO forumDAO = new ForumDAO(conn); // STUDENTDAO���@�ӫغc�l�Ǧ^
		//	ForumBean fromData = (ForumBean) request.getSession(true).getAttribute("vga");
		//	if (forumDAO.queryForum(vgeid) != null) {
				//System.out.println("Get some SQL commands done!");
				String vgename = request.getParameter("vgename");
				String vgetheme = request.getParameter("vgetheme");
				String vgecontent = request.getParameter("vgecontent");
				ForumBean vge = new ForumBean(vgeid,vgename,vgetheme,vgecontent);
				request.getSession(true).setAttribute("vge",vge);; // ����SESSION
				request.getRequestDispatcher("/DisplayForum.jsp").forward(request, response);
			//}
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

	protected void processUpdate(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
//		//int vgeid = request.getParameter("vgeid");
//		String vgename = request.getParameter("vgename");
//		String vgetheme = request.getParameter("vgetheme");
//		String vgecontent = request.getParameter("vgecontent");
//		//ForumBean vge = new ForumBean(vgeid, vgename, vgetheme, vgecontent);
//		//request.getSession(true).setAttribute("vge", vge); // bean�s��session �ݩʦW�ټg��DISPLAY (JSP.BEAN ID:NAME)
//		request.getRequestDispatcher("/DisplayForum.jsp").forward(request, response); // �T���浹display
	}

//	protected void processDelete(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		 	DataSource ds = null;
//		    InitialContext ctxt = null;
//		    Connection conn = null;
//		    try {    
//			      //�إ�Context Object,�s��JNDI Server	
//			      ctxt = new InitialContext();
//
//			      //�ϥ�JNDI API���DataSource
//			      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DBForum");
//			      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
//			      //�VDataSource�nConnection
//			      conn = ds.getConnection();
//
//			      //�إ�Database Access Object,�t�dTable��Access
//			      ForumDAO forumDAO = new ForumDAO(conn); //STUDENTDAO���@�ӫغc�l�Ǧ^
//			      ForumBean forumData = (ForumBean)request.getSession(true).getAttribute("vge");
//			      int vgeid = Integer.parseInt(request.getParameter("vgeid"));
//			          forumDAO.deleteForum(vgeid);
//			        
//			          
//			        
//			    } catch (NamingException ne) {
//			      System.out.println("Naming Service Lookup Exception");  
//			    } catch (SQLException e) {
//			      System.out.println("Database Connection Error"); 
//			    } finally {
//			      try {
//			        if (conn != null) conn.close();
//			      } catch (Exception e) {
//			        System.out.println("Connection Pool Error!");
//			      }
//			    }
//			           
//			  
//		
//	}
	 public void processConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {

	    DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    try {    
	      //�إ�Context Object,�s��JNDI Server	
	      ctxt = new InitialContext();

	      //�ϥ�JNDI API���DataSource
	      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DBForum");
	      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
	      //�VDataSource�nConnection
	      conn = ds.getConnection();

	      //�إ�Database Access Object,�t�dTable��Access
	      ForumDAO forumDAO = new ForumDAO(conn); //STUDENTDAO���@�ӫغc�l�Ǧ^
	      ForumBean forumData = (ForumBean)request.getSession(true).getAttribute("vge");
	      if (ForumDAO.insertForum(forumData))
	        {
	          System.out.println("Get some SQL commands done!");
	          request.getSession(true).invalidate(); //����SESSION
	          request.getRequestDispatcher("/Thank.jsp").forward(request,response);
	        }
	    } catch (NamingException ne) {
	      System.out.println("Naming Service Lookup Exception");  
	    } catch (SQLException e) {
	      System.out.println("Database Connection Error"); 
	    } finally {
	      try {
	        if (conn != null) conn.close();
	      } catch (Exception e) {
	        System.out.println("Connection Pool Error!");
	      }
	    }
	           
	  }
}