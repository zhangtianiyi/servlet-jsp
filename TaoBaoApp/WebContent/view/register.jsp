<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/Header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<script type="text/javascript" src="/TaoBaoApp/js/checkform.js"></script>
	<title>register</title>
</head>
<body>
		<% request.setCharacterEncoding("UTF-8"); %>
		<div align="center">
			<form name="registerform" action="<%= path %>/control/RegisterServlet" method="post" onSubmit="return check(this);">
				<table border="1" cellpadding="10" cellspacing="1">
					<tr>
						<td>用户姓名: <input name="username"/></td>
						<td>用户密码：<input type="password" name="userpass" placeholder="6-16字符之间"/></td>
					</tr>
					<tr>
						<td>重复密码：<input type="password" name="again_userpass"/></td>
						<td>联系电话：<input type="text" name="phone" placeholder="11位手机号"/></td>
					</tr>
					<tr>
						<td>邮寄地址：<input type="text" name="address"/></td>
						<td>真实姓名：<input type="text" name="realname"/></td>
					</tr>
				</table>
				<input type="submit" value="提交"/>
				<% String hint = (String)request.getAttribute("hint");
					if(hint != null){
						%>
						</br>
						 <%
						out.print(hint); 
					}
				%>
			</form>
		</div>
</body>
</html>