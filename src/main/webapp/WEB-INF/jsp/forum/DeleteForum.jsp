<%@ page import="bean.ForumBean" %>
<%@ page import="java.sql.*" %>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%User userForum = (User)request.getSession().getAttribute("user");%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1 不允許瀏覽器站存
response.setHeader("Pragma", "no-cache"); // HTTP 1.0 不允許瀏覽器站存
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server  不允許瀏覽器站存
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查詢</title>
<style>
header {
	color: black;
	padding: 20px;
	text-align: center;
}

hr.style-five {
	border: 0;
	height: 0;
	/* Firefox... */
	box-shadow: 0 0 10px 1px black;
}
p{
  text-align:center;
}

</style>
</head>
<body>
<form action=".\ForumServlet" method="post">
	<jsp:useBean id="vge" class="bean.ForumBean" scope="session" />
<p>刪除成功</p>
<p><input type="submit" name="ForumHome" value="回首頁" ></p>
</form>
</body>
</html>