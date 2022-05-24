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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			PostDAO postDAO = new PostDAO(conn);

			// 如果要編碼值為UTF-8
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("add") != null) {
				CreatePostImage(request, response, postDAO);
			}
			if (request.getParameter("update") != null) {
				Update(request, response, postDAO);
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

	private void Update(HttpServletRequest request, HttpServletResponse response, PostDAO postDAO)
			throws SQLException, IOException, ServletException {

		Post post = new Post();
		String title = request.getParameter("title");
		String posted_text = request.getParameter("postedText");
		int id = (Integer.parseInt(request.getParameter("update")));

		if (postDAO.updatePost(post, title, posted_text, id)) {
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			request.setAttribute("message", "更新失敗");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}
	}

	private void Create(HttpServletRequest request, HttpServletResponse response, PostDAO postDAO)
			throws SQLException, IOException, ServletException {

		Post post = new Post();
		String title = request.getParameter("title");
		String posted_text = request.getParameter("postedText");
		Date time = post.getPostedDate();

		if (postDAO.addPost(title, posted_text)) {
			request.setAttribute("message", "發表成功");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			request.setAttribute("message", "發表失敗");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		}

	}
	
	private void CreatePostImage(HttpServletRequest request, HttpServletResponse response, PostDAO postDao)
			throws SQLException, IOException, ServletException {

		String title = null;
		String postedText = null;
		String headUrl = ""; // 存放路徑
		String headImgFileName = "images/postsPhoto"; // Web項目中存放圖片的文件夾名。可自定義

		FileItemFactory factory = new DiskFileItemFactory();

		// 创建文件上传处理器
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 开始解析请求信息
		List items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 对所有请求信息进行判断
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			// 信息为普通的格式

			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				if (fieldName.equals("title")) {
					// 得到表單的值
					title = item.getString("UTF-8");
				}
				if (fieldName.equals("postedText")) {
					postedText = item.getString("UTF-8");
				}

//				System.out.println(fieldName + "=" + title + postedText);
//                 String value = item.getString();
//			request.setAttribute(title, title);
			}
			// 讀入資料為檔案
			else {
				String fileName = item.getName();
				System.out.println("原文件名" + fileName);
				String suffix = fileName.substring(fileName.lastIndexOf('.'));//取得副檔名
				System.out.println("擴展名：" + suffix);// .jpg
				//新文件名稱
				String newFileName = new Date().getTime() + suffix;
				System.out.println("新文件名：" + newFileName);// 1478509873038.jpg

				
				ServletContext context = this.getServletContext();
				// 絕對路徑
				String serverPath = context.getRealPath("") + headImgFileName;//
				String savePath ="D:/204webws/Vegan/src/main/webapp/images/postsPhoto";
				System.out.println(serverPath);
				System.out.println(savePath);

				// 將圖片存入指定位置
				File headImage = new File(savePath, newFileName);
				// 上傳圖片寫入
				try {
					item.write(headImage);
				} catch (Exception e) {
					e.printStackTrace();
				}

				//把圖片路徑（headUrl）儲存到table
				headUrl = headImgFileName + "/" + newFileName; // 拼接相對路徑 headImage/1478509873038.jpg
				System.out.println(headUrl);
				
			}
			
		}
		if (postDao.addPostImage(title, postedText, headUrl)) {
			System.out.println("上傳成功");
			// 存儲成功後，把路徑存到session裏面，重定向到提交圖片的界面，進行回顯
			request.setAttribute("message", "發表成功");
			request.getRequestDispatcher("showResultForm.jsp").forward(request, response);
		} else {
			System.out.println("頭像路徑更改失敗");
		}
	}


}