package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Interface.PostService;
import bean.Post;
import dao.PostHibernateServiceImpl;

public class PostCServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			CreatePostImage(request, response);
		} catch (SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void CreatePostImage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		PostService pService = new PostHibernateServiceImpl();

		String title = null;
		String postedText = null;
		String headUrl = null;
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
				if (fieldName.equals("add")) {
				}

				System.out.println(fieldName + "=" + title + postedText);
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

		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.now();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		if (headUrl != null) {

		} else {
			headUrl = defaultImgurl;
		}
		Post post = new Post(title, date, postedText, headUrl);
		if (pService.addPostImage(post)) {
			System.out.println("儲存成功");
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/showResultForm").forward(request, response);
		} else {
			System.out.println("失敗");
		}
	}


}