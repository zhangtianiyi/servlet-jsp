package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HandleGoodsModel;
import model.HandleStoreModel;
import model.PageShow;
//店铺的数据库相关操作：显示店铺，查询店铺。 （销量升序，原来顺序）
public class HandleStoreServlet extends HttpServlet{
	public HandleStoreServlet(){
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
		PageShow storeShow = (PageShow)session.getAttribute("storeShow");

		if (storeShow == null){
			storeShow = new PageShow(null, 5, 1, 1);
			session.setAttribute("storeShow", storeShow);
		}
		HandleStoreModel hg = new HandleStoreModel();
		String s = hg.queryStore(storeShow, key, keyWord);
		if(s.equals("查询成功")){
			request.getRequestDispatcher("/view/showStore.jsp").forward(request, response);
		}else{
			session.setAttribute("exception", s);
			response.sendRedirect("/view/exception.jsp");
		}
	}
}
