<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>show car</title>
</head>
<body>
      <jsp:useBean id="loginBean" class="model.UserSession" scope="session"/>
      <% request.setCharacterEncoding("UTF-8"); %>
      
     <center>
     	<br><br>
        <%   
              List<String> items = loginBean.getCar(); 
              if(items.size()==0)
              {%>
                    <font color=green> 购物车空空的，抓紧去 </font> 
                    <a href="/TaoBaoApp/control/HandleGoodsServlet?key=1"><font color=red size=6>购物</font></a> 
              <%
                return;
              }
        %>
       <table border="1" bordercolor="#4EEE94" cellpadding="10" cellspacing="0" width="400" height="80">
         <caption>我的购物车</caption>
           <tr bgcolor=#458B74>
              <th>序号</th>
              <th>商品编号</th>
              <th>名称</th>
              <th>产地</th>
              <th>价格</th>
              <th>店铺编号</th>
              <th>操作</th>
          </tr>
          
          <%
        	  double totalPrice = 0.00;
              for(int j=0,m=items.size();j<m;++j)
              {
            %><tr bgcolor=#43CD80><% 
                   String[] item = items.get(j).split(",");
                   %><td><%= j+1 %></td><% 
                   
	                for(int i=0;i<4;++i)
	                {%>
	                       <td><%= item[i]%></td>
	                <%}
                 totalPrice += Double.parseDouble(item[3]); 
            %>
            <td><%= item[5] %></td> 
            <td><a href='/TaoBaoApp/control/HandleCarServlet?id=<%= j %>&key=3' >删除</a></td></tr><%
              }
              String backPage = "<a href='/TaoBaoApp/control/HandleGoodsServlet?key=1'><font color=green><b>继续购物</b></font></a>";
           %>
       </table>
      
       <table cellpadding="10" cellspacing="0" width="400" height="80">
           <tr>
              <td colspan=3>合计：<%= totalPrice %> RMB</td>
              <td>
                    <form action="/TaoBaoApp/control/HandleBuyServlet" method="post">
                        <input type="submit" value="确认付款"/>
                    </form>
              </td>
              <td><%= backPage %></td>
           </tr>
       </table>
      </center>
 
</body>
</html>