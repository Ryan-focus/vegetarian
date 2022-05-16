<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
 <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
    <style><%@include file="/../css/index/body.css"%></style>
    <style><%@include file="/../css/index/search.css"%></style>
  <body>
  <%--  第一版 搜尋按鈕
    <div class="container">
      <form
        action="https://www.google.com/search"
        method="get"
        class="search-bar"
      >
        <input type="text" name="q" placeholder="Serch..." />
        <button type="submit"><img data-src="images/index/search.png" src="images/index/search.png" alt="search" title="search" data-was-processed="true"/></button>
      </form>
      </div>
      --%>
    <FORM ACTION="/map" method="get">
    
    <fieldset>
    <select id="city" name="restaurantAddress"></select>
    <select id="area" name="restaurantAddress"></select>
    
    <div>
        <label> 餐廳名稱：</label><input type="text" name="restaurantName" autofocus placeholder="" autocomplete="off" size="15"
            id="account1">
    </div>

    <div>
        <label>餐廳類型：</label>
      
        <label>
            <input type="checkbox" name="restaurantCategory" value="中式" id="">中式
        </label>
        <label>
            <input type="checkbox" name="restaurantCategory" value="義式" id="">義式
        </label>
        <label>
            <input type="checkbox" name="restaurantCategory" value="自助餐" id="">自助餐
        </label>
        <label>
            <input type="checkbox" name="restaurantCategory" value="麵食" id="">麵食
        </label>
    </div>

    <div>
        <label>素食種類：</label>
     
        <label>
            <input type="checkbox" name="restaurantType" value="全素"  >全素
        </label>
        <label>
            <input type="checkbox" name="restaurantType" value="奶素" >奶素
        </label>
        <label>
            <input type="checkbox" name="restaurantType" value="蛋素" >蛋素
        </label>
        <label>
            <input type="checkbox" name="restaurantType" value="五辛素" >五辛素
        </label>
    </div>
</fieldset>
    <br>

    <p class="p1">
      <input name ="查詢餐廳by" type="submit" value="查詢餐廳by">
    </p>
    </FORM>
	 <script type="text/javascript" charset="UTF-8" src="js/index/search.js"></script>
  </body>
</html>
