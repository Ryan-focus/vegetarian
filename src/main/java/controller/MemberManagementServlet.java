package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import bean.User;
import dao.MemberManagementDAO;

public class MemberManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberManagementDAO dao;

	public MemberManagementServlet() {

		dao = new MemberManagementDAO();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("action") != null) {

			List<User> IsUser = new ArrayList<User>();
			String action = (String) request.getParameter("action");
			Gson gson = new Gson();
			response.setContentType("application/json");

			if (action.equals("list")) {

				try {
					
					int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
					int numRecordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
					
					IsUser = dao.getAllUsers(startPageIndex, numRecordsPerPage);
					int userCount = dao.getUserCount();
					
					JsonElement element = gson.toJsonTree(IsUser, new TypeToken<List<User>>(){}.getType());
					JsonArray jsonArray = element.getAsJsonArray();
					String listData = jsonArray.toString();
					listData = "{\"Result\":\"OK\",\"Records\":" + listData +
							",\"TotalRecordCount\":" + userCount + "}";
					response.getWriter().print(listData);

				} catch (Exception ex) {
					
					ex.printStackTrace();
				}

			} else if (action.equals("create") || action.equals("update")) {

				User user = new User();
				
				if (request.getParameter("uid") != null) {

					int uid = Integer.parseInt(request.getParameter("uid"));
					user.setUid(uid);

				}

				if (request.getParameter("email") != null) {

					String email = (String) request.getParameter("email");
					user.setEmail(email);

				}

				if (request.getParameter("password") != null) {

					String password = (String) request.getParameter("password");
					user.setPassword(password);

				}

				if (request.getParameter("username") != null) {

					String username = (String) request.getParameter("username");
					user.setUsername(username);

				}

				if (request.getParameter("status") != null) {

					String status = (String) request.getParameter("status");
					user.setStatus(status);

				}

				try {

					if (action.equals("create")) {

						dao.addUser(user);
						IsUser.add(user);

						String json = gson.toJson(user);

						String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
						response.getWriter().print(listData);

					} else if (action.equals("update")) {

						dao.updateUser(user);
						String listData = "{\"Result\":\"OK\"}";
						response.getWriter().print(listData);

					}
					
				} catch (Exception ex) {

					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace() + "}";
					response.getWriter().print(error);

				}
				
			} else if (action.equals("delete")) {

				try {

					if (request.getParameter("uid") != null) {

						String uid = (String) request.getParameter("uid");
						dao.deleteUser(Integer.parseInt(uid));
						String listData = "{\"Result\":\"OK\"}";
						response.getWriter().print(listData);

					}

				} catch (Exception ex) {

					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}

			}

		}

	}

}
