<%@ page pageEncoding="UTF-8"%>
<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="bean.Restaurant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="utf-8">
<title>愛蔬網後台管理系統-修改餐廳</title>
<style>
    h1{
        text-align: center;
    }
    body{
        background-color: lightblue;
    }
    div{
    	text-align: left;
        margin-left: 10px;
    }
    fieldset{
    width:30%;
    border: 2px solid black;
    border-radius: 10px;
    margin: 20px auto;
    }
    .button{
        text-align: center;
    }
   
</style>
</head>
<body>
 <h1>後台修改餐廳</h1>
    <FORM ACTION="./RestaurantServletDS" method="get">
    <% Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");%>
    <fieldset>
	    <div>
	        <label> 餐廳編號：</label><input type="text" name="restaurantNumber" autofocus placeholder="11後" size="31" value="<%=restaurant.getRestaurantNumber()%>">  
	    </div>
	
	    <div>
	        <label> 餐廳名稱：</label><input type="text" name="restaurantName" autofocus placeholder="請輸入" size="31" value="<%=restaurant.getRestaurantName()%>">
	    </div>
	
	    <div> 
	        <label> 餐廳電話：</label><input type="text" name="restaurantTel" autofocus placeholder="例:03-" autocomplete="off" size="31" value="<%=restaurant.getRestaurantTel()%>"> 
	    </div>
	
	    <div>
	        <label> 餐廳地址：</label><input type="text" name="restaurantAddress" autofocus placeholder="請輸入完整地址" size="31" value="<%=restaurant.getRestaurantAddress()%>">   
	    </div>
	
	    <div>
	        <label> 評　　分：</label><input type="text" name="restaurantScore" autofocus placeholder="0-5(可小數)" autocomplete="off" size="31" value="<%=restaurant.getRestaurantScore()%>">
	    </div>
    <div>
        <label>餐廳類型：</label>
        <label>
            <input type="radio" name="restaurantCategory" value="中式"  <c:if test="${restaurant.restaurantCategory=='中式'}">checked</c:if>>中式
        </label>
        <label>
            <input type="radio" name="restaurantCategory" value="義式" <c:if test="${restaurant.restaurantCategory=='義式'}">checked</c:if>>義式
        </label>
        <label>
            <input type="radio" name="restaurantCategory" value="自助餐" <c:if test="${restaurant.restaurantCategory=='自助餐'}">checked</c:if>>自助餐
        </label>
        <label>
            <input type="radio" name="restaurantCategory" value="麵食" <c:if test="${restaurant.restaurantCategory=='麵食'}">checked</c:if>>麵食
        </label>
    </div>

    <div>
        <label>素食種類：</label>
        <label>
            <input type="radio" name="restaurantType" value="全素" <c:if test="${restaurant.restaurantType=='全素'}">checked</c:if>>全素
        </label>
        <label>
            <input type="radio" name="restaurantType" value="奶素" <c:if test="${restaurant.restaurantType=='奶素'}">checked</c:if>>奶素
        </label>
        <label>
            <input type="radio" name="restaurantType" value="蛋素" <c:if test="${restaurant.restaurantType=='蛋素'}">checked</c:if>>蛋素
        </label>
        <label>
            <input type="radio" name="restaurantType" value="五辛" <c:if test="${restaurant.restaurantType=='五辛'}">checked</c:if>>五辛素
        </label>
    </div>

    <div> 
        <label> 營業時間：</label><br> 
        <textarea cols="40" rows="8" name="restaurantBusinessHours" ><%=restaurant.getRestaurantBusinessHours()%></textarea>
    </div> 
    
	</fieldset>
    <br> 
    <div class="button">
	        <input name ="修改餐廳" type="submit" value="確認修改" onclick="location.href='./backend.jspf'">
	        <input name ="reset" type="reset" value="回復">
	        <input type ="button" onclick="history.back()" value="回到上一頁"></input>
   	 </div>
    </FORM>
</body>
</html>