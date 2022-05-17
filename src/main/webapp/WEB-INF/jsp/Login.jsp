<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/jsp/parts/meta.jsp" %>  
<title>Login</title>
<style>
		* {
			margin: 0;
			padding: 0;
		}
		
		h1 {
			margin: 50px auto;
			font-family: "Comic Sans MS";
		}
		
        fieldset {
        	font-size: 18px;
        	font-family:微軟正黑體;
        	color: white;
            width: 500px;
            border: 0px none;
            border-radius: 15px;
            margin: auto;
            margin-top: -2px;
            background-color: #001366;
        }
        /*fieldset {
            width: 500px;
            border: 1px solid darkolivegreen;
            border-radius: 10px;
            margin: auto;
            background-color: #FFF3EE;
        }*/

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
        
    </style>
</head>
<body BGCOLOR="#fcfcfc">
<jsp:include page="/WEB-INF/jsp/parts/header.jsp" />
<H1 ALIGN="CENTER">Login Form</H1>
<FORM ACTION="./UserServlet" method="POST">
<fieldset>
            <div class="st1">
                <label for="email1" class="t1">E-mail：</label><input type="email" name="email" maxlength="50" id="email1" autocomplete="off" required>
            </div>
            <div class="st1">
                <label for="pwd1" class="t1">密碼：</label><input type="password" name="password" maxlength="20" id="pwd1" autocomplete="off" required>
            </div>
         <CENTER>
			<INPUT TYPE="SUBMIT" VALUE="送出"> <INPUT TYPE="reset"
				VALUE="清空" /> <BR> <BR> <INPUT TYPE="button"
				VALUE="註冊"
				ONCLICK="location.href='/vegetarian/UserRegister'"> <br> <br>
		</CENTER>
            </div>
        </fieldset>
</FORM>
</body>
</html>