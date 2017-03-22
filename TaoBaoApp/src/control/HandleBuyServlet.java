package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.rowset.CachedRowSetImpl;

import db.JdbcUtils;
import model.PageShow;
import model.UserSession;

public class HandleBuyServlet extends HttpServlet{
	public HandleBuyServlet() {
        super();
    }

	 public void init() throws ServletException{
	 }
	  
    public void destroy() {
        super.destroy(); 
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
       doPost(request, response);
    }
    

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        		
        HttpSession session = request.getSession();               
        UserSession loginBean = (UserSession)session.getAttribute("loginBean");   
        String userName = loginBean.getUsername();
        List<String> car = loginBean.getCar();
        
        if (car.size()!=0) {
            boolean falg = false;
            Connection   conn  = null;
            PreparedStatement psGoods = null;
            PreparedStatement psRecord = null;
            PreparedStatement psStore = null;
            ResultSet rsGoods = null;
            ResultSet rsRecord = null;
            ResultSet rsStore = null;
            
            //将商品行集数据遍历到数组中
            for (int i = 0,m=car.size(); i < m; i++) {
                String[] goods = car.get(i).split(",");         
                    
                String sqlGoods = "update goods set gbalance=?, gnum=? where gid=?";
                String sqlRecord = "insert into buyrecord(cname, gname, gprice, bnum) values(?,?,?,?)";
                String sqlStore = "update store set snum=snum+1 where sid=?";
    
                try {
                	conn = JdbcUtils.getConnection();
                     psGoods = conn.prepareStatement(sqlGoods);
                     psRecord = conn.prepareStatement(sqlRecord);
                     psStore = conn.prepareStatement(sqlStore);
                            
                     psRecord.setString(1,userName);
                     psRecord.setInt(4,1);

                     for (int j = 0,n=goods.length; j < n; j++) {
                          switch (j){
                              case 0:
                                  String gid = goods[0];  
                                  psGoods.setInt(3, Integer.parseInt(gid));
                                  break;
                              case 1:
                                  String gname = goods[1];                
                                  psRecord.setString(2, gname); 
                                  break;
                             case 2:
                                  break;
                             case 3:
                                  double gprice = Double.parseDouble(goods[3]);
                                  psRecord.setDouble(3, gprice); 
                                  break;
                             case 4:
                                  int gbalance = Integer.parseInt(goods[4])-1;
                                  if (gbalance >= 0){
                                	  psGoods.setInt(1, gbalance);
                                  }else {
                                      String failNumber = "数据库中商品不足";
                                      messShopping(request,response,failNumber);
                                  }
                                  break;
                             case 5:
                            	 int store = Integer.parseInt(goods[5]);
                            	 psStore.setInt(1, store);  
                            	 break;
                             case 6:
                            	 int gnum = Integer.parseInt(goods[6]) + 1;
                            	 psGoods.setInt(2, gnum);
                            	 break;
                             default:
                                  break;
                         }
                   }         
                psGoods.executeUpdate();
                psRecord.executeUpdate();
                psStore.executeUpdate();
                falg = true;
            } catch (SQLException e) {
                    PrintWriter out = response.getWriter();
                    out.print(e+"<br>");
                    out.print("返回"+"");      
                    out.print("<a href=/MyJavaWebApp/jsp/shoppingCar/lookShoppingCar.jsp>购物车</a>");
                    return;
            }finally{
                    JdbcUtils.free(rsGoods, psGoods, conn);
                    JdbcUtils.free(rsRecord, psRecord, conn);
                    JdbcUtils.free(rsStore, psStore, conn);
            }
        }
                    
                if (falg==true) {
                    car.clear();
                    updateInfo(request,response);
                    String successBackNews = "您已将购物车中的商品买回家了";
                    messShopping(request,response,successBackNews);
                }
            }
    }
 
     //从数据库中更新行集对象

    //为了显示，实体就是为了显示！
    private void updateInfo(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        CachedRowSetImpl rowSet = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PageShow goods = null;
        
        HttpSession session = request.getSession();
        goods = (PageShow)session.getAttribute("goods");          
        if (goods==null) {
            goods = new PageShow();
            session.setAttribute("goods", goods);
        }
        
        String sqlListClear= "select * from goods";
        try
        {
        	conn = JdbcUtils.getConnection();
            pstmt = conn.prepareStatement(sqlListClear);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                rowSet = new CachedRowSetImpl();
                rowSet.populate(rs);               
                goods.setRowSet(rowSet);             
            }
        } catch (SQLException e){
            PrintWriter out = response.getWriter();
            out.print(e+"<br>");
            out.print("返回"+"");
            out.print("<a href=/MyJavaWebApp/jsp/shoppingCar/lookShoppingCar.jsp>购物车</a>");
        }finally {
        	JdbcUtils.free(rs, pstmt, conn);
        }
    }

     // 商品购买处理信息
    public void messShopping(HttpServletRequest request, HttpServletResponse response, String mess) throws IOException
    {
    	PrintWriter out = response.getWriter();
        out.print("<br><br><br>");
        out.print("<center><font size=5 color=red><B>"+mess+"</B></font>&nbsp;已成功添加购物车");
        out.print("<br><br><br>");
        out.print("<a href='/TaoBaoApp/control/HandleGoodsServlet?key=1'>返回继续购物</a>");
        out.print("&nbsp;or&nbsp;");
        out.print("<a href=/TaoBaoApp/control/HandleCarServlet?key=1>查看购物车</a></center>");
    }
}
