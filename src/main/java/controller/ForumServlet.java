package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bean.ForumBean;
import bean.User;


import dao.ForumHibernateDao;
import dao.ForumHibernateService;
import dao.PostHibernateServiceImpl;
import Interface.*;

@WebServlet(name="ForumServlet" , urlPatterns="/ForumServlet")
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	ForumService forumService = new ForumHibernateService();
	ForumHibernateDao forumHibernateDao = new ForumHibernateDao();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
//		DataSource ds = null;
//		InitialContext ctxt = null;
//		Connection conn = null;
		try {
//			ctxt = new InitialContext();
//
//			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
//
//			conn = ds.getConnection();
		//	ForumDAO forumDAO = new ForumDAO(conn);
		
			if (request.getParameter("Query") != null)
				processqueryone(request, response, forumHibernateDao);

			if (request.getParameter("Create") != null)
				processCreate(request, response,forumHibernateDao);
		
			if (request.getParameter("Delete") != null)
				processDelete(request, response, forumHibernateDao);
//
//			if (request.getParameter("confirm") != null)
//				processConfirm(request, response);

			if (request.getParameter("Update") != null)
				processUpdate(request, response,forumHibernateDao);
			
			if(request.getParameter("ForumHome")!=null)
				prcoessHome(request,response);
		}catch (Exception e) {
			e.printStackTrace();
		} 
