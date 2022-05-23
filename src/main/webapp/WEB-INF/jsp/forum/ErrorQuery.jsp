<%@ page pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1 不允許瀏覽器站存
response.setHeader("Pragma","no-cache"); // HTTP 1.0 不允許瀏覽器站存
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server  不允許瀏覽器站存
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Query</title>
<style>
p{
  text-align:center;
}
</style>
</head>
<body>
<form action=".\ForumServlet" method="post">
<p>您輸入的編號不存在請重新查詢</p>

<p><input type="submit" name="ForumHome" value="回首頁" ></p>
</form>
</body>
</html>