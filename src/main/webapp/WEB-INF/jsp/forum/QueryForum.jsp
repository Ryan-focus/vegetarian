 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
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
<title>新增資料</title>
</head>
<body>
<h2>
查詢文章
</h2>
<form action="./ForumServlet" method="post">

<p>使用編號查詢文章或填寫以下表格新增文章</p>
<p>編號:<input type="text" name="vgeid" size="10" maxlength="10"></p>
<p>名稱:<input type="text" name="vgename" size="10" maxlength="10"></p>
<p>標題:<input type="text" name="vgetheme" size="50" maxlength="30"></p>
<p>文章:<textarea name="vgecontent" rows="6" cols="40"></textarea></p>
 
<center>

<input type="submit" name="Query" value="查詢" >
<input type="submit" name="Create" value="新增文章" >
</center>
</form>
</body>
</html>