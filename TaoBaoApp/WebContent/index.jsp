<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/TaoBaoApp/css/index.css">
		<base href="<%=basePath%>"> 
		<title>淘宝网 - 淘！我喜欢</title>
			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">    
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<meta http-equiv="description" content="This is my page">
	</head>
	
	<body>
		<jsp:useBean id="loginBean" class="model.UserSession" scope="session"/>
 	    <% request.setCharacterEncoding("UTF-8"); %>
 			<ul class="user">
    			<li>
    				<%
    				    String username = loginBean.getUsername();
    					if(username == null){
    					  	request.getSession().invalidate();
                        %>
	     					<a href="view/login.jsp">登录</a>
	     					<a href="view/register.jsp">注册</a>
    					<%
    					}else{
    				 %>
   						<dl>
   							<dt>
	    						<a>欢迎您,<b><font color="red"><%= username %></font></b></a>
	    						<a href="/TaoBaoApp/control/ExitServlet"><font color="#CDC9C9">退出</font></a>
   							</dt>
   						</dl>
   					<%
    					}
    				 %>
    			</li>
    		</ul>
    			<div align="center">
    			<span align="center">鞋 衣 裤 书 手机</span>
				    <div class="search-box">
				        <form class="search-form" name="formname" action="" target="_self" id="search-form" method="post">
				            <input type="text" class="search-text" name="keyWord" id="search_input" autocomplete="off"/>
				            <input type="hidden" name="key" value="2" />
				            <input type="submit" onclick="document.formname.action='/TaoBaoApp/control/HandleGoodsServlet?key=2'" class="search-button" value="搜索商品"/>
				            <input type="submit" onclick="document.formname.action='/TaoBaoApp/control/HandleStoreServlet?key=2'" class="search-button2" value="搜索店铺"/>
				        </form>
				    </div>
			    <br />
					<span align="center">潘的小店 刘的小店 周的小店</span>
			    </div>
	</body>
</html>