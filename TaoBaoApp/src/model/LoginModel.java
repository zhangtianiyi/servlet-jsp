package model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.JdbcUtils;

public class LoginModel {
	public String handle(String username, String userpass, String isCookie){
		String hint = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = JdbcUtils.getConnection();
			String sql = "select * from customer where cname=? and cpass=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, userpass);
			rs = pstmt.executeQuery();
			if (rs.next()){
				//登录成功
				hint = "登录成功";
				return hint;
			}else {
				hint = "用户名或者密码错误";
				return hint;
			}
		} catch (SQLException e) {
			hint = "登录失败";
			return hint;
		}finally {
			JdbcUtils.free(rs, pstmt, conn);
		}
	}
	
	// 处理用户cookies信息
	public Cookie[] setCookies(String name,String pass) throws UnsupportedEncodingException
	{
		Cookie[] cookies = new Cookie[2];
		String username = URLEncoder.encode(name,"UTF-8");
		String userpass = URLEncoder.encode(pass,"UTF-8");			
		Cookie nameCookie = new Cookie("username",username );
		Cookie passCookie = new Cookie("userpass",userpass );
		nameCookie.setPath("/");
		passCookie.setPath("/");
		nameCookie.setMaxAge(864000);
		passCookie.setMaxAge(864000);
		cookies[0] = nameCookie; 
		cookies[1] = passCookie;
		return cookies;
	}
	
	public void success(UserSession us, HttpSession session, String username){              
		us = (UserSession) session.getAttribute("loginBean");
		if (us == null) {
			us = new UserSession();
			us.setUsername(username);
			session.setAttribute("loginBean", us);
			session.setMaxInactiveInterval(60 * 30);   //半小时没操作，重新登陆
		}
	}
}
