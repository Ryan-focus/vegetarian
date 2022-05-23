<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title> ${title}</title>

    <style>
    	*{
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
       .textbody{background-color: #f6f8fc ;padding:20px;}
       .posts{margin:0 auto; width: 80%; }
       .button{text-align: center;}
    </style>
</head>
<body>
<div class="textbody">
<div class="posts">
 <h2 class="title">${forum.vgetheme}</h2>
 
    <br>
    <pre> ${forum.vgecontent}</pre>
    <br>
    <div class="button">
    <input class="button" type="button" onclick="javascript:window.location.href='./postIndex' ;" value="返回首頁" />
    </div>
</div>    
</div>
</body>
</html>