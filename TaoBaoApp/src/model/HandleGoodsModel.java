package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.rowset.CachedRowSetImpl;

import db.JdbcUtils;

public class HandleGoodsModel {
	public String queryGoods(PageShow goodShow, int key, String keyWord) throws ServletException, IOException
	{   
		String res = "抱歉, 出了一些错误";
        CachedRowSetImpl rowSet = null;   
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		switch (key){
			case 1:  //显示全部商品
				String sql1 = "select * from goods";
				try {
					conn = JdbcUtils.getConnection();
					pstmt = conn.prepareStatement(sql1);
					rs = pstmt.executeQuery();
					if(rs.next()){
					    rs = pstmt.executeQuery();
						rowSet = new CachedRowSetImpl();
						rowSet.populate(rs);
						goodShow.setRowSet(rowSet);
						res = "查询成功";
					}else {
						res = "亲 , 卖家还没上货呢";
                    }
				} catch (SQLException e){
					e.printStackTrace();
				}finally{
					JdbcUtils.free(rs, pstmt, conn);
				}
				break;
			case 2:   //查询商品
				String sql2 = "select * from goods where gname like ?";
				try {
					conn = JdbcUtils.getConnection();
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, "%"+keyWord+"%");
					rs = pstmt.executeQuery();
					if(rs.next()){
					    rs = pstmt.executeQuery();
						rowSet = new CachedRowSetImpl();
						rowSet.populate(rs);
						goodShow.setRowSet(rowSet);
						res = "查询成功";
					}else {
						res = "亲 , 卖家还没上货呢";
                    }
				} catch (SQLException e){
					e.printStackTrace();
				}finally{
					JdbcUtils.free(rs, pstmt, conn);
				}
				break;
			default:
				break;
		}
		return res;
	}
}
