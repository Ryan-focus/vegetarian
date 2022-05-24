<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation</title>
<style><%@include file="/../css/Reservation/Reservation.css"%></style>
</head>
<body> 
	<div class="app">
    <h1><%= request.getAttribute("restauranName")%></h1>
	    <FORM ACTION="./reserve" method="post">
		    <p>日期</p>
		    <input type="date" id="date" required="required" name="orderdate"/>
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
   <script type="text/javascript" charset="UTF-8" src="js/Reservation/Reservation.js"></script>
</body>
</html>