//		catch (NamingException e) {
//			e.printStackTrace();
//		} 
//		finally {
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (Exception e) {
//				System.out.println("Connection Pool Error!");
//			}
//		}
	}

	protected void processqueryone(HttpServletRequest request, HttpServletResponse response, ForumDAO forumDAO)
			throws ServletException, IOException {
		
		String vgename =request.getParameter("vgename");
		List<ForumBean> forumBeans = forumService.queryone(vgename);
		for (ForumBean f : forumBeans) {
			System.out.println(f.getVgename());
			System.out.println(f.getVgeid());
		}
		request.setAttribute("forumBean", forumBeans);
		request.getRequestDispatcher("/WEB-INF/jsp/forum/QueryResult.jsp").forward(request, response);
	}
	

	
	
	protected void processquery(HttpServletRequest request, HttpServletResponse response, ForumDAO forumDAO)
			throws ServletException, IOException {
		String vgename =request.getParameter("vgename");
		List<ForumBean> forumBean = forumService.queryName(vgename);
		request.setAttribute("forumBean", forumBean);
		request.getRequestDispatcher("/WEB-INF/jsp/forum/QueryResult.jsp").forward(request, response);		
//		if(forumBean!=) {
//		}
		
		//request.getSession(true,forumBean);
		
//		List<ForumBean>list = forumService.QueryName();
//		request.getSession(true);
//		request.getRequestDispatcher("/WEB-INF/jsp/forum/QueryResult.jsp").forward(request, response);			
//		return;
//		-------------------------------------------------------
//		DataSource ds = null;
//		InitialContext ctxt = null;
//		Connection conn = null;
//		try {
//			ctxt = new InitialContext();
//
//			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
//
//			conn = ds.getConnection();
//			forumDAO = new ForumDAO(conn); 
//			String name = request.getParameter("vgename");
//			ForumBean forumBean = forumDAO.queryForum(name);
//			if(forumBean ==null) {
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/forum/ErrorQuery.jsp");
//				dispatcher .forward(request, response);
//				// request.getRequestDispatcher("/ShowError.html");
//			
//			}else {
//				request.setAttribute("vgeid", forumBean.getVgeid());
//				request.setAttribute("vgename", forumBean.getVgename());
//				request.setAttribute("vgetheme", forumBean.getVgetheme());
//				request.setAttribute("vgecontent", forumBean.getVgecontent());
//				
//				request.getSession(true);
//				request.getRequestDispatcher("/WEB-INF/jsp/forum/QueryResult.jsp").forward(request, response);			
//			}
//		} catch (NamingException ne) {
//			System.out.println("Naming Service Lookup Exception");
//		} catch (SQLException e) {
//			System.out.println("Database Connection Error");
//		} finally {
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (Exception e) {
//				System.out.println("Connection Pool Error!");
//			}
//		}

	}

	protected void processCreate(HttpServletRequest request, HttpServletResponse response, ForumDAO forumDAO)
			throws ServletException, IOException {
		
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("error", errorMsg);
		Integer vgeid = 0 ;
		String vgename = request.getParameter("vgename");
		String vgetheme = request.getParameter("vgetheme");
		String vgecontent = request.getParameter("vgecontent");
		
		if(vgename.equals("") || vgetheme.equals("") ||vgecontent.equals("")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/forum/ErrorCreate.jsp");
			dispatcher .forward(request, response);
		}
		ForumService forumService = new ForumHibernateService();
		HttpSession session = request.getSession();
		try {
			ForumBean forumBean = new ForumBean(vgeid,vgename,vgetheme,vgecontent);
			session.setAttribute("forumBean", forumBean);
			forumService.save(forumBean);
		}catch (Exception e) {
			System.out.println("Database Connection Error");
		}
		
//		-------------------------------------------------
//		DataSource ds = null;
//		InitialContext ctxt = null;
//		Connection conn = null;
//		try {
//			String vgeid = request.getParameter("vgeid");
//			ctxt = new InitialContext();
//
//			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
//
//			conn = ds.getConnection();
//
//			ForumBean forumBean = forumDAO.queryForum(vgeid);
//			if(forumBean != null) {
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/forum/ErrorCreate.jsp");
//				dispatcher .forward(request, response);
//			}else {
//				
//				 forumDAO = new ForumDAO(conn); 
//				 String vgename = request.getParameter("vgename");
//				 String vgetheme = request.getParameter("vgetheme");
//				 String vgecontent = request.getParameter("vgecontent");
//				 ForumBean vge = new ForumBean(vgeid, vgename, vgetheme, vgecontent);
//				
//				 if( vgename.equals("") || vgetheme.equals("") ||vgecontent.equals("")) {
//					 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/forum/ErrorCreate.jsp");
//					 dispatcher .forward(request, response);
//				 }else {
//				 
//					 System.out.println(vgecontent+" "+vgename+" "+vgetheme);
//					 request.getSession(true).setAttribute("vge", vge);
//					 request.getRequestDispatcher("/WEB-INF/jsp/forum/DisplayForum.jsp").forward(request, response);
//					 }
//			}
//			
//		} catch (NamingException ne) {
//			System.out.println("Naming Service Lookup Exception");
//		} catch (SQLException e) {
//			System.out.println("Database Connection Error");
//		} finally {
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (Exception e) {
//				System.out.println("Connection Pool Error!");
//			}
//		}
	}

	protected void processUpdate(HttpServletRequest request, HttpServletResponse response, ForumDAO forumDAO)
			throws ServletException, IOException {
		
		HttpSession hession = request.getSession();
		String modify = request.getParameter("finalDecision");
		String vgeid =request.getParameter("vgeid");
		int id = Integer.parseInt(vgeid);
		String vgename = request.getParameter("vgename");
		String vgetheme = request.getParameter("vgetheme");
		String vgecontent = request.getParameter("vgecontent");
		System.out.println("modify="+modify);
		ForumService forumService = new ForumHibernateService();
		int count = 0;
		count = forumService.deleteForum(id);
		if(count == 1) {
			hession.setAttribute("modify", "成功");
		}else {
			hession.setAttribute("modify", "失敗");
		}
		ForumBean forumBean = new ForumBean(id,vgename,vgetheme,vgecontent);
		count = forumService.updateForum(forumBean);
		request.getRequestDispatcher("/WEB-INF/jsp/forum/UpdateResult.jsp").forward(request, response);
		
		
//		DataSource ds = null;
//		InitialContext ctxt = null;
//		Connection conn = null;
//		try {
//			ctxt = new InitialContext();
//			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
//			conn = ds.getConnection();
//			forumDAO = new ForumDAO(conn);
//			
//			String vgeid =request.getParameter("vgeid");
//			String vgename = request.getParameter("vgename");
//			String vgetheme = request.getParameter("vgetheme");
//			String vgecontent = request.getParameter("vgecontent");
//			ForumBean foruData=new ForumBean (vgeid,vgename,vgetheme,vgecontent);
//			forumDAO.updateForum(foruData);
//			//foruData.setVgeid(vgeid);
//			foruData.setVgename(vgename);
//			foruData.setVgetheme(vgetheme);
//			foruData.setVgecontent(vgecontent);
//			
//			request.getSession(true).setAttribute("foruData", foruData);
//			request.getRequestDispatcher("/WEB-INF/jsp/forum/UpdateResult.jsp").forward(request, response);
//		} catch (NamingException ne) {
//			System.out.println("Naming Service Lookup Exception");
//		} catch (SQLException e) {
//			System.out.println("Database Connection Error");
//		} finally {
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (Exception e) {
//				System.out.println("Connection Pool Error!");
//			}
//		}
	}

	protected void processDelete(HttpServletRequest request, HttpServletResponse response ,ForumDAO forumDAO)
			throws ServletException, IOException {
		
		HttpSession hession = request.getSession();
		String vgeid =request.getParameter("vgeid");
		int id = Integer.parseInt(vgeid);
		ForumService forumService = new ForumHibernateService();
		int count = 0;
		count = forumService.deleteForum(id);
		request.getRequestDispatcher("/WEB-INF/jsp/forum/DeleteForum.jsp").forward(request, response);
//		-----------------------------------------------
//		DataSource ds = null;
//		InitialContext ctxt = null;
//		Connection conn = null;
//		try {
//			ctxt = new InitialContext();
//
//			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
//
//			conn = ds.getConnection();
//			forumDAO = new ForumDAO(conn); 
//			 String id =request.getParameter("vgeid") ;		 
//			 forumDAO.deleteForum(id);
//			 request.getSession(true);
//			 request.getRequestDispatcher("/WEB-INF/jsp/forum/DeleteForum.jsp").forward(request, response);
//			
//
//
//		} catch (NamingException ne) {
//			System.out.println("Naming Service Lookup Exception");
//		} catch (SQLException e) {
//			System.out.println("Database Connection Error");
//		} finally {
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (Exception e) {
//				System.out.println("Connection Pool Error!");
//			}
//			}	
	}

	
//	public void processConfirm(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		User userForum = (User) request.getSession().getAttribute("user");
//		DataSource ds = null;
//		InitialContext ctxt = null;
//		Connection conn = null;
//		try (PrintWriter out = response.getWriter()){
//			ctxt = new InitialContext();
//
//			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
//			conn = ds.getConnection();
//
//			@SuppressWarnings("unused")
//			ForumDAO forumDAO = new ForumDAO(conn); 
//			ForumBean forumData = (ForumBean) request.getSession(true).getAttribute("vge");
//			
//			
//			if (ForumDAO.insertForum(forumData)) {
//				request.setAttribute("user", userForum);
//				request.getSession().getAttribute("user");
//				request.getRequestDispatcher("/WEB-INF/jsp/forum/Thank.jsp").forward(request, response);
//			}
//		} catch (NamingException ne) {
//			System.out.println("Naming Service Lookup Exception");
//		} catch (SQLException e) {
//			System.out.println("Database Connection Error");
//		} finally {
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (Exception e) {
//				System.out.println("Connection Pool Error!");
//			}
//		}
//
//	}
	public void prcoessHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//				User userForum = (User) request.getSession().getAttribute("user");
//		try (PrintWriter out = response.getWriter()){
//			if(userForum==null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/forum/QueryForum.jsp");
					dispatcher.forward(request, response);

//			}else {
//					if(userForum.getUid()==0) 
//						response.sendRedirect("/vegetarian/Login");
//						request.getRequestDispatcher("/WEB-INF/jsp/forum/QueryForum.jsp").forward(request,response);
				
		}		
//	}catch (Exception e) {
//	}
//	}
	
	
}
