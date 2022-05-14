<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="parts/meta.jsp" %>  
<title>Home Page</title>
<%@ include file="parts/header.jsp" %>  
</head>
<body>
    <div class="view">
        <div class="pages">
            <jsp:include page="parts/page-body.jsp"></jsp:include>
        </div>
    </div>
    <jsp:include page="parts/footer.jsp" />
</body>
</html>