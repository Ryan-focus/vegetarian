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
        body {background-color: antiquewhite;}

        form {text-align: center;}

        h2 {text-align: center;}

        .text_title {padding: 2px;width: 800px;}
        
        textarea {width: 8000px;resize: none;}
            
    </style>
</head>

<body>
    <h2>新增文章</h2>
    <form action="PostServlet" method="get">
        <h4>文章標題:</h4>
        <input class="text_title" type="text" name="title"  onkeyup="this.value=this.value.replace(/\s+/g,'')" value="${title}" required/>
        <h4>文章內容:</h4>
        <textarea class="text_title" name="postedText"  rows="20"  required>${posted_text}
    </textarea>
        <br />
        <br />
        <input type="submit" value="更新文章" />
        <input type='hidden' name='update' value= "${post_id}" >
    </form>
</html>