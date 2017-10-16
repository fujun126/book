package com.book.ReaderType.ui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.book.ReaderType.dao.ReaderTypeDao;
import com.book.ReaderType.pojo.ReaderType;
import com.book.util.MyTable;

/**
 * 
 * @author 付军
 *
 * @date 2017-9-25上午10:07:57
 */

public class ReaderTypeUi extends JFrame{
	

// 三个按钮 
JButton save =new JButton("新增");
JButton edit =new JButton("修改");
JButton del =new JButton("删除");

// 一个表格
JTable jtable = new MyTable();

//滚动条
JScrollPane  jsp=new JScrollPane(jtable);
//一个面板
JPanel jp = new JPanel();

public ReaderTypeUi(){
	this.setTitle("读者类型");
	this.setSize(300,300);
	this.setLocationRelativeTo(null);
    jp.add(save);
	jp.add(edit);
	jp.add(del);
	this.add(jp,BorderLayout.SOUTH);
	this.add(jsp);
	
	
	addActionLiserter();
	
	loadtable(); // 给表格添加数据
	
	this.setVisible(true);
}




// 给按钮添加事件的方法
public  void  addActionLiserter(){
	//给添加数据的按钮添加事件
	save.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		  new AddReaderTypeUi(ReaderTypeUi.this);
		}
	});

    //给修改的按钮添加事件 
	edit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			  // 先判断用户选择了那条数据 
		  
			 //返回用户先择的是第几条记录 
		     int index=jtable.getSelectedRow();
			
			  if(index==-1){
				//如果值为-1说明没有选中  
				  JOptionPane.showMessageDialog(null, "请选择你要修改的数据");
				  return;  //结束方法
			  }
		     
			 
			  //获取用户选中记录的编号 
			  
			String  sid=  (String) jtable.getValueAt(index, 0);
			
			//将字符串转换成  int类型
			int id=Integer.parseInt(sid);
			
			 //创建一个修改窗体的对象
			new EditReaderTypeUi(ReaderTypeUi.this,id);
		}
	});
	
	//给删除的按钮添加事件
	 del.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		//判断用户是否选择到记录  
			
        int index=jtable.getSelectedRow();
			
        if(index==-1){
        	//如果值为-1说明没有选中  
			  JOptionPane.showMessageDialog(null, "请选择你要修改的数据");
			  return;  //结束方法
		 	
        }
			
	 int  a= JOptionPane.showConfirmDialog(null, "你是真的真的要删除这个记录吗 吗吗");
           
	 if(a==0){   //a的值是0 说明用户是确定删除
		 
		  //或用户删除的id
		 
		String sid= (String) jtable.getValueAt(index, 0);
		 
		//将id转换成 int类型  
		int id=Integer.parseInt(sid);
		 
		//创建dao层 从数据库中删除数据  
		ReaderTypeDao  rd=new ReaderTypeDao();
		
		if(rd.delReaderType(id)){
			  JOptionPane.showMessageDialog(null, "删除成功");
			  
		}else{
			  JOptionPane.showMessageDialog(null, "删除失败");
			  
		}
		
		//刷新表格 
		loadtable();
		 
	 }
        
        
			
			
		}
	});


}


// 给表格添加数据  

  public  void   loadtable(){
//从数据库中查询到所有的数据  
	  ReaderTypeDao  rd=new ReaderTypeDao();
	  List<ReaderType> list=rd.queryAll();
	  //将list数据放置到一个Vertor集合中
	  Vector<Vector<String>>  vdata=new Vector<Vector<String>>(); 
	  for(ReaderType rt:list){
		  
		  Vector<String>  v1=new Vector<String>();
		  //将集合中的每一个对象都放置到新的集合中
		  v1.add(String.valueOf(rt.getId()));
		  v1.add(rt.getName());
		  vdata.add(v1);
	  }
	  
	  Vector<String> head=new Vector<String>();	  
	  head.add("编号");
	  head.add("类型");
	  
	  //创建一个表格模型   
	  TableModel  tm=new DefaultTableModel(vdata, head);
	  
	  //将模型中的数据设置到表格中  
	  
	  jtable.setModel(tm);
	  
  }




}
