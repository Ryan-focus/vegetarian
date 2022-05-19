<%@ page import="java.util.List" %>
<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="bean.Restaurant" %>
<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>restaurantForm</title>
</head>
<style><%@include file="/../css/table.css"%></style>
<body>
<noscript>
      <iframe
        src="https://www.googletagmanager.com/ns.html?id=GTM-PGQ9WQT"
        height="0"
        width="0"
        style="display: none; visibility: hidden"
      ></iframe>
    </noscript>
        <h2>搜尋餐廳結果</h2>
   	<div class="wrapper">
    <FORM ACTION="./RestaurantServletDS" method="get">
    <table class="tb1">

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
   <%@include file="/WEB-INF/jsp/parts/map.jspf" %>
   
   </div>
