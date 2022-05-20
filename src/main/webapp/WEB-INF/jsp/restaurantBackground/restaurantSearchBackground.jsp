<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RestaurantBackgroundSearch</title>
</head>
<style>
    h1{
        text-align: center;
    }
    body{
        background-color: lightblue;
    }
    div{
    	text-align: center;
    }
    fieldset{
    width:30%;
    border: 2px solid black;
    border-radius: 10px;
    margin: 20px;
    text-align: center;
    }
</style>
<body>
    <h1>後台餐廳查詢表</h1>
    <fieldset>
    <FORM ACTION="./RestaurantServletDS" method="get">
    <div>
        <label> 餐廳編號：</label><input type="text" name="restaurantNumber" autofocus placeholder="" autocomplete="off" size="15"
            id="account1">
    </div>

    <div>
        <label> 餐廳名稱：</label><input type="text" name="restaurantName" autofocus placeholder="" autocomplete="off" size="15"
            id="account1">
    </div>

<!--     <div> -->
<!--         <label> 餐廳電話：</label><input type="text" name="restaurantTel" autofocus placeholder="例:03-" autocomplete="off" size="15" -->
<!--             id="account1"> -->
<!--     </div> -->
<!--     <div> -->
<!--         <label> 餐廳地址：</label><input type="text" name="restaurantAddress" autofocus placeholder="請輸入完整地址" autocomplete="off" size="30" -->
<!--             id="account1"> -->
<!--     </div> -->

    <div>
        <label>餐廳類型：</label>
        <label>
            <input type="radio" name="restaurantCategory" value="中式">中式
        </label>
        <label>
            <input type="radio" name="restaurantCategory" value="義式">義式
        </label>
        <label>
            <input type="radio" name="restaurantCategory" value="自助餐">自助餐
        </label>
        <label>
            <input type="radio" name="restaurantCategory" value="麵食">麵食
        </label>
    </div>

    <div>
        <label>素食種類：</label>
        <label>
            <input type="radio" name="restaurantType" value="全素">全素
        </label>
        <label>
            <input type="radio" name="restaurantType" value="奶素">奶素
        </label>
        <label>
            <input type="radio" name="restaurantType" value="蛋素">蛋素
        </label>
        <label>
            <input type="radio" name="restaurantType" value="五辛素">五辛素
        </label>
    </div>
    </fieldset>

<!--     <div> -->
<!--         <label> 營業時間：</label><br> -->
<!--         <textarea cols="40" rows="5"></textarea> -->
<!--     </div> -->
<!--     <div> -->
<!--         <label> 評分：</label><input type="text" name="restaurantScore" autofocus placeholder="" autocomplete="off" size="15" -->
<!--             id="account1"> -->
<!--     </div> -->
<!--     <br> -->

    <div>
        <input name ="所有餐廳" type="submit" value="所有餐廳">
        <input name ="查詢餐廳" type="submit" value="查詢餐廳">
        <input name ="新增餐廳" type="submit" value="新增餐廳">
        <input name ="刪除餐廳" type="submit" value="刪除餐廳">
        <input name ="修改餐廳" type="submit" value="修改餐廳">
        <input name ="reset" type="reset" value="清除">
    </div>
    </FORM>
</body>
</html>