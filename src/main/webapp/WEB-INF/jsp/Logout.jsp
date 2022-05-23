<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="3; url=/vegetarian/index.jsp">
<title>Log out</title>
</head>
<body>

<%session.invalidate();%>

 <script type="text/javascript">
 alert("已登出\r返回主頁");
 
 window.location='/vegetarian/index.jsp';
 </script>
 
<h1>您已登出<br><br>將返回主畫面</h1>
</body>
</html>