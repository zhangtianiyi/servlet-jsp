package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserSession;

public class HandleCarServlet extends HttpServlet{
	public HandleCarServlet() {
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
        
        String key = request.getParameter("key");  
        int keyvalue = Integer.parseInt(key);
        
        switch(keyvalue){
        	case 1:  //查看购物车
        		response.sendRedirect("/TaoBaoApp/view/showCar.jsp");
        		break;
        	case 2:  //添加商品到购物车
        		String goods = request.getParameter("Car");
        		if (goods==null) {
                	response.sendRedirect("/TaoBaoApp/index.jsp");              
                }else 
                    {
                String[] details = goods.split(",");       
                        
                HttpSession session = request.getSession();         
                UserSession loginBean = (UserSession)session.getAttribute("loginBean");   
                if(loginBean == null){
                	request.setAttribute("hint", "添加购物车需要先登录哦"); 
                	request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                List <String> car = loginBean.getCar();
                car.add(goods);
                loginBean.setCar(car);       
                backNews(request, response, details[1]);
             }
        		break;
        	case 3: //删除购物车中的商品
        		int deleteID =  Integer.parseInt(request.getParameter("id"));
                
                HttpSession session = request.getSession();
                UserSession loginBean = (UserSession)session.getAttribute("loginBean");  
               
                List<String> car = loginBean.getCar();
                car.remove(deleteID);
                loginBean.setCar(car);
                request.getRequestDispatcher("/view/showCar.jsp").forward(request, response);
        	default:
        }
    }
    
     // 添加购物车成功后，返回提示操作信息
    private void backNews(HttpServletRequest request, HttpServletResponse response, String goodsName) throws IOException {        
        PrintWriter out = response.getWriter();
        out.print("<br><br><br>");
        out.print("<center><font size=5 color=red><B>"+goodsName+"</B></font>&nbsp;已成功添加购物车");
        out.print("<br><br><br>");
        out.print("<a href='/TaoBaoApp/control/HandleGoodsServlet?key=1'>返回继续购物</a>");
        out.print("&nbsp;or&nbsp;");
        out.print("<a href=/TaoBaoApp/control/HandleCarServlet?key=1>查看购物车</a></center>");
    }
}
