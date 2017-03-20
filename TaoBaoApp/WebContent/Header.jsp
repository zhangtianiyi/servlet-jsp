<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/TaoBaoApp/css/header.css">
		<title>导航条</title>
	</head>
	<body>
		<div>
	  		<ul class="headerlist">
	   			<li><a class="on" href="/TaoBaoApp/index.jsp">首  页</a></li>
	    		<li><a href="/TaoBaoApp/control/HandleGoodsServlet?key=1">显示商品</a></li>
	    		<li><a href="/TaoBaoApp/control/HandleStoreServlet?key=1">显示店铺</a></li>
	    	</ul>
	   	</div>
	</body>
</html>