<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/Header.jsp" %>
<%@page import="com.sun.rowset.CachedRowSetImpl"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    <title>浏览商品</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="/TaoBaoApp/js/checkform.js"></script>
  </head>

  <body>	 
    	<jsp:useBean id="goodShow" class="model.PageShow" scope="session"/>
  		<% request.setCharacterEncoding("UTF-8"); %>
  <br/><br/>
  <center>		
  	<table border="1" bordercolor="#00ff00" cellpadding="10" cellspacing="2" width="500" height="80">
  		  <caption><b>商品</b><br></caption>
  		    <tr>
  		        <th>商品名称</th>
  		        <th>商品价格</th>
  		        <th>商品库存</th>
  		        <th>商品销量</th>
  		        <th>店铺</th>
  		        <th>查看详情</th>
  		        <th>添加到购物车</th>
  		    </tr>
  		<% 
  			CachedRowSetImpl rowSet = goodShow.getRowSet();
  			if(rowSet==null) {
  				out.print("商品已被买光，卖家正在上货");
  				return;
  			}
  			rowSet.last();
  			int totalRecord = rowSet.getRow();;             
  			int PageSize = goodShow.getPageSize();            
  			int totalPages = goodShow.getTotalPage();         
  			int currentPage = goodShow.getCurrentPage();
  			//分析：记录即rowset是不变的。每页行数可能变，当前页面可能变，总页数可能因此而变。
  			//刷新每页行数
  			if(request.getParameter("newPageSize") != null) {
  			   PageSize = Integer.parseInt(request.getParameter("newPageSize"));
  			   currentPage = 1;//从第一页开始显示
  			}
  			
  			//分页, 刷新总页数（根据每页行数）
  			if(totalRecord % PageSize == 0) {
  			   totalPages = totalRecord/PageSize;
  			}else{
  			   totalPages = totalRecord/PageSize+1;
  			}
  			
  		    //翻页, 刷新当前页面
  			if(request.getParameter("newCurrentPage") != null) {
  			    int newCurrentPage = Integer.parseInt(request.getParameter("newCurrentPage"));
  			    currentPage = newCurrentPage;
  			    if(currentPage < 1){
  			    	currentPage = totalPages;
  			    }else if(currentPage > totalPages){
  			    	currentPage = 1;
  			    }
  			}
  		    
  			goodShow.setCurrentPage(currentPage);
  			goodShow.setPageSize(PageSize);
  			goodShow.setTotalPage(totalPages);

  			//定位当前页的第一条记录
  		    int currentRow = ((goodShow.getCurrentPage()-1)*PageSize)+1;
  			rowSet.absolute(currentRow);
  			   
  			for(int i=1; i <= goodShow.getPageSize(); i++) {
  			     int id = rowSet.getInt(1);
  			     String name = rowSet.getString(2);
  			     String made = rowSet.getString(3);
  			     String price = rowSet.getString(4);
  			     int balance = rowSet.getInt(5);
  			     int store = rowSet.getInt(6);
  			     int num = rowSet.getInt(7);
  			       
  			     String commodity = null;
  			     commodity = id+","+name+","+made+","+price+","+balance+","+store+","+num;
  			     commodity = commodity.replaceAll("\\p{Blank}","");
  			       
  			     String shopCarButton = "<form action='/TaoBaoApp/control/HandleCarServlet?key=2' name='carform' method='post' onSubmit='return checkGood(this);'>"+
  			                       "<input type='hidden' name='Car' value="+commodity+">"+
  			                       "<input type='submit' value='加入购物车'></form>";
  			     String detail = "<form action='/TaoBaoApp/control/HandleGoodsServlet?key=2' method='post'>"+
  			                       "<input type='hidden' name='detail' value="+commodity+">"+
  			                       "<input type='submit' value='商品详情'></form>";
  			  %>
  			           <tr <% if(i%2 == 0){%> bgcolor="#FFE4B5" <%}else{%> bgcolor="#FFFACD" <%}; %>>
  			               <td><%= name %></td>
  			               <td><%= price %></td>
  			               <td><%= balance %></td>
  			               <td><%= num %></td>
  			               <td><%= store %></td>
  			               <td><%= detail %></td>
  			               <td><%= shopCarButton %></td>
  			           </tr>
  			       <%	
  			     if(!rowSet.next())
			    	   break;
  			  }
  		%> 
  	</table>
  	
  	<br><%= goodShow.getCurrentPage() %>/<%= goodShow.getTotalPage() %> 页
  	
  	<table>
  	 <tr>
  	     <td>
  	         <form action="" method="post">
  	             <input type="hidden" name=newCurrentPage value="<%= (currentPage-1) %>">
  	             <input type="submit" value="上一页">
  	         </form>
  	     </td>
  	     <td>
  	         <form action="" method="post">
  	             <input type="hidden" name=newCurrentPage value="<%= (currentPage+1) %>">
  	             <input type="submit" value="下一页">
  	         </form>
  	     </td>
  	 </tr>
  	 
  	 <tr>
  	     <td><BR>
  	         <form action="" method="post">
  	            总计：<%= totalRecord %>条记录.每页显示<input type="text" name="newPageSize" value="<%= PageSize %>" size="2">条.
  	                 <input type="submit" value="确定">
  	         </form>
  	     </td>
  	 </tr>
  	 
  	</table>
  	
   </center>
  </body>
</html>