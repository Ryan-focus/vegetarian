<%@ page import="bean.ForumBean" %>
<%@ page import="java.sql.*" %>

<%@page import="java.util.*"%>
<%@page import="dao.ProductDao"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.*"%>
<%@page import="bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%User userForum = (User)request.getSession().getAttribute("user");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Management</title>
<style>
.box{
	width:  75%;
	padding: 10px;
}
.ellipsis {
	overflow:hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
	white-space: normal;
	}
	.button{
		display: flex;
    	justify-content: left; 
   		align-items: left;
   		padding: 30px;
	}
</style>
</head>
<body>

<form action="./ForumServlet" method=Post>

	<sql:setDataSource var="myDS" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
		url="jdbc:sqlserver://localhost:1433;databaseName=veganDB" user="sa" password="passw0rd" />

	<sql:query var="forum" dataSource="${myDS}">
        SELECT * FROM forum;
    </sql:query>

	<div align="center">

<h1>網誌</h1>
   <p  class="button"><input type="submit" name="ForumHome" value="新增文章" ></p>
<br>
			<c:forEach var="forum" items="${forum.rows}">
				<h2><c:out value="${forum.vgetheme}" /></h2>
				<div class ="box">
				<p class="ellipsis"><c:out value="${forum.vgecontent}" /></p> 
				<div align="right" ><c:out  value="${forum.vgename}"></c:out></div>
				<a href="./ForumPages?=ForumPages&id=${forum.vgeid}"> 詳細閱讀</a>   
				
				<hr align="left" noshade="false" size="4" width="100%" color="#00EC00">
				</div>


 		<%--	<a href="./ForumServlet?=ForumPages&id=${forum.vgeid}"> 繼續閱讀</a>  --%>  
			

			</c:forEach>
			

	</div>
	
	</form>
</body>
</html>