<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>"> 
		<title>some exception</title>
	</head>
	
	<body>
		<br/><br/><br/><br/>
		<center>
			<%        
				String exception = "";
				exception = (String)session.getAttribute("exception");
			%>
			<font color=green> <%= exception %> </font>
			<a href="/TaoBaoApp/index.jsp">进入首  页</a>
		</center>
	</body>
</html>