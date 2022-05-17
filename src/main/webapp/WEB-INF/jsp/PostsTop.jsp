<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>食記分享</title>
<style>
    body{
         margin-left: 200PX;
        margin-right: 200PX;
    }
    .box {
	width: 800px;
	padding: 10px;
	
	}
	.ellipsis {
	overflow:hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 3;
	-webkit-box-orient: vertical;
	white-space: normal;
	}
</style>
</head>
<body style="background-color: antiquewhite", width: 800px;>
<jsp:include page="/WEB-INF/jsp/parts/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/parts/footer.jsp"/>
	
	<h2 style="text-align:center ;">文章列表</h2>
	<input type="button" onclick="javascript:window.location.href='CreatePost.jsp';" value="發表文章" />
	<hr>
	
		
		<%
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url3="jdbc:sqlserver://localhost:1433;databaseName=Task";
			Connection conn3 = DriverManager.getConnection(url3, "sa","passw0rd");
			int intPageSize;//一頁顯示的記錄數
			int intRowCount;//記錄總數
			int intPageCount;//總頁數
			int intPage;//待顯示頁碼
			String strPage;
			int i;
			intPageSize=100;//設定一頁顯示的記錄數
			strPage=request.getParameter("page");//取得待顯示的頁碼
			if(strPage==null){
				//表明page的引數值為空，此時顯示第一頁資料
				intPage=1;
			}else{
				//將字串轉換成整型
				intPage=java.lang.Integer.parseInt(strPage);
				if(intPage<1)
					intPage=1;
			}
			
			Statement stmt=conn3.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY
					);
			String sql="SELECT * FROM posts order by post_id desc";
			ResultSet rs=stmt.executeQuery(sql);
			rs.last();//游標指向查詢結果集中的最後一條記錄
			intRowCount=rs.getRow();//獲取記錄總數
			intPageCount=(intRowCount+intPageSize-1)/intPageSize;//計算總頁數
			
			if(intPage>intPageCount)
				intPage=intPageCount;//調整待顯示的頁碼
			if(intPageCount>0){
				rs.absolute((intPage-1)*intPageSize+1);//將記錄指標定位到待顯示的第一條記錄上
				
				//顯示資料
				i=0;
				while(i<intPageSize&& !rs.isAfterLast()){
					
		%>
		<div>
		<h3><%=rs.getString("title") %></h3>
        <p><%=rs.getString("posted_date") %></p>
        <div class ="box">
        <p class="ellipsis"><%=rs.getString("posted_text") %></p> 
        </div>
        
        <a href="PostServlet?action=showPost&id=<%=rs.getInt("post_id") %>"> 繼續閱讀</a>
        <hr>
        <a href='PostServlet?action=deletePost&id=<%=rs.getInt("post_id") %>'>刪除文章</a>
        <a href='PostServlet?action=editPost&id=<%=rs.getInt("post_id") %>'>編輯文章</a>
        <hr>
        
        <br/>
		</div>
		
			<%
			rs.next();
			i++;
				}
			}
		%>
	<% 
		rs.close();
		stmt.close();
		conn3.close();
		%>
	
</body>
</html>