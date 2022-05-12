<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
        fieldset {
            width: 500px;
            border: 1px solid darkolivegreen;
            border-radius: 10px;
            margin: auto;
            background-color: #FFF3EE;
        }

        .st1 {
            width: 450px;
            border-bottom: 1px dashed gray;
            margin: 20px;
            padding-bottom: 10px;
            font-family: "微軟正黑體";
        }

        .t1 {
            width: 100px;
            float: left;
            text-align: right;
        }
        
        h1 {
            font-family: "Comic Sans MS";
        }
        
    </style>
</head>
<body BGCOLOR="#DFFFDF">
<H1 ALIGN="CENTER">Login Form</H1>
<FORM ACTION="./UserServlet" method="POST">
<fieldset>
            <div class="st1">
                <label for="email1" class="t1">E-mail</label><input type="text" name="email" id="email1" autocomplete="off">
            </div>
            <div class="st1">
                <label for="pwd1" class="t1">密碼:</label><input type="text" name="password" maxlength="20" id="pwd1" autocomplete="off">
            </div>
         <CENTER>
			<INPUT TYPE="SUBMIT" VALUE="送出"> <INPUT TYPE="reset"
				VALUE="清空" /> <BR> <BR> <INPUT TYPE="button"
				VALUE="註冊"
				ONCLICK="location.href='http://localhost:8080/TestWebDemo/Register.jsp'">
		</CENTER>
            </div>
        </fieldset>
</FORM>
</body>
</html>