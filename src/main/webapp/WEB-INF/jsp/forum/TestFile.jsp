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
	.button{
		display: flex;
    	justify-content: left; 
   		align-items: left;
   		padding: 30px;
	}
</style>
</head>
<body>
<form action="./ForumHibernateServlet" method=Post>

<%-- 	<sql:setDataSource var="myDS" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" --%>
<%-- 		url="jdbc:sqlserver://localhost:1433;databaseName=veganDB" user="sa" password="passw0rd" /> --%>

<%-- 	<sql:query var="forum" dataSource="${myDS}"> --%>
<!--         SELECT * FROM forum; -->
<%--     </sql:query> --%>

	<div align="center">

<h1>網誌</h1>
   <p  class="button"><input type="submit" name="ForumHome" value="新增文章" ></p>
<br>
		
		<c:forEach var='forum' varStatus="vs" items='${allMembers}'>
			<c:if test ='${vs.first}'>
				<c:out value="<table border='1'>" escapeXml='false'/>
				<c:out value="<tr><td>帳號</td><td>會員名稱</td><td>密碼</td></tr>" escapeXml='false'/>
				<c:out value="</table>" escapeXml='false'/>
			</c:if>
		</c:forEach>

 		<%--	<a href="./ForumServlet?=ForumPages&id=${forum.vgeid}"> 繼續閱讀</a>  --%>  
			

	
			

	</div>
	
	</form>
</body>
</html>