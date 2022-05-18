<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<style>
		* {
			margin: 0;
			padding: 0;
		}
		
		h1 {
			margin-top: 0px;
            margin-bottom: 20px;
            font-family: "Comic Sans MS";
		}
		
        fieldset {
        	font-size: 18px;
        	font-family:微軟正黑體;
        	color: white;
            width: 500px;
            border: 0px none;
            border-radius: 0px 0px 15px 15px;
            margin: auto;
            margin-top: -2px;
            background-color: #001366;
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
        
        .b1 {
        	font-size: 18px;
        	font-family:微軟正黑體;
        	color: white;
        	width:250px;
        	height:40px;
        	border: 0px;
        	border-radius: 15px 15px 0px 0px;
        	margin-left: -4px;
        	background-color: #001366;
        }
        .b2 {
        	font-size: 18px;
        	font-family:微軟正黑體;
        	color: 	#F0F0F0;
        	width:250px;
        	height:40px;
        	border: 0px;
        	border-radius: 15px 15px 0px 0px;
        	background-color: #99ACFF;
        }
        
    </style>
</head>
<body BGCOLOR="#fcfcfc">
<H1 ALIGN="CENTER">Register Form</H1>
<form action="./BusinessRegisterServlet" method="post">
<div style="width: 500px;margin: auto;">
<input type="button" class="b2" value="會員" ONCLICK="location.href='/vegetarian/UserRegister'">
<input type="button" class="b1" value="商家" >
</div>
<fieldset>
            <div class="st1">
                <label for="email1" class="t1">E-mail：</label><input type="email" name="email" maxlength="50" id="email1" autocomplete="off" required>
            </div>
            <div class="st1">
                <label for="pwd1" class="t1">密碼：</label><input type="password" name="password" maxlength="20" id="pwd1" autocomplete="off"
                onblur="blurPass(this)">
                &nbsp;&nbsp;&nbsp;<span id="alertpw1"></span>
            </div>
            <div class="st1">
                <label for="pwd2" class="t1">確認密碼：</label><input type="password" maxlength="20" id="pwd2" autocomplete="off" 
                onblur="blurRepass(this)">
                &nbsp;&nbsp;&nbsp;<span id="alertpw2"></span>
            </div>
            <div class="st1">
                <label for="name1" class="t1">暱稱：</label>
                <input type="text" name="username" maxlength="30" id="name1" autocomplete="off"
                onblur="blurName(this)">
                &nbsp;&nbsp;&nbsp;<span id="alertname"></span>
            </div>
            <div>
            <CENTER>
			<INPUT TYPE="SUBMIT" VALUE="送出"> <INPUT TYPE="reset"
				VALUE="清空" /> <BR> <BR> <INPUT TYPE="button"
				VALUE="登入"
				ONCLICK="location.href='/vegetarian/Login'"> <br> <br>
		</CENTER>
            </div>
        </fieldset>
</form>
<script src="js/CheckRegister/check.js"></script>
</body>
</html>