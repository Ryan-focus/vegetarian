<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/parts/meta.jsp" %>
<meta charset="UTF-8" http-equiv="refresh" content="3; url=/vegetarian/Login">
<title>Register Result</title>
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
<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />
<h2 style="text-align: center">註冊<%= request.getAttribute("result") %></h2>
<h3 style="text-align: center">3秒後跳轉頁面....</h3>
</body>
</html>