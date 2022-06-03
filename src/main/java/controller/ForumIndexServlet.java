package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Interface.ForumDAO;
import Interface.ForumService;
import bean.ForumBean;
import dao.ForumHibernateDao;
import dao.ForumHibernateService;

@WebServlet(name="/ForumIndexServlet" ,  urlPatterns="/ForumIndexServlet")
public class ForumIndexServlet extends HttpServlet {
			private static final long serialVersionUID = 1L;
			private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
			private static final String CHARSET_CODE = "UTF-8";
			ForumService forumService = new ForumHibernateService();
			ForumHibernateDao forumHibernateDao = new ForumHibernateDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.setCharacterEncoding(CHARSET_CODE);
			response.setContentType(CONTENT_TYPE);

			String vgename =request.getParameter("vgename");
			List<ForumBean> forumBeans = forumService.queryName();
			request.setAttribute("forumBean", forumBeans);
			request.getRequestDispatcher("/forumIndex.jsp").forward(request, response);	
	}

	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
