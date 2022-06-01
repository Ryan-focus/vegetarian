package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ForumBean;
import service.ForumHibernateService;
import service.ForumService;


@WebServlet(name="/ForumHibernateServlet" , urlPatterns="/ForumHibernateServlet")
public class ForumHibernateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		ForumService ms = new ForumHibernateService();
		List <ForumBean>list = ms.getAllMembers();
		//Collection<MemberBean> coll = ms.getAllMembers();
		//用List不用Collection,因為List是子代,功能比較多
		request.setAttribute("allMembers", list);
		RequestDispatcher rd = request.getRequestDispatcher("showMembers.jsp");
		rd.forward(request, response);
		return;		
	}

}
