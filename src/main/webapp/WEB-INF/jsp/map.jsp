<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style><%@include file="/../css/index/body.css"%></style>
</head>
<body>
     <!-- Google Tag Manager (noscript)-->
    <noscript>
      <iframe
        src="https://www.googletagmanager.com/ns.html?id=GTM-PGQ9WQT"
        height="0"
        width="0"
        style="display: none; visibility: hidden"
      ></iframe>
    </noscript>

    <div id="app" class="container">
      <div class="row">
        <div class="col">
          <div id="map" class="embed-responsive embed-responsive-16by9"></div>
        </div>
      </div>

      <!-- <div class="row">
        <div class="col">
          <button
            type="button"
            class="btn btn-outline-secondary"
            v-for="f in features"
            @click="openInfoWindows(f.properties.id)"
          >
            {{ f.properties.name }}
          </button>
        </div> 
      </div> -->
    </div>
    <script
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9blRIb054WQz_K5FRcAojJhDG45QQDXc"
    async
    defer
  ></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.6.10/vue.min.js"></script>
   <script type="text/javascript" charset="UTF-8" src="js/map/map.js"></script>
  </body>
</html>