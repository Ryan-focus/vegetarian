<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>${post.title}</title>

<style>
* {
	margin: 0;
	padding: 0;
}

.text_title {
	padding: 2px;
	width: 500px;
}

.title {
	text-align: center;
}

pre {
	font-size: 16px;
	white-space: pre-wrap
}

textarea {
	height: 200px;
	width: 500px;
	resize: none;
}

.textbody {
	background-color: #f6f8fc;
	padding: 20px;
}

.posts {
	margin: 0 auto;
	width: 80%;
}

.button {
	text-align: center;
}

.img1 {
   height: 300px; 
    width: 400px; 
    
}
.pic{
	text-align: center;
	padding: 20px;
}
</style>
</head>
<body>
	<div class="textbody">
		<div class="posts">
			<h2 class="title">${post.title}</h2>
			<p class="title">${post.postedDate}</p>
			<br>
			<div class="pic">
				<img class="img1" src="${post.imgurl}">
			</div>
			<pre> ${post.postedText}</pre>
			<br>
			<div class="button">
				<input class="button" type="button"
					onclick="javascript:window.location.href='./postIndex' ;"
					value="返回首頁" />
			</div>
		</div>
	</div>
</body>
</html>