package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.Product;
import dao.ProductDao;

/**
 * Servlet implementation class ProductImageServlet
 */
@MultipartConfig
@WebServlet("/ProductImage")
public class ProductImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	DataSource ds =null;
//	InitialContext ctx = null;
//	Connection con = null;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("In do post method of Add Image servlet.");
		Part file=request.getPart("image");
		
		String imageFileName=file.getSubmittedFileName();  // get selected image file name
		System.out.println("Selected Image File Name : "+imageFileName);
		
		String uploadPath="C:\\Users\\iSpan\\Documents\\GitHub\\vegetarian\\src\\main\\webapp\\images\\GoodsPhoto\\"+imageFileName;  // upload path where we have to upload our actual image
		System.out.println("Upload Path : "+uploadPath);
		
		// Uploading our selected image into the images folder
		
		try
		{
		
		FileOutputStream fos=new FileOutputStream(uploadPath);
		InputStream is=file.getInputStream();
		
		byte[] data=new byte[is.available()];
		is.read(data);
		fos.write(data);
		fos.close();
		
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//**********************
		

		
		try 
		{
//			ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/veganDB");
//			
//			con=ds.getConnection();
//			PreparedStatement stmt;
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			double price = Double.parseDouble(request.getParameter("price"));
//			String query="insert into products(name,category,price,image) values(?,?,?,?)";
//			stmt=con.prepareStatement(query);
//			stmt.setString(1,name);
//			stmt.setString(2,category);
//			stmt.setDouble(3,price);
//			stmt.setString(4,imageFileName);
//			int row=stmt.executeUpdate(); // it returns no of rows affected.
			
			Product product = new Product();
			product.setName(name);
			product.setCategory(category);
			product.setPrice(price);
			product.setImage(imageFileName);
			ProductDao pd = new ProductDao();
			
			if(pd.insertProduct(product)!=null)
			{
				 printWriter.flush();
		           printWriter.println("<script>");
		           printWriter.println("alert('success');");
		           printWriter.println("window.location.href='addToProduct.jspf';");
		           printWriter.println("</script>");
		           printWriter.close();
			}
			
			else
			{
				 printWriter.flush();
		           printWriter.println("<script>");
		           printWriter.println("alert('failed');");
		           printWriter.println("window.location.href='addToProduct.jspf';");
		           printWriter.println("</script>");
		           printWriter.close();
			}
			
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

}
