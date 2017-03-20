package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.sun.rowset.CachedRowSetImpl;

import db.JdbcUtils;

public class HandleStoreModel {
	public String queryStore(PageShow storeShow, int key, String keyWord) throws ServletException, IOException
	{   
		String res = "抱歉, 出了一些错误";
        CachedRowSetImpl rowSet = null;   
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		switch (key){
			case 1: //显示店铺
				String sqlList= "select * from store";
				try {
					conn = JdbcUtils.getConnection();
					pstmt = conn.prepareStatement(sqlList);
					rs = pstmt.executeQuery();
					if(rs.next()){
					    rs = pstmt.executeQuery();
						rowSet = new CachedRowSetImpl();
						rowSet.populate(rs);
						storeShow.setRowSet(rowSet);
						res = "查询成功";
					}else {
						res = "亲 , 店铺暂时关闭";
                    }
				} catch (SQLException e){
					e.printStackTrace();
				}finally{
					JdbcUtils.free(rs, pstmt, conn);
				}
				break;
			case 2:   //查询店铺
				String sql2 = "select * from store where sname like ?";
				try {
					conn = JdbcUtils.getConnection();
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, "%"+keyWord+"%");
					rs = pstmt.executeQuery();
					if(rs.next()){
					    rs = pstmt.executeQuery();
						rowSet = new CachedRowSetImpl();
						rowSet.populate(rs);
						storeShow.setRowSet(rowSet);
						res = "查询成功";
					}else {
						res = "亲 , 店铺暂时关闭";
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
