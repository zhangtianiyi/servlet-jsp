package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.rowset.CachedRowSetImpl;

import db.JdbcUtils;
import model.HandleGoodsModel;
import model.PageShow;
import model.UserSession;
//商品查询，（分类，鞋  ）。商品显示。（价格升序/销量降序）
public class HandleGoodsServlet extends HttpServlet {
	public HandleGoodsServlet(){
		super();
	}

	public void init() throws ServletException{
	}
	
	public void destroy(){
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
		response.setContentType("text/html;chartset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String keyvalue = request.getParameter("key");
		int key = Integer.parseInt(keyvalue);          
		String keyWord = request.getParameter("keyWord");
		
		HttpSession session = request.getSession();        
		PageShow goodShow = (PageShow)session.getAttribute("goodShow");

		if (goodShow == null){
			goodShow = new PageShow(null, 5, 1, 1);
			session.setAttribute("goodShow", goodShow);
		}
		HandleGoodsModel hg = new HandleGoodsModel();
		String s = hg.queryGoods(goodShow, key, keyWord);
		if(s.equals("查询成功")){
			request.getRequestDispatcher("/view/showGoods.jsp").forward(request, response);
		}else{
			session.setAttribute("exception", s);
			response.sendRedirect("/view/exception.jsp");
		}
	}
}
