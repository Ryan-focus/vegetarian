<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Management</title>
</head>
<body>
	<sql:setDataSource var="myDS" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
		url="jdbc:sqlserver://localhost:1433;databaseName=veganDB" user="sa" password="passw0rd" />

	<sql:query var="listUsers" dataSource="${myDS}">
        SELECT * FROM users;
    </sql:query>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>會員資料</h2>
			</caption>
			<tr>
				<th>UID</th>
				<th>Email</th>
				<th>PASSWORD</th>
				<th>USERNAME</th>
				<th>STATUS</th>
			</tr>
			<c:forEach var="user" items="${listUsers.rows}">
				<tr>
					<td><c:out value="${user.uid}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.password}" /></td>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.status}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>