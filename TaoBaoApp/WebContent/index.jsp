<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/TaoBaoApp/css/index.css">
		<base href="<%=basePath%>"> 
		<title>淘宝网 - 淘！我喜欢</title>
			
	</head>
	<style type="text/css">
.good{
	position:absolute;
	top:130px;
	left: 200px;
}
.store{
	position:absolute;
	top:180px;
	left: 200px;
}
  </style>
	
	
	
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
	    						<br/><br/>
	     					<a href="/TaoBaoApp/control/HandleCarServlet?key=1">购物车</a>
	     					<a href="">待收货</a>
   							</dt>
   						</dl>
   					<%
    					}
    				 %>
    			</li>
    		</ul>
    		<center>
    			<font>
    				<% String hint = (String)request.getAttribute("hint");
    				if(hint != null){ %>
    					<%= hint%>
    				<%
    				}
    				%>
    			</font>
    		</center>
    			<div class = "bigdiv">
    			<font class="good" color=gray> 商品关键字如：鞋 衣 裤 书 手机 </font>
				    <div class="search-box">
				        <form class="search-form" name="formname" action="" target="_self" id="search-form" method="post">
				            <input type="text" class="search-text" name="keyWord" id="search_input" autocomplete="off"/>
				            <input type="hidden" name="key" value="2" />
				            <input type="submit" onclick="document.formname.action='/TaoBaoApp/control/HandleGoodsServlet?key=2'" class="search-button" value="搜索商品"/>
				            <input type="submit" onclick="document.formname.action='/TaoBaoApp/control/HandleStoreServlet?key=2'" class="search-button2" value="搜索店铺"/>
				        </form>
				    </div>
			    <br />
			    	<font class="store" color=gray> 店铺名如：潘的小店 刘的小店 周的小店 </font>
			    </div>
	</body>
</html>