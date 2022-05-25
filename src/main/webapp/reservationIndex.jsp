<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="restaurant" class="bean.Restaurant" scope="request"/>
<jsp:setProperty name="restaurant" property="*"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation</title>
<style><%@include file="/../css/Reservation/Reservation.css"%></style>
</head>
<body> 
	<div class="app">
    <h1><%=request.getAttribute("restaurantName")%></h1>

      
      <div style="
    width: 95%;
    display: block;
    text-align: left;
"><img style="width:85%;" src="images/reserveImage/<%=request.getAttribute("restaurantNumber")%>.jpg" alt="" />

</div>
      <div class = "menu">
      <ul>
        <li>餐廳名稱 :<%=request.getAttribute("restaurantName")%></li>
        <li>餐廳地址 :<%=request.getAttribute("restaurantAddress")%></li>
        <li>餐廳電話 :<%=request.getAttribute("restaurantTel")%></li>
        <li>美食分類 :<%=request.getAttribute("restaurantCategory")%></li>
        <li><span>素食種類 : <%=request.getAttribute("restaurantType")%></span></li>
        <li>營業時間 :<%=request.getAttribute("restaurantBusinessHours")%></li>
      </ul>
      </div>
      <div class="map"><%=request.getAttribute("restaurantMap")%></div>
	    <FORM ACTION="./reserve" method="post">
		    <p>日期</p>
		    <input type="date" id="theDate" min='2022-05-25' max='2050-05-25' required="required" name="orderdate"/>
		    <p>人數</p>
		    <select id="memberCount" name="memberCount"
		    	tabindex="1" data-placeholder="選擇人數" required>
			    <option value="" disabled>請選擇人數</option>
			      <%for(int i=1 ; i <= 6 ; i++) {%>
			    <option value="<%=i%>"><%= i%></option>
		      	<%}%>
		  	</select>
		  	<input type="hidden" name="restaurantNumber" value="<%=request.getAttribute("restaurantNumber")%>">
			<input type="hidden" name="userID" value="<%=user.getUid()%>">
		    <button class="remove" name ="orderRS" type="submit" value="submit">送出</button>
	    </FORM>
    </div>

   <script src="js/memberjs/jquery-1.8.2.js" type="text/javascript"></script> 
   <script>
   $(document).ready(function() {
	    var date = new Date();

	    var day = date.getDate();
	    var month = date.getMonth() + 1;
	    var year = date.getFullYear();
		var maxMonth = month+1;
	    
	    if (month < 10) month = "0" + month;
	    if(maxMonth < 10 ) maxMonth = "0" + maxMonth;
	    if (day < 10) day = "0" + day;

		
	    var today = year + "-" + month + "-" + day; 
	    var maxday = year + "-" + maxMonth + "-" + day;
	    
	    $("#theDate").attr("min", today);
	    $("#theDate").attr("max", maxday);
	    $("#theDate").attr("value", today);
	});
   </script>
</body>
</html>