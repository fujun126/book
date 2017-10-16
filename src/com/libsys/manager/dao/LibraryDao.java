package com.libsys.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.util.DB;
import com.libsys.manager.pojo.Library;


public class LibraryDao {
	
	private	DB db = DB.getDB();
	private	Connection conn;
	private	PreparedStatement pstmt ;
	/**
	 * ����
	 */
	public boolean saveLibrary(Library lib){
		conn=db.getConn();
		String sql = "insert into tb_library(libraryname,curator,tel,address,email,url,createDate,"
				+ "introduce) values(?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lib.getLibraryname());
			pstmt.setString(2, lib.getCurator());
			pstmt.setString(3, lib.getTel());
			pstmt.setString(4, lib.getAddress());
			pstmt.setString(5, lib.getEmail());
			pstmt.setString(6, lib.getUrl());
			pstmt.setString(7, lib.getCreateDate());
			pstmt.setString(8, lib.getIntroduce());
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
	 * �޸�
	 */
	public boolean editLibrary(Library lib){
		conn=db.getConn();
		String sql = "update tb_library set libraryname=?,curator=?,tel=?,address=?,email=?,url=?,"
				+ "createDate=?,introduce=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lib.getLibraryname());
			pstmt.setString(2, lib.getCurator());
			pstmt.setString(3, lib.getTel());
			pstmt.setString(4, lib.getAddress());
			pstmt.setString(5, lib.getEmail());
			pstmt.setString(6, lib.getUrl());
			pstmt.setString(7, lib.getCreateDate());
			pstmt.setString(8, lib.getIntroduce());
			pstmt.setInt(9, lib.getId());
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
	 * ɾ��
	 */
	public boolean delLibrary(int id){
		conn=db.getConn();
		String sql = "delete from tb_library where id=?";
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
	 * ��ѯȫ������
	 */
	public List<Library> queryLibrary(){
		conn=db.getConn();
		String sql = "select id,libraryname,curator,tel,address,email,url,createDate,"
				+ "introduce from tb_library";
		ResultSet rs = null;
		List<Library> list = new ArrayList<Library>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				Library lib = new Library();
				lib.setId(rs.getInt("id"));
				lib.setLibraryname(rs.getString("libraryname"));
				lib.setCurator(rs.getString("curator"));
				lib.setTel(rs.getString("tel"));
				lib.setAddress(rs.getString("address"));
				lib.setEmail(rs.getString("email"));
				lib.setUrl(rs.getString("url"));
				lib.setCreateDate(rs.getString("createDate"));
				lib.setIntroduce(rs.getString("introduce"));
				
				list.add(lib);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return list;
	}
	/**
	 * ��ѯ��������
	 */
	public Library getLibrary(int id){
		conn=db.getConn();
		String sql = "select id,libraryname,curator,tel,address,email,url,createDate,"
				+ "introduce from tb_library where id=?";
		ResultSet rs = null;
		Library lib = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				lib = new Library();
				lib.setId(rs.getInt("id"));
				lib.setLibraryname(rs.getString("libraryname"));
				lib.setCurator(rs.getString("curator"));
				lib.setTel(rs.getString("tel"));
				lib.setAddress(rs.getString("address"));
				lib.setEmail(rs.getString("email"));
				lib.setUrl(rs.getString("url"));
				lib.setCreateDate(rs.getString("createDate"));
				lib.setIntroduce(rs.getString("introduce"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return lib;
	}
}
