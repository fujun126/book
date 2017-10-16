package com.book.rankinglist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.book.book.pojo.BookType;
import com.book.book.pojo.Bookinfo;
import com.book.util.DB;

public class RankingListDao {
	private	DB db = DB.getDB();
	private	Connection conn;
	private	PreparedStatement pstmt ;
	
	

	/**
	 * 图书借阅排行全部数据
	 */
	public List<Bookinfo> bookList(){
		conn=db.getConn();
		String sql = "select b.bookname ,count(b.id) page  from tb_borrow_back a,tb_bookinfo b  where a.bookid=b.id  GROUP BY b.id";
		ResultSet rs = null;
		List<Bookinfo> list = new ArrayList<Bookinfo>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				Bookinfo book = new Bookinfo();
				book.setBookname(rs.getString("bookname"));
				book.setPage(rs.getInt("page"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 读者借阅排行数据
	 */
	public List<Bookinfo> readerList(){
		conn=db.getConn();
		String sql = "select b.name bookname ,count(b.id) page  from tb_borrow_back a,tb_reader b  where a.readerid=b.id  GROUP BY b.id";
		ResultSet rs = null;
		List<Bookinfo> list = new ArrayList<Bookinfo>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				Bookinfo book = new Bookinfo();
				book.setBookname(rs.getString("bookname"));
				book.setPage(rs.getInt("page"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return list;
	}
	
	
}
