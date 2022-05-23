<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        body,html{
        		width: 100%;
				height: 100%;
        }
        .textbody{
        padding:10%;
            text-align: center;
            background-color: #f6f8fc;
           
        }
        .text{ position: relative;
  			top: 50%;  
  			}
    </style>
</head>
<body>
  <div class="textbody">
  <div class="text">
        <h2>${message}</h2>
        <br>
        <br>
        <input type="button" onclick="javascript:window.location.href='/vegetarian/createpost' ;" value="繼續發表" />
        <input type="button" onclick="javascript:window.location.href='./postIndex' ;" value="返回首頁" />
        </div>
    </div>
</body>
</html>