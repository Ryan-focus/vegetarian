<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="3; url=http://localhost:8080/TestWebDemo/Login.jsp">
<title>Login Result</title>
</head>
<body BGCOLOR="#DFFFDF">
<h2 style="text-align: center">登入<%= request.getAttribute("result") %></h2>
<h3 style="text-align: center">3秒後跳轉頁面....</h3>
</body>
</html>