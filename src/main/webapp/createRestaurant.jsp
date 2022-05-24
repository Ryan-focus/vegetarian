<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="bean.Restaurant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
	<head>
	<meta charset="utf-8">
	<title>愛蔬網後台管理系統 - 新增餐廳資料</title>
	
	</head>
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
<body>
 <h1>後台新增餐廳</h1>
    <FORM ACTION="./RestaurantServletDS" method="get">
    <fieldset>
<!-- 	    <div> -->
<!-- 	        <input type="hidden" name="restaurantNumber" readonly="readonly" placeholder="11後" size="31">   -->
<!-- 	    </div> -->
	
	    <div>
	        <label> 餐廳名稱：</label><input type="text" name="restaurantName" autofocus placeholder="請輸入" size="31">
	    </div>
	
	    <div> 
	        <label> 餐廳電話：</label><input type="text" name="restaurantTel" autofocus placeholder="例:03-" autocomplete="off" size="31"> 
	    </div>
	
	    <div>
	        <label> 餐廳地址：</label><input type="text" name="restaurantAddress" autofocus placeholder="請輸入完整地址" size="31">   
	    </div>
	
	    <div>
	        <label> 評　　分：</label><input type="text" name="restaurantScore" autofocus placeholder="0-5(可小數)" autocomplete="off" size="31">
	    </div>
    <div>
        <label>餐廳類型：</label>
        <label>
            <input type="radio" name="restaurantCategory" value="中式" id="">中式
        </label>
        <label>
            <input type="radio" name="restaurantCategory" value="義式" id="">義式
        </label>
        <label>
            <input type="radio" name="restaurantCategory" value="自助餐" id="">自助餐
        </label>
        <label>
            <input type="radio" name="restaurantCategory" value="麵食" id="">麵食
        </label>
    </div>

    <div>
        <label>素食種類：</label>
        <label>
            <input type="radio" name="restaurantType" value="全素" id="">全素
        </label>
        <label>
            <input type="radio" name="restaurantType" value="奶素" id="">奶素
        </label>
        <label>
            <input type="radio" name="restaurantType" value="蛋素" id="">蛋素
        </label>
        <label>
            <input type="radio" name="restaurantType" value="五辛素" id="">五辛素
        </label>
    </div>

    <div> 
        <label> 營業時間：</label><br> 
        <textarea cols="40" rows="8" name="restaurantBusinessHours"></textarea>
    </div> 
    
	</fieldset>
    <br> 
    <div class="button">
	        <input name ="新增餐廳" type="submit" value="確認新增" onclick="location.reload()">
	        <input name ="reset" type="reset" value="清除輸入">
   	 </div>
    </FORM>
</body>
</html>