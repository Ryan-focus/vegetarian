<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title> ${title}</title>

    <style>
        body {
            background-color: antiquewhite;
            margin-left: 200PX;
            margin-right: 200PX;
        }

        form {
            text-align: center;
        }

        h2 {
            text-align: center;
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
    </style>
</head>
<body>

 <h2>${title}</h2>
    <p class="title"> ${posted_date}  </p>
    <br>
    <pre> ${posted_text}</pre>
    <br>
    <br>
    

</body>
</html>