<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%!int urlStatus = 3;%>
<% if (request.getAttribute("urlStatus") != null) urlStatus = (int) request.getAttribute("urlStatus");%>
<% if (urlStatus == 0){%>
<meta charset="UTF-8" http-equiv="refresh" content="3; url=/vegetarian/index.jsp">
<%} else if (urlStatus == 1) {%>
<meta charset="UTF-8" http-equiv="refresh" content="3; url=/vegetarian/index.jsp">
<%} else if (urlStatus == 2) {%>
<meta charset="UTF-8" http-equiv="refresh" content="3; url=/vegetarian/index.jsp">
<%} else {%>
<meta charset="UTF-8" http-equiv="refresh" content="3; url=/vegetarian/Login">
<%}%>
<title>Login Result</title>
<style>
h2 {
	margin-top: 85px;
}
h3 {
	margin-top: 85px;
}
</style>
</head>
<body BGCOLOR="#fcfcfc">
<h2 style="text-align: center"><%= request.getAttribute("result")%></h2>
<h3 style="text-align: center">3秒後跳轉頁面....</h3>
</body>
</html>