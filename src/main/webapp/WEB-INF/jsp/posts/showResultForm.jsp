<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>食記分享</title>
<style>
        body {
            background-color: antiquewhite;
        }
        div{
            text-align: center;
        }
    </style>
</head>
<body>
  <div>
        <h2>${message}</h2>
        <br>
        <input type="button" onclick="javascript:window.location.href='createPost.jsp' ;" value="繼續發表" />
        <input type="button" onclick="javascript:window.location.href='PostsTop.jsp' ;" value="返回首頁" />
    </div>
</body>
</html>