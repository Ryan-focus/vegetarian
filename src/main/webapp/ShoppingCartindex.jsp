<%@page import="java.util.*"%>
<%@page import="dao.ProductDao"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.*"%>
<%@page import="bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

DataSource ds = null;
Connection conn = null;


	try {
		InitialContext ctxt = new InitialContext();
		ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/veganDB");
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

ProductDao pd = new ProductDao(ds.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {

	request.setAttribute("cart_list", cart_list);
}
%>

<!DOCTYPE html>
<html>
<head>
<title>歡迎來到素構易</title>
<%@include file="WEB-INF/jsp/parts/bootstrap.jsp"%>
</head>
<body>

	<%@include file="WEB-INF/jsp/parts/ShoppingCartBar.jsp"%>

	<div class="container">
		<div class="card-header my-3">全部商品</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100 " style="width: 18rem;">
					<img class="card-img-top" src="product-images/<%=p.getImage()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName()%></h5>
						<h6 class="price"><%=p.getPrice()%></h6>
						<h6 class="category"><%=p.getCategory()%></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a href="ShoppingCartServlet?action=add-to-cart&id=<%=p.getId()%>" class="btn btn-dark">加到購物車</a>
							<a href="ShoppingCartServlet?action=order-now&quantity=1&id=<%=p.getId()%>"
								class="btn btn-primary">立即購買</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>



		</div>
	</div>
	<%@include file="WEB-INF/jsp/parts/Script.jsp"%>
	<%@include file="WEB-INF/jsp/parts/footer.jsp"%>

</body>
</html>