package com.book.borrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.book.book.pojo.Bookinfo;
import com.book.borrow.pojo.BorrowBack;
import com.book.reader.pojo.Reader;
import com.book.util.DB;

public class BorrowBackDao {
	private	DB db = DB.getDB();
	private	Connection conn;
	private	PreparedStatement pstmt ;
	
	/**
	 * 新增借书记录
	 */
	public boolean saveBorrowBack(BorrowBack bb){
	
		conn=db.getConn();
		String sql = "insert into tb_borrow_back(readerid,bookid,borrowTime,backTime,operator) values(?,?,now(),?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bb.getReader().getId());
			pstmt.setInt(2, bb.getBook().getId());
			pstmt.setString(3, bb.getBackTime());
			pstmt.setInt(4, bb.getOperator());
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
	 * 修改借书记录
	 */
	public boolean editBorrowBack(BorrowBack bb){
		conn=db.getConn();
		String sql = "update tb_borrow_back set readerid=?,bookid=?,borrowTime=now(),backTime=?,operator=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bb.getBook().getId());
			pstmt.setInt(2, bb.getBook().getId());
			pstmt.setString(3, bb.getBackTime());
			pstmt.setInt(4, bb.getOperator());
			pstmt.setInt(5, bb.getId());
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
	 * 删除借书记录
	 */
	public boolean delBorrowBack(int id){
		conn=db.getConn();
		String sql = "delete from tb_borrow_back where id=?";
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
	 * 查询已经借阅的数据
	 */
	public List<BorrowBack> queryBorrowBack(){
		conn=db.getConn();
		String sql = "select a.id,readerid,b.name readername,bookid,c.bookname,borrowTime,"
				+ "backTime,realTime,a.operator,ifback from tb_borrow_back a,tb_reader b,tb_bookinfo c "
				+ "where a.readerid=b.id and a.bookid=c.id and ifback=0";
		ResultSet rs = null;
		List<BorrowBack> list = new ArrayList<BorrowBack>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				BorrowBack bb = new BorrowBack();
				bb.setId(rs.getInt("id"));
				Reader reader = new Reader();
				reader.setId(rs.getInt("readerid"));
				reader.setName(rs.getString("readername"));
				bb.setReader(reader);
				Bookinfo book = new Bookinfo();
				book.setId(rs.getInt("bookid"));
				book.setBookname(rs.getString("bookname"));
				bb.setBook(book);
				bb.setBorrowTime(rs.getString("borrowTime"));
				bb.setBackTime(rs.getString("backTime"));
				bb.setRealTime(rs.getString("realTime"));
				bb.setOperator(rs.getInt("operator"));
				bb.setIfback(rs.getInt("ifback"));
				
				list.add(bb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return list;
	}
	
	
	/**
	 * 查询全部数据
	 */
	public List<BorrowBack> queryAllBorrowBack(){
		conn=db.getConn();
		String sql = "select a.id,readerid,b.name readername,bookid,c.bookname,borrowTime,"
				+ "backTime,realTime,a.operator,ifback from tb_borrow_back a,tb_reader b,tb_bookinfo c "
				+ "where a.readerid=b.id and a.bookid=c.id ";
		ResultSet rs = null;
		List<BorrowBack> list = new ArrayList<BorrowBack>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				BorrowBack bb = new BorrowBack();
				bb.setId(rs.getInt("id"));
				Reader reader = new Reader();
				reader.setId(rs.getInt("readerid"));
				reader.setName(rs.getString("readername"));
				bb.setReader(reader);
				Bookinfo book = new Bookinfo();
				book.setId(rs.getInt("bookid"));
				book.setBookname(rs.getString("bookname"));
				bb.setBook(book);
				bb.setBorrowTime(rs.getString("borrowTime"));
				bb.setBackTime(rs.getString("backTime"));
				bb.setRealTime(rs.getString("realTime"));
				bb.setOperator(rs.getInt("operator"));
				bb.setIfback(rs.getInt("ifback"));
				
				list.add(bb);
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
	public BorrowBack getBorrowBack(int id){
		conn=db.getConn();
		String sql = "select a.id,readerid,b.name readername,bookid,c.bookname,borrowTime,"
				+ "backTime,realTime,a.operator,ifback from tb_borrow_back a,tb_reader b,tb_bookinfo c "
				+ "where a.readerid=b.id and a.bookid=c.id and ifback=0 and a.id=?";
		ResultSet rs = null;
		BorrowBack bb = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				bb = new BorrowBack();
				bb.setId(rs.getInt("id"));
				Reader reader = new Reader();
				reader.setId(rs.getInt("readerid"));
				reader.setName(rs.getString("readername"));
				bb.setReader(reader);
				Bookinfo book = new Bookinfo();
				book.setId(rs.getInt("bookid"));
				book.setBookname(rs.getString("bookname"));
				bb.setBook(book);
				bb.setBorrowTime(rs.getString("borrowTime"));
				bb.setBackTime(rs.getString("backTime"));
				bb.setRealTime(rs.getString("realTime"));
				bb.setOperator(rs.getInt("operator"));
				bb.setIfback(rs.getInt("ifback"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return bb;
	}
	/**
	 * 还书：修改借书记录中的真实还书时间以及是否还书字段
	 */
	public boolean editBorrowBackForBack(int id){
		conn=db.getConn();
		String sql = "update tb_borrow_back set realTime=now(),ifback=1 where id=?";
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
	 * 带条件查询数据
	 */
	public List<BorrowBack> queryBorrowBack(BorrowBack bb){
        conn=db.getConn();
		String sql = "select a.id,readerid,b.name readername,bookid,c.bookname,borrowTime,"
				+ "backTime,realTime,a.operator,ifback from tb_borrow_back a,tb_reader b,tb_bookinfo c "
				+ "where a.readerid=b.id and a.bookid=c.id";
		
		/////////动态条件/////////
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		if(!bb.getReader().getName().equals("")){
			sql += " and b.name like concat('%',?,'%')";
			map.put(map.size()+1, bb.getReader().getName());
		}
		if(!bb.getBook().getBookname().equals("")){
			sql += " and bookname like concat('%',?,'%')";
			map.put(map.size()+1, bb.getBook().getBookname());
		}
		if(bb.getIfback()==0){
			sql += " and ifback = 0 and backTime >= curdate()";
		}
		if(bb.getIfback()==1){
			sql += " and ifback = 1";
		}
		if(bb.getIfback()==2){
			sql += " and ifback = 0 and backTime < curdate()";
		}
		if(!bb.getStartDate().equals("") && !bb.getEndDate().equals("")){
			if(bb.getTimeType()==0)
				sql += " and borrowTime between ? and ?";
			else if(bb.getTimeType()==1)
				sql += " and backTime between ? and ?";
			else if(bb.getTimeType()==2)
				sql += " and realTime between ? and ?";
			map.put(map.size()+1, bb.getStartDate());
			map.put(map.size()+1, bb.getEndDate());
		}
		if(!bb.getStartDate().equals("") && bb.getEndDate().equals("")){
			if(bb.getTimeType()==0)
				sql += " and borrowTime >= ?";
			else if(bb.getTimeType()==1)
				sql += " and backTime >= ?";
			else if(bb.getTimeType()==2)
				sql += " and realTime >= ?";
			map.put(map.size()+1, bb.getStartDate());
		}
		if(bb.getStartDate().equals("") && !bb.getEndDate().equals("")){
			if(bb.getTimeType()==0)
				sql += " and borrowTime <= ?";
			else if(bb.getTimeType()==1)
				sql += " and backTime <= ?";
			else if(bb.getTimeType()==2)
				sql += " and realTime <= ?";
			map.put(map.size()+1, bb.getEndDate());
		}
		System.out.println(sql);
		/////////////////
		ResultSet rs = null;
		List<BorrowBack> list = new ArrayList<BorrowBack>();
		try {
			pstmt = conn.prepareStatement(sql);
			Set<Map.Entry<Integer, Object>> set = map.entrySet();
			for (Map.Entry<Integer, Object> entry : set) {
				pstmt.setObject(entry.getKey(), entry.getValue());
			}
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				BorrowBack bean = new BorrowBack();
				bean.setId(rs.getInt("id"));
				Reader reader = new Reader();
				reader.setId(rs.getInt("readerid"));
				reader.setName(rs.getString("readername"));
				bean.setReader(reader);
				Bookinfo book = new Bookinfo();
				book.setId(rs.getInt("bookid"));
				book.setBookname(rs.getString("bookname"));
				bean.setBook(book);
				bean.setBorrowTime(rs.getString("borrowTime"));
				bean.setBackTime(rs.getString("backTime"));
				bean.setRealTime(rs.getString("realTime"));
				bean.setOperator(rs.getInt("operator"));
				bean.setIfback(rs.getInt("ifback"));
				
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return list;
	}
}
