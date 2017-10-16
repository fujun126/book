package com.book.book.dao;

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

public class BookinfoDao {
	private	DB db = DB.getDB();
	private	Connection conn;
	private	PreparedStatement pstmt ;
	
	
	/**
	 * 新增
	 */
	public boolean saveBookinfo(Bookinfo book){
		
		String sql = "insert into tb_bookinfo(bookname,typeid,author,ISBN,price,page,inTime," +
				"operator) values(?,?,?,?,?,?,now(),?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookname());
			pstmt.setInt(2, book.getBt().getId());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getISBN());
			pstmt.setDouble(5, book.getPrice());
			pstmt.setInt(6, book.getPage());
			pstmt.setInt(7, book.getOperator());
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
	public boolean editBookinfo(Bookinfo book){
		conn=db.getConn();
		String sql = "update tb_bookinfo set bookname=?,typeid=?,author=?,ISBN=?,price=?,page=?," +
				"inTime=now(),operator=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookname());
			pstmt.setInt(2, book.getBt().getId());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getISBN());
			pstmt.setDouble(5, book.getPrice());
			pstmt.setInt(6, book.getPage());
			pstmt.setInt(7, book.getOperator());
			pstmt.setInt(8, book.getId());
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
	public boolean delBookinfo(int id){
		conn=db.getConn();
		String sql = "delete from tb_bookinfo where id=?";
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
	public List<Bookinfo> queryBookinfo(){
		conn=db.getConn();
		String sql = "select a.id,bookname,typeid,b.typename,author,ISBN,price,page,inTime," +
				"operator from tb_bookinfo a,tb_booktype b where a.typeid=b.id and del=0";
		ResultSet rs = null;
		List<Bookinfo> list = new ArrayList<Bookinfo>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				Bookinfo book = new Bookinfo();
				book.setId(rs.getInt("id"));
				book.setBookname(rs.getString("bookname"));
				BookType bt = new BookType();
				bt.setId(rs.getInt("typeid"));
				bt.setTypename(rs.getString("typename"));
				book.setBt(bt);
				book.setAuthor(rs.getString("author"));
				book.setISBN(rs.getString("ISBN"));
				book.setPrice(rs.getDouble("price"));
				book.setPage(rs.getInt("page"));
				book.setInTime(rs.getString("inTime"));
				book.setOperator(rs.getInt("operator"));
				
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
	 * 查询单条数据
	 */
	public Bookinfo getBookinfo(int id){
		conn=db.getConn();
		String sql = "select a.id,bookname,typeid,b.typename,author,ISBN,price,page,inTime," +
				"operator from tb_bookinfo a,tb_booktype b " +
				"where a.typeid=b.id and del=0 and a.id=?";
		ResultSet rs = null;
		Bookinfo book = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				book = new Bookinfo();
				book.setId(rs.getInt("id"));
				book.setBookname(rs.getString("bookname"));
				BookType bt = new BookType();
				bt.setId(rs.getInt("typeid"));
				bt.setTypename(rs.getString("typename"));
				book.setBt(bt);
				book.setAuthor(rs.getString("author"));
				book.setISBN(rs.getString("ISBN"));
				book.setPrice(rs.getDouble("price"));
				book.setPage(rs.getInt("page"));
				book.setInTime(rs.getString("inTime"));
				book.setOperator(rs.getInt("operator"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return book;
	}
	
	/**
	 * 带条件查询数据
	 */
	public List<Bookinfo> queryBookinfo(Bookinfo bookinfo){
	
		 conn=db.getConn();
		String sql = "select a.id,bookname,typeid,b.typename,author,ISBN,price,page,inTime," +
				"operator from tb_bookinfo a,tb_booktype b where a.typeid=b.id and del=0";
		
		System.out.println(bookinfo.getBookname());
		
		/////////动态条件/////////
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		if(bookinfo!=null && !bookinfo.getBookname().equals("")){
			sql += " and bookname like concat('%',?,'%')";
			map.put(map.size()+1, bookinfo.getBookname());
		}
		if(bookinfo!=null && bookinfo.getBt()!=null){
			sql += " and typeid = ?";
			map.put(map.size()+1, bookinfo.getBt().getId());
		}
		if(bookinfo!=null && !bookinfo.getAuthor().equals("")){
			sql += " and author like concat('%',?,'%')";
			map.put(map.size()+1, bookinfo.getAuthor());
		}
		if(bookinfo!=null && !bookinfo.getISBN().equals("")){
			sql += " and ISBN like concat('%',?,'%')";
			map.put(map.size()+1, bookinfo.getISBN());
		}
		/////////////////
		System.out.println(sql);
		ResultSet rs = null;
		List<Bookinfo> list = new ArrayList<Bookinfo>();
		try {
			pstmt = conn.prepareStatement(sql);
			Set<Map.Entry<Integer, Object>> set = map.entrySet();
			for (Map.Entry<Integer, Object> entry : set) {
				pstmt.setObject(entry.getKey(), entry.getValue());
			}
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				Bookinfo book = new Bookinfo();
				book.setId(rs.getInt("id"));
				book.setBookname(rs.getString("bookname"));
				BookType bt = new BookType();
				bt.setId(rs.getInt("typeid"));
				bt.setTypename(rs.getString("typename"));
				book.setBt(bt);
				book.setAuthor(rs.getString("author"));
				book.setISBN(rs.getString("ISBN"));
				book.setPrice(rs.getDouble("price"));
				book.setPage(rs.getInt("page"));
				book.setInTime(rs.getString("inTime"));
				book.setOperator(rs.getInt("operator"));
				
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
