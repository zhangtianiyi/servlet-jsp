package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;

import db.JdbcUtils;

public class RegisterModel {
	public String handle(String username, String userpass, String again_userpass, String address, String phone, String realname){
		String hint = null;
		String reg = "[\\d]{11}";
		if (!(again_userpass.equals(userpass))){
			hint = "两次密码不一致,注册失败";
			return hint;
		}
		else if(phone.matches(reg)){
			if (userpass.length()>5)
			{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try
				{
					conn = JdbcUtils.getConnection();
					String sql = "INSERT INTO customer(cname,cpass,cphone,caddress,crealname) VALUES(?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,username);
					pstmt.setString(2,userpass); 
					pstmt.setString(3,phone);
					pstmt.setString(4,address);
					pstmt.setString(5,realname);
					pstmt.executeUpdate();
					hint = "注册成功";
					return hint;
				} catch (SQLException e){
					hint = "用户名已被注册";
					return hint;
				}finally{
					JdbcUtils.free(rs, pstmt, conn);
				}
			}else {
				hint = "密码不合法";
				return hint;
			}
		}else //if (phone!=null && phone.length()>0 && !phone.matches(reg))  
		{
			hint = "11位手机号";
			return hint;
		}
	}
}
