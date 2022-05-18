<%@page import="dao.OrderDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="bean.*"%>
<%@page import="java.util.*"%>
<%@page import="dao.OrderDao"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.*"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DataSource ds = null;
Connection conn = null;


	try {
		InitialContext ctxt = new InitialContext();
		ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");
List<Order> orders = null;
if (auth != null) {
	request.setAttribute("auth", auth);
	orders = new OrderDao(ds.getConnection()).userOrders(auth.getUid());

} else {
	response.sendRedirect("login.jsp");
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Orders</title>
<%@include file="parts/bootstrap.jsp"%>
</head>
<body>
	<%@include file="parts/ShoppingCartBar.jsp"%>

	<div class="container">
		<div class="card-header my-3">全部訂單</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">日期</th>
					<th scope="col">品項</th>
					<th scope="col">種類</th>
					<th scope="col">數量</th>
					<th scope="col">金額</th>
					<th scope="col">取消訂單</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (orders != null) {
					for (Order o : orders) {
				%>
				<tr>
					<td><%=o.getDate()%></td>
					<td><%=o.getName()%></td>
					<td><%=o.getCategory()%></td>
					<td><%=o.getQuantity()%></td>
					<td><%=dcf.format(o.getPrice())%></td>
					<td><a class="btn btn-sm btn-danger"
						href="ShoppingCartServlet?action=cancel-order&id=<%=o.getOrderId()%>">取消訂單</a></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>

	</div>
	<%@include file="parts/Script.jsp"%>
</body>
</html>