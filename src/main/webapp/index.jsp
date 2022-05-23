		<%@ page pageEncoding="UTF-8"%>
		<div class="bodyContainer">
	    <FORM ACTION="./RestaurantServletDS" method="get">
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
	            <input type="radio" name="restaurantCategory" value="中式" >中式
	        </label>
	        <label>
	            <input type="radio" name="restaurantCategory" value="義式" >義式
	        </label>
	        <label>
	            <input type="radio" name="restaurantCategory" value="自助餐">自助餐
	        </label>
	        <label>
	            <input type="radio" name="restaurantCategory" value="麵食" >麵食
	        </label>
	    </div>
	
	    <div>
	        <label>素食種類：</label>
	     
	        <label>
	            <input type="radio" name="restaurantType" value="全素"  >全素
	        </label>
	        <label>
	            <input type="radio" name="restaurantType" value="奶素" >奶素
	        </label>
	        <label>
	            <input type="radio" name="restaurantType" value="蛋素" >蛋素
	        </label>
	        <label>
	            <input type="radio" name="restaurantType" value="五辛" >五辛素
	        </label>
	    </div>
		</fieldset>
		
	    <br>
	    
	    <p class="p1">
	      <input name ="查詢餐廳GO" type="submit" value="查詢餐廳GO">
	    </p>
	    </FORM>
	</div>
	 <script type="text/javascript" charset="UTF-8" src="js/index/search.js"></script>

