<%@ page pageEncoding="UTF-8"%>
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
 <h1>確認修改資料</h1>
    <FORM ACTION="./RestaurantServletDS" method="get">
    <fieldset>
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
            <input type="radio" name="restaurantType" value="五辛" id="">五辛素
        </label>
    </div>

    <div> 
        <label> 營業時間：</label><br> 
        <textarea cols="40" rows="8" name="restaurantBusinessHours"></textarea>
    </div> 
    
	</fieldset>
    <br> 
    <div class="button">
	        <input name ="修改餐廳" type="submit" value="確認修改" onclick="location.reload()">
	        <input name ="reset" type="reset" value="清除輸入">
	        <input type ="button" onclick="history.back()" value="回到上一頁"></input>
   	 </div>
    </FORM>
</body>
</html>