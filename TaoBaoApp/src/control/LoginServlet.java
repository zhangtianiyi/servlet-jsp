package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.JdbcUtils;
import model.LoginModel;
import model.UserSession;
//处理登录请求
public class LoginServlet extends HttpServlet{
	public LoginServlet()
	{
		super();
	}
	public void init() throws ServletException
	{
	}
	public void destroy()
	{
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
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		String isCookie  = request.getParameter("isCookie");
		LoginModel loginmodel = new LoginModel();
		Cookie[] cookies = null;
		
		if ("isCookie".equals(isCookie)){     //用户选择记住密码，刷新cookie
			cookies = loginmodel.setCookies(username, userpass);
		} else {
			cookies = loginmodel.setCookies("", "");
		}
		response.addCookie(cookies[0]); 
		response.addCookie(cookies[1]); 
		//检查用户名和密码，跳转
		String hint = loginmodel.handle(username, userpass, isCookie);
		if(hint.equals("登录成功")){
			UserSession us = null;
			HttpSession session = request.getSession(); 
			loginmodel.success(us, session, username);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else {
			request.setAttribute("hint", hint); 
			request.getRequestDispatcher("/view/login.jsp").forward(request, response);
		}
	}
}
