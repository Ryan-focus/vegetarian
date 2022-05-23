<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="utf-8">

<title>愛蔬網後台管理系統-新增餐廳</title>

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
<!-- 開啟DataSource -->
<sql:setDataSource var="myDS" 
driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
url="jdbc:sqlserver://localhost:1433;databaseName=veganDB"
user="sa" 
password="manager" />
	<section id="container">
		<!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
 <h1>後台新增餐廳</h1>
    <fieldset>
    <FORM ACTION="./RestaurantServletDS" method="get">
    <div>
        <label> 餐廳編號：</label><input type="text" name="restaurantNumber" autofocus placeholder="11後" size="31">  
    </div>

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
        <textarea cols="40" rows="8"></textarea>
    </div> 
    
</fieldset>
    <br> 

    <div class="button">
    <% System.out.println("1"); %>
        <input name ="新增餐廳" type="submit" value="確認新增" onsubmit="javascript:location.href='index.jsp'">
    <% System.out.println("2"); %>
        <input name ="reset" type="reset" value="清除輸入">

    </div>
    </FORM>
</body>
</html>