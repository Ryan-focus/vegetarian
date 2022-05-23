<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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

<%--<jsp:setProperty name="user" property="*" value=""/>--%>

<%session.invalidate();%>

<%
boolean isEmailExist = (boolean) request.getAttribute("isEmailExist");
String result = (String) request.getAttribute("result");
if (!isEmailExist) {
%>

<script type="text/javascript">
alert("註冊<%=result%>\r前往登入頁面");
	 
window.location='/vegetarian/index.jsp';
</script>

<%} else {%>

<script type="text/javascript">
alert("註冊<%=result%>\r帳號(email)已存在\r請重新註冊!");
	 
window.location='/vegetarian/index.jsp';
</script>

<%
}
%>
</body>
</html>