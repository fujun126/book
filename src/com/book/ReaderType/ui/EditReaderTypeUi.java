package com.book.ReaderType.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.book.ReaderType.dao.ReaderTypeDao;
import com.book.ReaderType.pojo.ReaderType;

public class EditReaderTypeUi extends  JFrame {

	private   JLabel      jid=new JLabel("编号");
	private   JTextField  tid=new JTextField(10);
	private   JLabel      jname=new JLabel("类型");
	private   JTextField  tname=new JTextField(10);
	private   JButton     save=new JButton("确定");
	private   JButton     esc=new JButton("重置");
	private   JPanel     jp1=new JPanel();
	private   JPanel     jp2=new JPanel();
	private   JPanel     jp3=new JPanel();
	
	//定义一个其他窗口的对象  用来调用他的刷新表格的方法
	private  ReaderTypeUi  rtu;
	
	//定义一个 int id 用来接受用户传递过来选中的id
	
	private int id;
	
	//使用有参数的构造方法  给其他窗体的对象赋值
	public EditReaderTypeUi(ReaderTypeUi rtu,int id){
		this.rtu=rtu;
		this.id=id;
		this.setTitle("修改类型");
		this.setSize(200, 200);
		this.setLocationRelativeTo(null);
		
		// 添加组件的方法
		addcomponent();
		
		//添加事件的方法
		addActionListener();
		
		// 添加数据的方法
		load();
		this.setVisible(true);
	}
	
	
	//添加组件的方法
	
	public  void  addcomponent(){
		
		//改变窗体的布局
		this.setLayout(new GridLayout(3, 1));
		jp3.add(jid);
		jp3.add(tid);
		
		//设置编号不能修改  
		tid.setEditable(false);
		
		jp1.add(jname);
		jp1.add(tname);
		
		jp2.add(save);
		jp2.add(esc);
		
		this.add(jp3);
		this.add(jp1);
		this.add(jp2);
		
		
	}
	
	
	
	//添加事件的方法 
	public  void addActionListener(){
		//添加按钮的事件
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//将用户输入的信息添加到 数据库 
				//1 获取到用户输入的信息
				
				String name=tname.getText().trim();
				
				if(name.length()<=1 || name.equals("")){
					
				 JOptionPane.showMessageDialog(null,"请输入读者类型");	
					return;
				}
				
				
				
				ReaderTypeDao  rd=new ReaderTypeDao();
				
				
				ReaderType  rt=new ReaderType();
				rt.setName(name);
				rt.setId(id);
				if(rd.updateReaderType(rt)){
					
					JOptionPane.showMessageDialog(null, "修改成功");
					// 添加成功后  添加页面要隐藏  
					EditReaderTypeUi.this.setVisible(false);
					
					// 刷新表格的方法
					rtu.loadtable();
					
					
				}else{
					JOptionPane.showMessageDialog(null, "添加失败");
				}
				
				
				
				
				
				
			}
		});
		
		 //给重置添加一个 事件 
		
		esc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			   load();	
			}
		});
		
		
	}
	
	//页面加载数据的方法   
	
	public  void   load(){
		//通过id 去数据库查询单个对象 
		ReaderTypeDao  rd=new ReaderTypeDao();
		ReaderType  rt=rd.getReaderTypeById(id);
		
		//将查询到数据 设置到 界面上  
		tid.setText(String.valueOf(rt.getId()));
		tname.setText(rt.getName());
		
		
		
		
		
	}
	
	
	
}
