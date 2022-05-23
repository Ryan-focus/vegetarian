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
    *{
	margin: 0;
	padding: 0;
			}
    .box {
	width: 100%;
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
	.textbody{background-color: #f6f8fc ; margin:0 auto;max-width: 1600px; }
	.posts{background-color: #f6f8fc ;margin:0 auto; width: 80%;}
	h3{padding:20px}
	
	
</style>
</head>
<body >
	<div class="textbody">
	<div class="posts">
	<h3 style="text-align:center ;">文章列表</h3>
	<!-- Filter無法套用在使用javascript寫建立window.location的方法. -->
	<input type="button" onclick="window.location.href='/vegetarian/createpost';" value="發表文章" />
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
		</div>
		</div>
		
</body>
</html>