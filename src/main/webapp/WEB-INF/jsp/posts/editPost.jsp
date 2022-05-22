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
    <title>編輯文章</title>
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
    <h3>編輯文章</h3>
    </div>
    <form action="PostServlet" method="get">
        <h5>文章標題:</h5>
        <input class="text_title" type="text" name="title" maxlength="100" onkeyup="this.value=this.value.replace(/\s+/g,'')" value="${title}" required/>
        <h5>文章內容:</h5>
        <textarea class="text_title" name="postedText"  rows="20"  required>${posted_text}
    </textarea>
        <br />
        <br />
        <input type="submit" value="更新文章" />
        <input type='hidden' name='update' value= "${post_id}" >
    </form>
    </div>
    </body>
</html>