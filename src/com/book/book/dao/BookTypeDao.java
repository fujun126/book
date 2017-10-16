package com.book.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.book.pojo.BookType;
import com.book.util.DB;

public class BookTypeDao {
	private	DB db = DB.getDB();
	private	Connection conn;
	private	PreparedStatement pstmt ;
	/**
	 * 新增
	 */
	public boolean saveBookType(BookType bt){
	    conn=db.getConn();
		String sql = "insert into tb_booktype(typename) values(?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bt.getTypename());
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, null);
		}
		return false;
	}
	/**
	 * 修改
	 */
	public boolean editBookType(BookType bt){
		conn=db.getConn();
		String sql = "update tb_booktype set typename=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bt.getTypename());
			pstmt.setInt(2, bt.getId());
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, null);
		}
		return false;
	}
	/**
	 * 删除
	 */
	public boolean delBookType(int id){
		conn=db.getConn();
		String sql = "delete from tb_booktype where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, null);
		}
		return false;
	}
	/**
	 * 查询全部数据
	 */
	public List<BookType> queryBookType(){
		conn=db.getConn();
		String sql = "select id,typename from tb_booktype";
		ResultSet rs = null;
		List<BookType> list = new ArrayList<BookType>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				BookType bt = new BookType();
				bt.setId(rs.getInt("id"));
				bt.setTypename(rs.getString("typename"));
				
				list.add(bt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return list;
	}
	/**
	 * 查询单条数据
	 */
	public BookType getBookType(int id){
		conn=db.getConn();
		String sql = "select id,typename from tb_booktype where id=?";
		ResultSet rs = null;
		BookType bt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				bt = new BookType();
				bt.setId(rs.getInt("id"));
				bt.setTypename(rs.getString("typename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return bt;
	}
}
