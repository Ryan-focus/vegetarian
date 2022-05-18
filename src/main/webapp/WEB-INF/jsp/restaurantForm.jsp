<%@ page import="java.util.List" %>
<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="bean.Restaurant" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>restaurantForm</title>
</head>
<style><%@include file="/../css/index/table.css"%></style>
<body>
<jsp:include page="/WEB-INF/jsp/parts/header.jsp" />
<jsp:include page="/WEB-INF/jsp/parts/map.jsp" />
<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />
    <FORM ACTION='./RestaurantServletDS' method="get">
    <table class="tb1">
    <caption>
        <h1>搜尋餐廳結果</h1>
    </caption>
    <thead>
        <tr>
            <th>餐廳編號</th>
            <th>餐廳名稱</th>
            <th>餐廳電話</th>
            <th>餐廳地址</th>
            <th>餐廳類型</th>
            <th>素食種類</th>
            <th>營業時間</th>
            <th>評分</th>
        </tr>
    </thead>

    <tbody>
    	<% List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("restaurantList"); %>
        <% for (Restaurant restaurant : restaurantList) {%>
        <tr>
            <td><%= restaurant.getRestaurantNumber() %></td>
            <td><%= restaurant.getRestaurantName() %></td>
            <td><%= restaurant.getRestaurantTel() %></td>
            <td><%= restaurant.getRestaurantAddress() %></td>
            <td><%= restaurant.getRestaurantCategory() %></td>
            <td><%= restaurant.getRestaurantType() %></td>
            <td><%= restaurant.getRestaurantBusinessHours() %></td>
            <td><%= restaurant.getRestaurantScore() %></td>
        <% } %>
        </tr>
    </tbody>
    </table>
    </FORM>
   
    <p><a href="index.jsp"><button>回到首頁</button></a></p>
<!--     <link rel="stylesheet" href="/../../map.jsp"> -->
</body>
</html>