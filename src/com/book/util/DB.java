package com.book.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

private static DB db=new DB();
public static  DB getDB(){
	return db;
}
private DB(){
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
	
		e.printStackTrace();
	}
}
	private String url="jdbc:mysql://192.168.0.115:3306/db_lib";
	private String user="root";
	private String  password="root";
public Connection   getConn(){
	try {
		return DriverManager.getConnection(url, user, password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
	
public  void  close(Connection conn,PreparedStatement pstmt,ResultSet rs){
	
	
	   try {
		   if(rs!=null){
			  rs.close();
		   }
		   if(pstmt!=null){
			  pstmt.close();
		   }
		   if(conn!=null){
		      conn.close();
		   }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	
}
