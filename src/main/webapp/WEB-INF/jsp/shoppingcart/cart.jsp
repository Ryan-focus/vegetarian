<%@page import="java.text.DecimalFormat"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.*"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DataSource ds = null;
Connection conn = null;

{
	try {
		InitialContext ctxt = new InitialContext();
		ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User user2 = (User) request.getSession().getAttribute("user");

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	ProductDao pDao = new ProductDao(ds.getConnection());
	cartProduct = pDao.getCartProducts(cart_list);
	double total = pDao.getTotalCartPrice(cart_list);
	request.setAttribute("total", total);
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<%-- <%@include file="includes/head.jsp"%> --%>
<title>愛素購物車</title>
<style type="text/css">
.table tbody td {
	vartical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 20px;
}
<%@include file="/WEB-INF/jsp/shoppingcart/bootstrap.jsp"%>

</style>
</head>
<body>
<%@include file="/WEB-INF/jsp/shoppingcart/ShoppingCartBar.jspf"%>
	<div class="container my-3">
		<div class="d-flex py-3">
			<h3>總金額為： ${ (total>0)? dcf.format(total):0} 元</h3>
			<a class="mx-3 btn btn-primary" href="/ShoppingCartServlet?action=cart-check-out">結帳</a>
		</div>

		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">品項</th>
					<th scope="col">種類</th>
					<th scope="col">價格</th>
					<th scope="col">購買數量</th>
					<th scope="col">取消</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProduct) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=dcf.format(c.getPrice())%>$</td>
					<td>
						<form action="ShoppingCartServlet?action=order-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%=c.getId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-incre" href="/ShoppingCartServlet?action=quantity-inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a>
								<input type="text" name="quantity" class="form-control w-50" value="<%=c.getQuantity()%>" readonly>
								<a class="btn btn-sm btn-decre" href="/ShoppingCartServlet?action=quantity-dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm" >立即購買</button>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger" href="ShoppingCartServlet?action=remove-from-cart&id=<%= c.getId() %>">移除</a></td>

				</tr>
				<%
				}}%>
			</tbody>
		</table>

	</div>
	<%@include file="/WEB-INF/jsp/shoppingcart/Script.jsp"%>
	<%@include file="/WEB-INF/jsp/parts/footer.jspf"%>
</body>
</html>