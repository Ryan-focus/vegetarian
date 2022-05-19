<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改成功</title>
<style>
	p{text-align:center;}
	h2{text-align:center;}
</style>
</head>
<body>
<jsp:useBean id="foruData" class="bean.ForumBean" scope="session" />

<h2>網誌修改成功</h2>
<form action=".\ForumServlet" method="post">
<p>編號:<input type="text" name="vgeid" size="30" value=" <jsp:getProperty name="foruData" property="vgeid"  />" maxlength="30"></p>
<p>名稱:<input type="text" name="vgeid" size="30" value=" <jsp:getProperty name="foruData" property="vgename"  />" maxlength="30"></p>
<p>標題:<input type="text" name="vgeid" size="30" value=" <jsp:getProperty name="foruData" property="vgetheme"  />" maxlength="30"></p>
<p>發表:<input type="text" name="vgeid" size="30" value=" <jsp:getProperty name="foruData" property="vgecontent"  />" maxlength="30"></p>
<p>文章:<textarea name="vgecontent" rows="6" cols="30"><jsp:getProperty name="foruData" property="vgecontent"  /></textarea></p>

<p><input type="submit" name="ForumHome" value="回首頁" ><p>
</form>
</body>
</html>