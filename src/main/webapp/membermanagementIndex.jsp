<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member Management</title>

<link href="membercss/metro/crimson/jtable.css" rel="stylesheet" type="text/css" />
<link href="membercss/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />

<script src="memberjs/jquery-1.8.2.js" type="text/javascript"></script>
<script src="memberjs/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="memberjs/jquery.jtable.js" type="text/javascript"></script>
<script type="text/javascript">

    $(document).ready(function () {
    	
        $('#UserTable').jtable({
        	
            title: 'Table of user',
            paging: true,
            pageSize: 10,
           
            actions: {
            	
                listAction: 'MemberManagementServlet?action=list',
                createAction:'MemberManagementServlet?action=create',
                updateAction: 'MemberManagementServlet?action=update',
                deleteAction: 'MemberManagementServlet?action=delete'
                		
            },
            fields: {
            	
                uid: {
                	
                	title:'UID',
                    key: true,
                    list: true,
                    create:true
                },
                
                email: {
                	
                    title: 'Email',
                    width: '20%',
                    edit:true
                },
                
                password: {
                    title: 'Password',
                    width: '20%',
                    edit:true
                },
                
                username: {
                	
                    title: 'Username',
                    width: '20%',
                    edit: true
                },    
                
                status: {
                	
                    title: 'Status',
                    width: '20%',
                    edit: true
                }                
            }
        });
        $('#UserTable').jtable('load');
    });
    
</script>
</head>
<body>
<div style="width:60%;margin-right:20%;margin-left:20%;text-align:center;">
<h1>愛蔬網會員資料</h1>
<div id="UserTable"></div>
</div>
</body>
</html>