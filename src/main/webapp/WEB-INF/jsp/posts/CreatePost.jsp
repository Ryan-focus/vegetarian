<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增文章</title>
    <style>
        form {text-align: center;}
        h3 {text-align: center; }
        .text_title {margin:10px;width: 80%;resize: none;}
        .title {padding: 20px}
        .text{padding:5px}
        .textbody{background-color: #f6f8fc ; }
        
            
    </style>
</head>

<body>
<div class="textbody">
	<div class="title">
    <h3>新增文章</h3>
    <hr>
    </div>
    <form action="./PostServlet" method="get">
    <div class="text">
        <h5 class="text">文章標題:</h5>
        <input class="text_title" type="text" name="title" maxlength="100" onkeyup="this.value=this.value.replace(/\s+/g,'')" required/>
        <h5 class="text">文章內容:</h5>
        <textarea class="text_title" name="postedText" rows="20"  required></textarea>
        <br />
        <br />
        <input type="submit" name="add" value="發表文章" />
        </div>
    </form>
    </div>
    </body>
</html>