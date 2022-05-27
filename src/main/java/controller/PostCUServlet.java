package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.Post;
import dao.PostDAO;

public class PostCUServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			PostDAO postDAO = new PostDAO(conn);

			// 憒��楊蝣澆�潛UTF-8
			request.setCharacterEncoding("UTF-8");

			
				Update(request, response, postDAO);


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
			request.setAttribute("message", "������");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			request.setAttribute("message", "��憭望��");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}
	}

	@SuppressWarnings("unused")
	private void Create(HttpServletRequest request, HttpServletResponse response, PostDAO postDAO)
			throws SQLException, IOException, ServletException {

		//Post post = new Post();
		String title = request.getParameter("title");
		String posted_text = request.getParameter("postedText");


		if (postDAO.addPost(title, posted_text)) {
			request.setAttribute("message", "�銵冽���");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			request.setAttribute("message", "�銵典仃���");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}

	}
	
	@SuppressWarnings("unused")
	private void UpdatePostImage(HttpServletRequest request, HttpServletResponse response, PostDAO postDao)
			throws SQLException, IOException, ServletException {

		String title = null;
		String postedText = null;
		String add = null;
		String headUrl = ""; // 摮頝臬��
		String headImgFileName = "images/PostsPhoto"; // Web��銝剖������辣憭曉���摰儔
		int id = (Integer.parseInt(request.getParameter("update")));
		Post post = new Post();

		FileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(factory);

		List<?> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		Iterator<?> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			
            //����撘�
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				if (fieldName.equals("title")) {
					// 敺銵典����
					title = item.getString("UTF-8");
				}
				if (fieldName.equals("postedText")) {
					postedText = item.getString("UTF-8");
				}
				if (fieldName.equals("add")) {
					add = item.getString("UTF-8");
				}

				System.out.println(fieldName + "=" + title + postedText);
//                 String value = item.getString();
//			request.setAttribute(title, title);
			}
			// 霈��鞈�瑼��
			else {
				String fileName = item.getName();
				System.out.println("�����" + fileName);
				String suffix = fileName.substring(fileName.lastIndexOf('.'));//���瑼��
				System.out.println("�瑼���" + suffix);// .jpg
				//���辣��迂
				String newFileName = new Date().getTime() + suffix;
				System.out.println("�瑼���" + newFileName);// 1478509873038.jpg

				
				ServletContext context = this.getServletContext();
				// 蝯�楝敺�
				String serverPath = context.getRealPath("") + headImgFileName;//
				String savePath ="C:/Users/PC/Documents/GitHub/vegetarian/src/main/webapp/"+headImgFileName;
				System.out.println(serverPath);
				System.out.println(savePath);

				// 撠�������蔭
				File headImage = new File(savePath, newFileName);
				// 銝���神�
				try {
					item.write(headImage);
				} catch (Exception e) {
					e.printStackTrace();
				}

				//����楝敺�eadUrl嚗摮table
				headUrl = headImgFileName + "/" + newFileName; // ���撠楝敺� headImage/1478509873038.jpg
				System.out.println(headUrl);
				
			}
			
		}
		if (postDao.updatePost(post,title, postedText,id)) {
			System.out.println("銝����");
			request.setAttribute("message", "�銵冽���");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			System.out.println("憭望��");
		}
	}


}