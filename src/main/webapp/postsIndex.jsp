<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.*"%>
<%@ page pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="bean.Post"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>食記分享</title>
<style>
* {
	margin: 0;
	padding: 0;
}

.ellipsis {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 6;
	-webkit-box-orient: vertical;
	white-space: normal;
	margin-right: 10px
}

.textbody {
	background-color: #f6f8fc;
	margin: 0 auto;
	max-width: 1600px;
}

.posts {
	background-color: #f6f8fc;
	margin: 0 auto;
	width: 80%;
}

h3 {
	padding: 10px
}

.pic {
	float: left;
	margin: 10px;
}

.img1 {
	height: 200px;
	width: 300px;
}

.box {
	height: 250px;
	display: flex;
	align-items: center;
	margin: 10px;
	border-style: double;
	border-color: #E6E8E6;
}
</style>
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
</head>
<body>
	<div class="textbody">
		<div class="posts">
			<h3 style="text-align: center;">文章列表</h3>
			<!-- Filter無法套用在使用javascript寫建立window.location的方法. -->
        
			<input type="button"
				onclick="window.location.href='/vegetarian/createpost';"
				value="發表文章" />
		
			<hr>

			<c:forEach var='post' varStatus='vs' items='${postlist}'>

				<div>
					<h3>${post.title}</h3>
					<p>${post.postedDate}</p>
					<div class="box">
						<div class="pic">
							<img class="img1" src="${post.imgurl}">
						</div>
						<div class="ellipsis">${post.postedText}</div>
					</div>
					<div class="con">
						<a href="./post?action=showPost&id=${post.postId}"> 繼續閱讀</a>
					</div>
					<hr>

					<a href='./post?action=deletePost&id=${post.postId}' id="delete">刪除文章</a>
					<a href='./post?action=editPost&id=${post.postId}'>編輯文章</a>
					<hr>

					<br />
				</div>
			</c:forEach>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	<script>
		$(function() {
			$('#delete').confirm({
				title : '',
				content : "請問是否確定刪除？",
				animation : 'zoom',
				closeAnimation : 'scale',
				buttons : {
					是 : function() {
						location.href = this.$target.attr('href');
					},
					否 : function() {

					}
				}
			});
		})
	</script>

</body>
</html>