<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="3; url=/vegetarian/Login">
<title>Reserve Result</title>
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
boolean isSuccess = (boolean)request.getAttribute("isSuccess");
String results = (String) request.getAttribute("results");
if (isSuccess) {
%>

<script type="text/javascript">
alert("預訂餐廳<%=results%>\r");
	 
window.location='/vegetarian/index.jsp';
</script>

<%} else {%>
<script type="text/javascript">
alert("預訂餐廳失敗<%=results%>\r再試一次");
window.location='/vegetarian/index.jsp';
</script>
<%
}
%>
</body>
</html>