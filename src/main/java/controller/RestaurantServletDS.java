package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.Restaurant;
import dao.RestaurantDAO;

@WebServlet("/RestaurantServletDS")
public class RestaurantServletDS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		DataSource ds =null;
		InitialContext ctx = null;
		Connection con = null;
		
		res.setContentType("text/html; charset=UTF-8");
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/veganDB");
			con = ds.getConnection();
			RestaurantDAO restaurantDAO = new RestaurantDAO(con);

			if (req.getParameter("所有餐廳") != null) {
				restaurantQueryAll(req, res, restaurantDAO);
			}
			if (req.getParameter("查詢餐廳") != null) {
				restaurantQueryByNmuber(req, res, restaurantDAO);
			}
			if (req.getParameter("查詢餐廳GO") != null) {
				restaurantQuery(req, res, restaurantDAO);
			}
			if (req.getParameter("新增餐廳") != null) {
				processCreate(req, res, restaurantDAO);
			}
		
			if (req.getParameter("刪除餐廳") != null) {
				restaurantDeleteByNumber(req, res, restaurantDAO);
			}
			if (req.getParameter("修改餐廳") != null) {
				restaurantUpdate(req, res, restaurantDAO);
			}
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}

	// 所有餐廳 select all
	private void restaurantQueryAll(HttpServletRequest req, HttpServletResponse res, RestaurantDAO restaurantDAO)
			throws SQLException, IOException {
		
		// 透過DAO元件Access Dept Table
		List<Restaurant> restaurantList = restaurantDAO.findAllRestaurant();
		if (restaurantList == null)
			try {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorPage/showError.jsp");
				dispatcher.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		else
			req.setAttribute("restaurantList", restaurantList);
			try {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/restaurantForm.jsp");
				dispatcher.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
	}
	
	// 查詢餐廳 by Address&Category&Type
		private void restaurantQuery(HttpServletRequest req, HttpServletResponse res, RestaurantDAO restaurantDAO)
				throws SQLException, IOException {
			// 讀取餐廳資料
			String restaurantName = req.getParameter("restaurantName");
			String restaurantAddress = req.getParameter("restaurantAddress");
			String restaurantCategory = req.getParameter("restaurantCategory");
			if (restaurantCategory==null) {
				restaurantCategory="";}
			String restaurantType = req.getParameter("restaurantType");
			if (restaurantType==null) {
				restaurantType="";}
			
			// 透過DAO元件Access Dept Table
			List<Restaurant> restaurantList = restaurantDAO.findRestaurant(restaurantName,restaurantAddress,restaurantCategory,restaurantType);
			
			// session
			req.getSession().setAttribute("restaurantList", restaurantList);
			
			if (restaurantList == null)
				try {
					RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorPage/showError.jsp");
					dispatcher.forward(req, res);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			else
				req.setAttribute("restaurantList", restaurantList);
			try {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/restaurantForm.jsp");
				dispatcher.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
		} 
	
	/* 查詢餐廳 by Address&Category&Type
	private void restaurantQuery(HttpServletRequest req, HttpServletResponse res, RestaurantDAO restaurantDAO)
			throws SQLException, IOException {
		// 讀取餐廳資料
		String restaurantName = req.getParameter("restaurantName");
		String restaurantAddress = req.getParameter("restaurantAddress");
		String[] restaurantCategory =req.getParameterValues("restaurantCategory");
		String[] test = {"","","",""};
		
		for (int i = 0; i < restaurantCategory.length; i++) {
			test[i]=restaurantCategory[i];
			System.out.println(test[i]);
		}
		String restaurantType = req.getParameter("restaurantType");
		if (restaurantType==null) {
			restaurantType="";}
		
		// 透過DAO元件Access Dept Table
		List<Restaurant> restaurantList = restaurantDAO.findRestaurant(restaurantName,restaurantAddress,test,restaurantType);
		if (restaurantList == null)
			try {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorPage/showError.jsp");
				dispatcher.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		else
			req.setAttribute("restaurantList", restaurantList);
		try {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/restaurantForm.jsp");
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	} */
	
	
	// 查詢餐廳 by number
	private void restaurantQueryByNmuber(HttpServletRequest req, HttpServletResponse res, RestaurantDAO restaurantDAO)
			throws SQLException, IOException {
		// 讀取部門代號
		String restaurantNumber = req.getParameter("restaurantNumber");
		
		// 透過DAO元件Access Dept Table
		Restaurant restaurant = restaurantDAO.findRestaurantByNumber(Integer.parseInt(restaurantNumber));
		if (restaurant == null)
			try {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorPage/showError.jsp");
				dispatcher.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		else
			try {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/restaurantForm.jsp");
				dispatcher.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		
	}
		
	// 新增餐廳
	private void processCreate(HttpServletRequest req, HttpServletResponse res, RestaurantDAO restaurantDAO)
			throws SQLException, IOException {
		String restaurantNumber = req.getParameter("restaurantNumber");
		String restaurantName = req.getParameter("restaurantName");
		String restaurantTel = req.getParameter("restaurantTel");
		String restaurantAddress = req.getParameter("restaurantAddress");
		String restaurantCategory = req.getParameter("restaurantCategory");
		String restaurantType = req.getParameter("restaurantType");
		String restaurantBusinessHours = req.getParameter("restaurantBusinessHours");
		String restaurantScore = req.getParameter("restaurantScore");
		
		
		Restaurant restaurant =new Restaurant(Integer.valueOf(restaurantNumber), restaurantName, restaurantTel, restaurantAddress, restaurantCategory, restaurantType, restaurantBusinessHours, restaurantScore);

		Boolean insertBoolean = restaurantDAO.createRestaurant(restaurant);
		if (insertBoolean) {
			try {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/restaurantForm.jsp");
				dispatcher.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
		
	} 

	
	// 刪除餐廳
	private void restaurantDeleteByNumber(HttpServletRequest req, HttpServletResponse res, RestaurantDAO restaurantDAO)
			throws SQLException, IOException {
		
		String restaurantNumber = req.getParameter("restaurantNumber");
		// 透過DAO元件Access Dept Table
		Boolean booleanDelete = restaurantDAO.deleteRestaurantByNumber(Integer.parseInt(restaurantNumber));
		if (booleanDelete)
			try {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/restaurantForm.jsp");
				dispatcher.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		
		
	}
	
	// 修改餐廳
	private void restaurantUpdate(HttpServletRequest req, HttpServletResponse res, RestaurantDAO restaurantDAO)
			throws SQLException, IOException {
		String restaurantNumber = req.getParameter("restaurantNumber");
		String restaurantName = req.getParameter("restaurantName");
		String restaurantTel = req.getParameter("restaurantTel");
		String restaurantAddress = req.getParameter("restaurantAddress");
		String restaurantCategory = req.getParameter("restaurantCategory");
		String restaurantType = req.getParameter("restaurantType");
		String restaurantBusinessHours = req.getParameter("restaurantBusinessHours");
		String restaurantScore = req.getParameter("restaurantScore");

		Restaurant restaurant = restaurantDAO.findRestaurantByNumber(Integer.parseInt(restaurantNumber));
		if (restaurant == null)
			try {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/showError.jsp");
				dispatcher.forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		else {
			restaurant.setRestaurantName(restaurantName);
			restaurant.setRestaurantTel(restaurantTel);
			restaurant.setRestaurantAddress(restaurantAddress);
			restaurant.setRestaurantCategory(restaurantCategory);
			restaurant.setRestaurantType(restaurantType);
			restaurant.setRestaurantBusinessHours(restaurantBusinessHours);
			restaurant.setRestaurantScore(restaurantScore);
			if (restaurantDAO.updateRestaurant(restaurant))
				try {
					RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/restaurantForm.jsp");
					dispatcher.forward(req, res);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
		}
	}


	/* 刪除成功
		private void showDelete(HttpServletResponse res, String message) throws IOException {
			PrintWriter out = res.getWriter();
			out.println("刪除成功");
		}
	
	  showError
	private void showError(HttpServletResponse res, String message) throws IOException {
		PrintWriter out = res.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Restaurant Form</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY BGCOLOR='#FDF5E6'>");
		out.println("message:" + message);
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}
	

	// 顯示表單
	private void showForm(HttpServletResponse res, Restaurant restaurant) throws IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>restaurant</title>\r\n"
				+ "<style>\r\n"
				+ "    h1{\r\n"
				+ "        text-align: center;\r\n"
				+ "    }\r\n"
				+ "    body{\r\n"
				+ "        background-color:rgb(250, 243, 250);\r\n"
				+ "    }\r\n"
				+ "</style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h1>餐廳資料表</h1>"
				+ "   <FORM ACTION='./RestaurantServletDS' method=\"get\">");
		out.println("  <div>\r\n"
				+ "        <label> 餐廳編號：</label><input type=\"text\" name=\"restaurantNumber\" size=\"15\" VALUE='" + restaurant.getRestaurantNumber() + "'><BR>");
		out.println("  <div>\r\n"
				+ "        <label> 餐廳名稱：</label><input type=\"text\" name=\"restaurantName\" size=\"15\" VALUE='" + restaurant.getRestaurantName() + "'><BR>");
		out.println("  <div>\r\n"
				+ "        <label> 餐廳電話：</label><input type=\"text\" name=\"restaurantTel\" size=\"15\" VALUE='" + restaurant.getRestaurantTel() + "'><BR>");
		out.println("  <div>\r\n"
				+ "        <label> 餐廳地址：</label><input type=\"text\" name=\"restaurantAddress\" size=\"30\" VALUE='" + restaurant.getRestaurantAddress() + "'><BR>");
		out.println("餐廳類型:<INPUT TYPE='TEXT' NAME='restaurantCategory' VALUE='" + restaurant.getRestaurantCategory() + "'><BR>");
		out.println("素食種類:<INPUT TYPE='TEXT' NAME='restaurantType' VALUE='" + restaurant.getRestaurantType() + "'><BR>");
		out.println("<label>營業時間:</label><INPUT TYPE='TEXT' NAME='restaurantBusinessHours' size=\"50\" VALUE='" + restaurant.getRestaurantBusinessHours() + "'><BR>");
		out.println("評分:<INPUT TYPE='TEXT' NAME='restaurantScore' VALUE='" + restaurant.getRestaurantScore() + "'><BR>");
		out.println(" <CENTER>");
		out.println("<input name='回到首頁' type ='button' onclick='history.back()' value='回到上一頁'></input>");
		out.println("<INPUT NAME='所有餐廳'  TYPE='SUBMIT' VALUE='所有餐廳'>");
		out.println("<INPUT NAME='查詢餐廳'  TYPE='SUBMIT' VALUE='查詢餐廳'>");
		out.println("<INPUT NAME='新增餐廳' TYPE='SUBMIT' VALUE='新增餐廳'>");
		out.println("<INPUT NAME='刪除餐廳' TYPE='SUBMIT' VALUE='刪除餐廳'>");
		out.println("<INPUT NAME='修改餐廳' TYPE='SUBMIT' VALUE='修改餐廳'>");
		out.println("</CENTER>");
		out.println("</FORM>");
		out.println("</BODY>");
		out.println("</HTML>");
		
	}
	*/

	/* 顯示所有餐廳的表單 restaurantForm2
		private void restaurantForm2(HttpServletResponse res, List<Restaurant> restaurantlist) throws IOException {
			
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			out.println("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"UTF-8\">\r\n"
					+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "<title>Restaurant Table</title>\r\n"
					+ "<style>\r\n"
					+ "*{\r\n"
					+ "    padding:1px;margin:1px auto\r\n"
					+ "}\r\n"
					+ ".tb1{\r\n"
					+ "width:1200px;\r\n"
					+ "border:3px solid rgb(88, 48, 2);\r\n"
					+ "border-collapse: collapse;\r\n"
					+ "}\r\n"
					+ "td,th{\r\n"
					+ " border:1px solid rgb(121, 120, 120);\r\n"
					+ "}\r\n"
					+ "thead{\r\n"
					+ "background-color: aquamarine;\r\n"
					+ "color: rgb(30, 17, 215);\r\n"
					+ "text-transform: capitalize;\r\n"
					+ "}\r\n"
					+ "tbody{\r\n"
					+ "background-color: rgb(255, 127, 197);\r\n"
					+ "text-align: center;\r\n"
					+ "}\r\n"
					+ "tbody tr:nth-child(2n){\r\n"
					+ "background-color:yellow;\r\n"
					+ "}\r\n"
					+ "tbody tr:nth-child(2n-1){\r\n"
					+ "background-color:rgb(255, 157, 0);\r\n"
					+ "}\r\n"
					+ "tbody tr:hover{\r\n"
					+ "background-color:rgb(208, 219, 219);\r\n"
					+ "color: antiquewhite;\r\n"
					+ "}\r\n"
					+ ".p1{\r\n"
					+ "    text-align: center;\r\n"
					+ "}\r\n"
					+ "</style>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					
					+ "<FORM ACTION='./RestaurantServletDS'>\r\n"
					
					+ "<table class=\"tb1\">\r\n"
					+ "<caption><h1>餐廳資料</h1></caption>\r\n"
					+ "<thead>\r\n"
					+ " <tr>\r\n"
					+ "<th>餐廳編號</th>\r\n"
					+ "<th>餐廳名稱</th>\r\n"
					+ "<th>餐廳電話</th>\r\n"
					+ "<th>餐廳地址</th>\r\n"
					+ "<th>餐廳類型</th>\r\n"
					+ "<th>素食種類</th>\r\n"
					+ "<th>營業時間</th>\r\n"
					+ "<th>評分</th>\r\n"
					+ "</tr>\r\n"
					+ "</thead>\r\n"
					+ "<tbody>");
			for(Restaurant restaurant : restaurantlist) {
				out.println("<tr>");
				out.println("<td>"+restaurant.getRestaurantNumber()+"</td>");
				out.println("<td>"+restaurant.getRestaurantName()+"</td>");
				out.println("<td>"+restaurant.getRestaurantTel()+"</td>");
				out.println("<td>"+restaurant.getRestaurantAddress()+"</td>");
				out.println("<td>"+restaurant.getRestaurantCategory()+"</td>");
				out.println("<td>"+restaurant.getRestaurantType()+"</td>"+"<br>");
				out.println("<td>"+restaurant.getRestaurantBusinessHours()+"</td>");
				out.println("<td>"+restaurant.getRestaurantScore()+"</td>");
				out.println("</tr>");
				}
			
			out.println("</tbody>\r\n");
			out.println("</table>\r\n");
			out.println("</FORM>");
			out.println("<p class=\"p1\"><input name='回到上一頁' type ='button' onclick='history.back()' value='回到上一頁'></input></p>");
			out.println("<link rel=\"stylesheet\" href=\"/../../map.jsp\">");
			out.println("</BODY>");
			out.println("</HTML>");		
		}
	 */
}
