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
<title>新增文章</title>
</head>
<body>
<h2>
上傳文章
</h2>
<form action=".\ForumServlet" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr>
    <td>編號:</td>
    <td><input type="text" name="vgeid" size="10" maxlength="10"></td>
</tr>
<tr>
    <td>名稱:</td>
    <td><input type="text" name="vgename" size="10" maxlength="10"></td>
</tr>
<tr>
    <td>標題:</td>
    <td><input type="text" name="vgetheme" size="10" maxlength="10"></td>
</tr>
<tr>
    <td>發表:</td>
    <td><input type="text" name="vgecontent" size="10" maxlength="10"></td>
</tr>


</table>
<center>
<input type="submit" name="Create" value="新增">

</center>
</form>
</body>
</html>