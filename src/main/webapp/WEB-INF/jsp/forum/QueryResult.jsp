<%@ page import="bean.ForumBean" %>
<%@ page import="java.sql.*" %>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
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
	<jsp:useBean id="vge" class="bean.ForumBean" scope="session" />
	<h2>文章查詢</h2>
	<form action=".\ForumServlet" method="post">
		<header>
		<h2>網誌</h2>
		</header>

	 	<c:forEach var='forum'  items='${forumBean}'>
			<p class="p">文章號碼: <input type="text" readonly="readonly" name="vgeid" value="${forum.vgeid}" size="30" maxlength="30"></p>  
			<p class="p">名稱:<input type="text" name="vgename"  value="${forum.vgename}" size="30" maxlength="30"></p> 
			<p class="p">標題:<input type="text" name="vgetheme" value="${forum.vgetheme}" size="30" maxlength="30"></p> 
			<p class="p">文章:<textarea name="vgecontent" rows="10"  cols="30">${forum.vgecontent}</textarea></p> 
	 	</c:forEach>
	
			<hr class="style-five">
	<center>
			<input type="submit" name="Update" value="更新網誌" >
			<input type="submit" name="Delete" value="刪除網誌" ><br>
			<p><input type="submit" name="ForumHome" value="回首頁" ></p>
	</center>
	</form>
</body>
</html>