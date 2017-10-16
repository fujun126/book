package com.libsys.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.util.DB;
import com.libsys.manager.pojo.Manager;


public class ManagerDao {
	/**
	 * ����
	 */
private	DB db = DB.getDB();
private	Connection conn;
private	PreparedStatement pstmt ;
	public boolean saveManager(Manager manager){
		
		 conn= db.getConn();
		
		String sql = "insert into tb_manager(username,pwd,role) values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, manager.getUsername());
			pstmt.setString(2, manager.getPwd());
			pstmt.setString(3, manager.getRole());
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
	public boolean editManager(Manager manager){
		conn=db.getConn();
		String sql = "update tb_manager set username=?,pwd=?,role=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, manager.getUsername());
			pstmt.setString(2, manager.getPwd());
			pstmt.setString(3, manager.getRole());
			pstmt.setInt(4, manager.getId());
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
	public boolean delManager(int id){
		conn=db.getConn();
		String sql = "delete from tb_manager where id=?";
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
	private ResultSet rs;
	public List<Manager> queryManager(){
		conn=db.getConn();
		String sql = "select id,username,pwd,role from tb_manager";
		
		List<Manager> list = new ArrayList<Manager>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				Manager manager = new Manager();
				manager.setId(rs.getInt("id"));
				manager.setUsername(rs.getString("username"));
				manager.setPwd(rs.getString("pwd"));
				manager.setRole(rs.getString("role"));
				
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return list;
	}
	/**
	 * ��ѯ��������   ---����������¼ 
	 */
	public Manager getManager(int id){
		conn=db.getConn();
		String sql = "select id,username,pwd,role from tb_manager where id=?";
		
		Manager manager = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				manager = new Manager();
				manager.setId(rs.getInt("id"));
				manager.setUsername(rs.getString("username"));
				manager.setPwd(rs.getString("pwd"));
				manager.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return manager;
	}
	
	/**
	 * ��ѯ��������   ---����������¼ 
	 */
	public Manager getManager(String name){
		conn=db.getConn();
		String sql = "select id,username,pwd,role from tb_manager where username=?";
		Manager manager = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				manager = new Manager();
				manager.setId(rs.getInt("id"));
				manager.setUsername(rs.getString("username"));
				manager.setPwd(rs.getString("pwd"));
				manager.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close(conn, pstmt, rs);
		}
		return manager;
	}
	
	/**
	 * �޸�
	 */
	public boolean changePwd(int id, String pwd){
		conn=db.getConn();
		String sql = "update tb_manager set pwd=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setInt(2, id);
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
}
