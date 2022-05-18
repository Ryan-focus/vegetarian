<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style><%@include file="/../css/index/header.css"%></style>
  </head>
  <body>
    <header>
      <div class="header">
      <a href="index.jsp"> 
        <img data-src="images/index/logo.png" src="images/index/logo.png" alt="logo" title="vegelife" data-was-processed="true"/>
	  </a>
        <ul class="navigation">

          <a href="/vegetarin/shoppingcartIndex"><li>商品頁</li></a>
       	  <a href="cart.jsp"><li>購物車</li></a>
       	    <%
        if(auth !=null){%>
          <a href="orders.jsp"><li>歷史訂單</li></a>
          <a href="#"><li>登出</li></a>
            <%}else{%>
          <a href="/vegetarian/Login"><li>登入</li></a>
             <%}
        %>
        </ul>
      </div>
    </header>
  </body>
</html>
