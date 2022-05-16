<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation</title>
<style><%@include file="/../css/Reservation/Reservation.css"%></style>
</head>
<body> 
	<jsp:include page="/WEB-INF/jsp/parts/header.jsp" />
	 <div class="app">
    <h1>預訂系統</h1>
    
    <p>日期</p>
    <input type="date" id="date"/>
    <p>人數</p>
    <select id="memberCount" name="memberCount"
    tabindex="1" data-placeholder="選擇人數">
      <option value="" disabled>請選擇人數</option>
      <%for(int i=1 ; i <= 6 ; i++) {%>
      <option value="<%=i%>"><%= i%></option>
      <%}%>
  </select>
    <p>訂購備註</p>
    <input type="text" id="content"/>
    <button class="add" id="addedBtn">新增</button>
    <button class="remove" id="deletedBtn">刪除最後一個</button>
    <div class="list" id="list">
      <div class="item">
        <div>

        </div>
      </div>
    </div>
  </div> 
   <script type="text/javascript" charset="UTF-8" src="js/Reservation/Reservation.js"></script>
	<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />
</body>
</html>