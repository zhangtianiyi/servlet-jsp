package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.JdbcUtils;
import model.RegisterModel;
import model.User;

//处理注册的请求
public class RegisterServlet extends HttpServlet{
	public RegisterServlet(){
		super();
	}

	public void init() throws ServletException{
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
		
		RequestDispatcher view = null;
		User userBean = new User();
		request.setAttribute("userBean", userBean);     
	
		String username = request.getParameter("username").trim();       
		String userpass = request.getParameter("userpass");
		String again_userpass = request.getParameter("again_userpass");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String realname = request.getParameter("realname");
		//调用model的业务逻辑，判断注册成功或失败原因并跳转
		String hint = new RegisterModel().handle(username, userpass, again_userpass, address, phone, realname);
		request.setAttribute("hint", hint);
		if(hint == "注册成功"){
			view = request.getRequestDispatcher("/view/login.jsp");
		} else {
			view = request.getRequestDispatcher("/view/register.jsp");
		}
		view.forward(request, response);
	}
}
