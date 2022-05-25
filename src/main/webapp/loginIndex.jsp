<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Login</title>
<style>
		@import url('https://fonts.googleapis.com/css2?family=Quicksand&family=Source+Sans+Pro:wght@200;300&display=swap');
        
     .b1 {
        	font-size: 18px;
        	font-family:微軟正黑體;
        	color: white;
        	width:400px;
        	height:40px;
        	border: 0px;
        	border-radius: 25px;
        	background-color: #2828FF;
        }  
        

html {
  height: 100%;
}
body {
  margin: 0;
  padding: 0;
  font-family: sans-serif;
  font-family: 'Source Sans Pro', sans-serif;
  overflow: auto;

}
.blur{
  width: 100vw;
  height: 100vh;
  background: linear-gradient(to right, #ff00001c, #5900ff1f), url(images/LoginRegister/veganfood.jpg);
  background-size: cover;
  background-position: center;
  transition: .8s;


}
.blur-new{
  filter: blur(7px);
  transition: .8s;
}

.main-box {
  position: absolute;
  top: 65%;
  left: 50%;
  width: 500px;
  padding: 40px;
  transform: translate(-50%, -50%);
  background: rgba(0, 0, 0, 0.7);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
  border-radius: 10px;
}

.main-box h2 {
  margin: 0 0 30px;
  padding: 0;
  color: #fff;
  text-align: center;
  font-family: 'Quicksand', sans-serif;

}

.main-box .input-box {
  position: relative;
}

.main-box .input-box input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}
.main-box .input-box label {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  pointer-events: none;
  transition: 0.5s;
}

.main-box .input-box input:focus ~ label,
.main-box .input-box input:valid ~ label {
  top: -20px;
  left: 0;
  color: #77f403;
  font-size: 12px;
}

.main-box form a {
  position: relative;
  display: inline-block;
  padding: 2px;
  color: #47f403;
  font-size: 16px;
  text-decoration: none;
  text-transform: uppercase;
  overflow: hidden;
  transition: 0.5s;
  margin-top: 30px;
  letter-spacing: 4px;
}

.main-box a:hover {
  background: #17f403;
  color: #fff;
  border-radius: 25px;
  box-shadow: 0 0 1px #03f42b;
}

.main-box a span {
  position: absolute;
  display: block;
}
        
    </style>
</head>
<body BGCOLOR="#fcfcfc">
    <div class="blur"></div>
    <div class="main-box">
      <h2>Login</h2>
      <form ACTION="./UserServlet" method="POST">
        <div class="input-box">
         <input type=text name="email" maxlength="50" id="email1" autocomplete="off" required>
          <label>E-mail</label>
            </div>
        <div class="input-box">
          <input type="password" name="password" maxlength="20" id="pwd1" autocomplete="off" required>
          <label>密碼</label>
            </div>
        <a href="#" class="submit">
          <INPUT TYPE="SUBMIT" VALUE="送出" class="b1"> 
        </a>
        <br>
        <a href="#" class="submit">
          <INPUT TYPE="button" class="b1"
				VALUE="註冊"
				ONCLICK="location.href='/vegetarian/UserRegister'">
        </a>
      </form>
    </div>
    <script src="js/LoginRegister/main.js" defer></script>
</body>
</html>