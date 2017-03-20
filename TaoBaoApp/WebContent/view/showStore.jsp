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
  </head>

  <body>	 
    	<jsp:useBean id="storeShow" class="model.PageShow" scope="session"/>
  		<% request.setCharacterEncoding("UTF-8"); %>
  <br/><br/>
  <center>		
  	<table border="1" bordercolor="#00ff00" cellpadding="10" cellspacing="2" width="500" height="80">
  		  <caption><b>店铺</b><br></caption>
  		    <tr>
  		        <th>店铺名称</th>
  		        <th>店铺销量</th>
  		        <th>查看详情</th>
  		    </tr>
  		<% 
  			CachedRowSetImpl rowSet = storeShow.getRowSet();
  			if(rowSet==null) {
  				out.print("店铺暂时关闭，即将开启");
  				return;
  			}
  			rowSet.last();
  			int totalRecord = rowSet.getRow();;             
  			int PageSize = storeShow.getPageSize();            
  			int totalPages = storeShow.getTotalPage();         
  			int currentPage = storeShow.getCurrentPage();
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
  		    
  			storeShow.setCurrentPage(currentPage);
  			storeShow.setPageSize(PageSize);
  			storeShow.setTotalPage(totalPages);

  			//定位当前页的第一条记录
  		    int currentRow = ((storeShow.getCurrentPage()-1)*PageSize)+1;
  			rowSet.absolute(currentRow);
  			   
  			for(int i=1; i <= storeShow.getPageSize(); i++) {
  			     int id = rowSet.getInt(1);
  			     String name = rowSet.getString(2);
  			     int num = rowSet.getInt(3);
  			       
  			     String commodity = null;
  			     commodity = id+","+name+","+num;
  			     commodity = commodity.replaceAll("\\p{Blank}","");
  			     
  			     String detail = "<form action='/MyJavaWebApp/jsp/browse/showDetail.jsp' method='post'>"+
  			                       "<input type='hidden' name='detail' value="+commodity+">"+
  			                       "<input type='submit' value='店铺详情'></form>";
  			  %>
  			          <tr <% if(i%2 == 0){%> bgcolor="#FFE4B5" <%}else{%> bgcolor="#FFFACD" <%}; %>>
  			               <td><%= name %></td>
  			               <td><%= num %></td>
  			               <td><%= detail %></td>
  			           </tr>
  			       <%	
  			       if(!rowSet.next())
  			    	   break;
  			  }
  		%> 
  	</table>
  	
  	<br><%= storeShow.getCurrentPage() %>/<%= storeShow.getTotalPage() %> 页
  	
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