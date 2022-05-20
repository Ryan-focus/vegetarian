<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.*" %>
<%@ page pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="bean.Post" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>食記分享</title>
<style>
    body{
         margin-left: 200PX;
        margin-right: 200PX;
    }
    .box {
	width: 800px;
	padding: 10px;
	
	}
	.ellipsis {
	overflow:hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 3;
	-webkit-box-orient: vertical;
	white-space: normal;
	}
</style>
</head>
<body style="background-color: antiquewhite",width: 800px;>
	
	<h2 style="text-align:center ;">文章列表</h2>
	<input type="button" onclick="javascript:window.location.href='/vegetarian/createpost';" value="發表文章" />
	<hr>
	
		<%
		List<Post> list = (List<Post>)request.getAttribute("postlist");
		%>
		
		<%
		for(Post post:list){ 
		%>
		
		<div>
		<h3><%=post.getTitle()%></h3>
        <p><%=post.getPostedDate() %></p>
        <div class ="box">
        <p class="ellipsis"><%=post.getPostedText() %></p> 
        </div>
        
        <a href="./PostServlet?action=showPost&id=<%=post.getPostId() %>"> 繼續閱讀</a>
        <hr>
        <a href='./PostServlet?action=deletePost&id=<%=post.getPostId() %>'>刪除文章</a>
        <a href='./PostServlet?action=editPost&id=<%=post.getPostId() %>'>編輯文章</a>
        <hr>
        <br/>
		</div>
		<% }%>
		
		
</body>
</html>