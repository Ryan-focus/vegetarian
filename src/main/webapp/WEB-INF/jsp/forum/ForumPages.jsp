<%@ page import="bean.ForumBean"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.lang.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>${title}</title>

<style>
				p {
					text-align: center
				}
				
				.box {
					width: 75%;
					padding: 10px;
					margin: auto;
					display: flex;
					justify-content: center;
					align-items: center;
				}
				
				.button{
					display: flex;
    				justify-content: center; 
   			    	align-items: center;
				}
				
				.ellipsis {
					text-align: center
				}
</style>
</head>
<body>
	<form action="./ForumServlet" method=Post>

		<sql:setDataSource var="myDS"
			driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
			url="jdbc:sqlserver://localhost:1433;databaseName=veganDB" user="sa"
			password="passw0rd" />

		<sql:query var="forum" dataSource="${myDS}">
        SELECT * FROM forum where vgeid = ${param.id}
    </sql:query>



		<c:forEach var="forum" items="${forum.rows}">
			<h2 style="text-align: center">
				<c:out value="${forum.vgetheme}" />
			</h2>
			<div class="box">
				<p>
					<c:out value="${forum.vgecontent}" />
				</p>
			</div>
		</c:forEach>
		<div class="button">
			<input class="button" type="button"
				onclick="javascript:window.location.href='./forumIndex' ;"
				value="返回首頁" />
		</div>

	</form>
</body>
</html>