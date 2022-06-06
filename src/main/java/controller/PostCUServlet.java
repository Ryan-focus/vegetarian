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

import Interface.PostService;
import bean.Post;
import dao.PostDAO;
import dao.PostHibernateServiceImpl;

public class PostCUServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			UpdatePostImage(request, response);
		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}

	}

	private void Update(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		PostService pService = new PostHibernateServiceImpl();
		String title = request.getParameter("title");
		String posted_text = request.getParameter("postedText");
		int id = (Integer.parseInt(request.getParameter("update")));

		Post post = new Post(id, title, posted_text);

		if (pService.updatePost(post)) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			request.setAttribute("message", "更新失敗");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}
	}

//	@SuppressWarnings("unused")
//	private void Create(HttpServletRequest request, HttpServletResponse response, PostDAO postDAO)
//			throws SQLException, IOException, ServletException {
//
//		//Post post = new Post();
//		String title = request.getParameter("title");
//		String posted_text = request.getParameter("postedText");
//
//
//		if (postDAO.addPost(title, posted_text)) {
//			request.setAttribute("message", "�銵冽���");
//			request.getRequestDispatcher("/showResultForm").forward(request, response);
//		} else {
//			request.setAttribute("message", "�銵典仃���");
//			request.getRequestDispatcher("/showResultForm").forward(request, response);
//		}
//
//	}
//	
	@SuppressWarnings("unused")
	private void UpdatePostImage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		PostService pService = new PostHibernateServiceImpl();
		Post post2 = new Post();
		String title = null;
		String postedText = null;
		String update = null;
		String headUrl = post2.getImgurl();
		String headImgFileName = "images/PostsPhoto"; 
		String defaultImgurl = "images/PostsPhoto/defaultPostImage.jpg";
		
		
		
		FileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();

			// 一般文字
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				if (fieldName.equals("title")) {
					// 獲得表單值
					title = item.getString("UTF-8");
				}
				if (fieldName.equals("postedText")) {
					postedText = item.getString("UTF-8");
				}
				if (fieldName.equals("update")) {
					update = item.getString("UTF-8");
				}
				//int id = (Integer.parseInt(request.getParameter("update")));
				System.out.println(fieldName + "=" + title + postedText);
				System.out.println(fieldName + "=" + postedText);
				System.out.println(fieldName + "=" + update);
//                 String value = item.getString();
//			request.setAttribute(title, title);
			}
			// 檔案
			else if (item.getSize() != 0) {
				String fileName = item.getName();
				System.out.println("檔案名" + fileName);
				String suffix = fileName.substring(fileName.lastIndexOf('.'));// 副檔名
				System.out.println("副檔名" + suffix);// .jpg
				// 新的檔名
				String newFileName = new Date().getTime() + suffix;
				System.out.println("新檔名" + newFileName);// 1478509873038.jpg

				ServletContext context = this.getServletContext();
				// 絕對路徑
				String serverPath = context.getRealPath("") + headImgFileName;//
				String savePath = "C:\\Users\\iSpan\\Documents\\GitHub\\vegetarian\\src\\main\\webapp\\"
						+ headImgFileName;
				System.out.println(serverPath);
				System.out.println(savePath);

				// 儲存
				File headImage = new File(savePath, newFileName);
				// 寫成圖片
				try {
					item.write(headImage);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 儲存路徑
				headUrl = headImgFileName + "/" + newFileName;
				System.out.println(headUrl);

			}

		}
		
		if (headUrl != null) {

		} else {
			headUrl = defaultImgurl;
		}
		int id = (Integer.parseInt(update));
		
		Post post = new Post(id, title, postedText,headUrl);
		if (pService.updatePost(post)) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			request.setAttribute("message", "更新失敗");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}
	}


}