<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/Header.jsp" %>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="/TaoBaoApp/js/checkform.js"></script>
		<title>登录</title>
	</head>
	<body>
		<% 
		request.setCharacterEncoding("UTF-8");
		String username = "";
		String userpass = "";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie c : cookies){
				if("username".equals(c.getName())){
					username = URLDecoder.decode(c.getValue(),"UTF-8");
				}
				if("userpass".equals(c.getName())){
					userpass = URLDecoder.decode(c.getValue(),"UTF-8");
				}
			}
		}
		%>
		<div align="center">
			<form action="<%= path %>/control/LoginServlet" method="post" onSubmit="return check(this);">
				<table border="0" cellpadding="15" cellspacing="1">
					<tr>
						<td colspan="2">
							<input name="username" value="<%= username %>" placeholder="username"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="password" name="userpass" value="<%= userpass %>" placeholder="password"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="checkbox" name="isCookie" value="isCookie" checked="checked">记我十天
						</td>
					</tr>
					<tr>	
						<td align="center">
							<input type="submit" value="登陆" />
						</td>
						<td align="center">
							<input type="reset" value="取消" />
						</td>
					</tr>
				</table>
			</form>
			
			<% String hint = (String)request.getAttribute("hint");
					if(hint != null){
						%>
						</br>
						 <%
						out.print(hint); 
					}
				%>
		</div>
	</body>
</html>