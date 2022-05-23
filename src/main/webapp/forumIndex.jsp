<%@ page import="bean.ForumBean" %>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
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
</style>
</head>
<body>
<form action="./ForumServlet" method="post">

	<sql:setDataSource var="myDS" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
		url="jdbc:sqlserver://localhost:1433;databaseName=veganDB" user="sa" password="passw0rd" />

	<sql:query var="forum" dataSource="${myDS}">
        SELECT * FROM forum;
    </sql:query>

	<div align="center">

<h1>網誌</h1>
<div   align="left"><input type="submit" name="ForumHome" value="新增文章" ></div>
<br>
			<c:forEach var="forum" items="${forum.rows}">
				<h2><c:out value="${forum.vgetheme}" /></h2>
				<div class ="box">
				<p class="ellipsis"><c:out value="${forum.vgecontent}" /></p> 
				</div>

		
 				<a href="./ForumServlet?=ForumPages&id=${forum.vgeid}"> 繼續閱讀</a>

				<hr>
			</c:forEach>
			

	</div>
	
	</form>
</body>
</html>