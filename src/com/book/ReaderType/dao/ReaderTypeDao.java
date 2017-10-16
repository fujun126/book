package com.book.ReaderType.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.ReaderType.pojo.ReaderType;
import com.book.util.DB;

public class ReaderTypeDao {
private  DB                 db=DB.getDB();
private  Connection         conn;
private  PreparedStatement  pstmt;
private  ResultSet          rs;	
	
	
	
	
	
	  //��ɾ�Ĳ�

   //ɾ���ķ���  


   public boolean   delReaderType(int id){
	   conn=db.getConn();
	     String sql="delete from tb_readertype where id=?";	  
		  try {
			pstmt=conn.prepareStatement(sql);
			
		
			pstmt.setInt(1, id);
			
			int a=pstmt.executeUpdate();
			return a>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close(conn, pstmt, rs);
		}
		  
		  return false;
   }



     //�޸ĵķ���   

  public  boolean  updateReaderType(ReaderType rt){
	  conn=db.getConn();
	     String sql="update tb_readertype set name =?  where id=?";	  
		  try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, rt.getName());
			pstmt.setInt(2, rt.getId());
			
			int a=pstmt.executeUpdate();
			return a>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close(conn, pstmt, rs);
		}
		  
		  return false;
  }



   //��ѯ������¼�ķ��� 
   public   ReaderType   getReaderTypeById(int id){
	 ReaderType rt=null;
	 String sql="select id,name from tb_readertype where id=?";
  	 conn=db.getConn();
  	 try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
		    //���������
		   if(rs.next()){
		        rt=new ReaderType();
		    	rt.setId(rs.getInt("id"));
		    	rt.setName(rs.getString("name"));
		    }
  	 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close(conn, pstmt, rs);
		}
  	 return rt;
   }



    // ���ӵķ��� 

  public boolean  addReaderType(ReaderType  rt){
 	 conn=db.getConn();
     String sql="insert into tb_readertype (name) values(?)";	  
	  try {
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, rt.getName());
		
		int a=pstmt.executeUpdate();
		return a>0;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		db.close(conn, pstmt, rs);
	}
	  
	  return false;
  }



  //��ѯ�ķ��������з���ֵ 
     public  List<ReaderType>  queryAll(){
    	 List<ReaderType>  list=new ArrayList<ReaderType>();
    	 String sql="select id,name from tb_readertype";
    	
    	 conn=db.getConn();
    	 try {
			pstmt=conn.prepareStatement(sql);
		    rs=pstmt.executeQuery();
		    //���������
		    while(rs.next()){
		    	ReaderType   rt=new ReaderType();
		    	rt.setId(rs.getInt("id"));
		    	rt.setName(rs.getString("name"));
		    	//��������õ�������
		    	list.add(rt);
		    }
    	 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close(conn, pstmt, rs);
		}
    	 return list;
     }




	
}
