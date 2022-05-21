<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

<%!int urlStatus = 3;%>

<%String result = (String) request.getAttribute("result");%>

<%
if (request.getAttribute("urlStatus") != null)
   {urlStatus = (int) request.getAttribute("urlStatus");}
 if (urlStatus == 0) {
%>

 <script type="text/javascript">
 alert("<%=result%>\r未設定，前往主頁");
 
 window.location='/vegetarian/index.jsp';
 </script>
 
<%} else if (urlStatus == 1) {%>

 <script type="text/javascript">
 alert("<%=result%>\r未設定，前往主頁");
 
 window.location='/vegetarian/index.jsp';
 </script>
 
 <%} else if (urlStatus == 2) {%>
 
 <script type="text/javascript">
 alert("<%=result%>\r將前往主頁");
 
 window.location='/vegetarian/index.jsp';
 </script>
 
  <%} else {%>
  
 <script type="text/javascript">
 alert("<%=result%>\r帳號(email)或密碼錯誤\r請重新登入!");
 
 window.location='/vegetarian/Login';
 </script>
 
<%
 }
%>
</body>
</html>