package com.book.Main.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.book.ReaderType.ui.ReaderTypeUi;
import com.book.book.ui.BookTypeUI;
import com.book.book.ui.BookinfoUI;
import com.book.borrow.ui.BookBackUI;
import com.book.borrow.ui.BorrowBackUI;
import com.book.query.ui.BookBorrowBackUIQuery;
import com.book.query.ui.BookinfoUIQuery;
import com.book.rankinglist.ui.BookRankingListUI;
import com.book.rankinglist.ui.ReaderRankingListUI;
import com.book.reader.ui.ReaderUI;
import com.libsys.manager.pojo.Manager;
import com.libsys.manager.ui.LibraryUI;
import com.libsys.manager.ui.ManagerUI;



public class MainUi extends JFrame {
JMenuBar jmb = new JMenuBar();

public JMenu jm1 = new JMenu("读者管理");
JMenu jm2 = new JMenu("图书管理");
JMenu jm3 = new JMenu("图书借还");
JMenu jm4 = new JMenu("系统查询");
JMenu jm5 = new JMenu("排行榜");
JMenu jm6 = new JMenu("系统设置");
JMenu jm7 =new JMenu("用户登陆");

JMenuItem jmi11 =new JMenuItem("读者类型管理");
JMenuItem jmi12 =new JMenuItem("读者档案管理");
JMenuItem jmi21 =new JMenuItem("图书类型管理");
JMenuItem jmi22 =new JMenuItem("图书档案管理");
JMenuItem jmi31 =new JMenuItem("图书借阅");
JMenuItem jmi32 =new JMenuItem("图书归还");
JMenu jmi41 =new JMenu("图书借阅查询");
JMenuItem jmi42 =new JMenuItem("图书档案查询");
JMenuItem jmi51 =new JMenuItem("图书借阅排行");
JMenuItem jmi52 =new JMenuItem("读者借阅排行");
JMenuItem jmi61 =new JMenuItem("图书馆信息");
JMenuItem jmi62 =new JMenuItem("用户管理");

JMenuItem jmi7 =new JMenuItem("用户注册");
JMenuItem jmi8 =new JMenuItem("更改密码");
JMenuItem jmi9 =new JMenuItem("退出系统");
JMenuItem jmi411 =new JMenuItem("借阅查询");
JMenuItem jmi412 =new JMenuItem("逾期查询");
 JMenuItem jmi00 =new JMenuItem("新增数据");
 JMenuItem jmi01 =new JMenuItem("修改数据");
 JMenuItem jmi02 =new JMenuItem("删除数据");
 JMenuItem jmi03 =new JMenuItem("查询数据");

 
 
 JLabel pic = new JLabel(new ImageIcon("file/bg.jpg"));
 
 
//在主界面下边展示 登录的用户是那个
	
	private  JPanel jp2=new JPanel();
	private  JLabel  jl1=new JLabel();
	private  JLabel  jl2=new JLabel("欢迎使用图书管理系统");
	private  JLabel  jl3=new JLabel();
 
 
 //定义一个全局的用户对象变量  ，用来保存是那个用户登录的
 private  Manager  manager;
 public MainUi(Manager  manager){
	 this();
	 System.out.println(12312);
	 this.manager=manager;
	 jp2.setLayout(new GridLayout(1, 3));
	 jp2.add(jl2);
	 jp2.add(jl1);
	 jp2.add(jl3);
	 jl1.setText("操作员:"+this.manager.getUsername());
	 this.add(jp2,BorderLayout.SOUTH);
     
	 this.admin();
 }
 
public MainUi(){
	this.setTitle("图书管理系统");
	this.setSize(600,400);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	 
	jmi21.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new BookTypeUI();
		}
	});
	jmi22.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new BookinfoUI();
		}
	});
	jmi11.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
   			new ReaderTypeUi();  //创建读者管理的界面
		}
	});
	jmi12.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			  new ReaderUI();
		}
	});
	jmi31.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new BorrowBackUI();
		}
	});
	
	jmi32.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new BookBackUI();
		}
	});
	jmi42.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new BookinfoUIQuery();
		}
	});
	
	
	jmi411.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new BookBorrowBackUIQuery();
		}
	});
	
	
	jmi7.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	
	//修改密码
	jmi8.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
	        new  ChangePwdUI(manager);
		}
	});
	
	
	//退出系统
	jmi9.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int a=JOptionPane.showConfirmDialog(null, "确定退出系统吗？", "系统退出", 2);
		   if(a==0) {
			   System.exit(0);
		   }
		}
	});
	
	//图书借阅排行榜
	jmi51.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new BookRankingListUI();
		}
	});
	
	//读者借阅排行榜
	jmi52.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new ReaderRankingListUI();
		}
	});
	
	//图书馆信息
	jmi61.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		   new  LibraryUI();
		}
	});
	
	//用户管理
	jmi62.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		   new  ManagerUI();
		}
	});
	
	
	new Thread(new Runnable() {
		@Override
		public void run() {
	       while(true) {
	    	   jl3.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	    	   try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }		
			
		}
	}).start();
	
}
 
public void admin(){

	jm1.add(jmi11);
	jm1.add(jmi12);
	jm2.add(jmi21);
	jm2.add(jmi22);
	jm3.add(jmi31);
	jm3.add(jmi32);
	jmi41.add(jmi411);
	//jmi41.add(jmi412);
	jm4.add(jmi411);
	jm4.add(jmi42);
	jm5.add(jmi51);
	jm5.add(jmi52);
	jm6.add(jmi61);
	jm6.add(jmi62);
	jm7.add(jmi7);
	jm7.add(jmi8);
	jm7.add(jmi9);

	jmb.add(jm1);
	jmb.add(jm2);
	jmb.add(jm3);
	jmb.add(jm4);
	jmb.add(jm5);
	jmb.add(jm6);
	jmb.add(jm7);

	
	

	this.add(jmb,BorderLayout.NORTH);
	this.add(pic);
	this.setResizable(false);
	this.setVisible(true);
}
public void Operator(){
	jm1.add(jmi11);
	jm1.add(jmi12);
	jm2.add(jmi21);
	jm2.add(jmi22);
	jm3.add(jmi31);
	jm3.add(jmi32);
	jmi41.add(jmi411);
	jmi41.add(jmi412);
	jm4.add(jmi41);
	jm4.add(jmi42);
	jm5.add(jmi51);
	jm5.add(jmi52);
	jm7.add(jmi7);
	jm7.add(jmi8);
	jm7.add(jmi9);

	
	jmb.add(jm1);
	jmb.add(jm2);
	jmb.add(jm3);
	jmb.add(jm4);
	jmb.add(jm5);
	jmb.add(jm7);
	
	
//	JTable jtable = new JTable();
//	JScrollPane jsp = new JScrollPane();
	
	this.add(jmb,BorderLayout.NORTH);
	this.add(pic);
	this.setResizable(false);
	this.setVisible(true);
}
public void reader(){

	jm3.add(jmi31);
	jm3.add(jmi32);
	jm5.add(jmi51);
	jm5.add(jmi52);
	jm7.add(jmi7);
	jm7.add(jmi8);
	jm7.add(jmi9);
	jmb.add(jm3);
	jmb.add(jm5);
	jmb.add(jm7);
	this.add(jmb,BorderLayout.NORTH);
	this.add(pic);
	this.setResizable(false);
	this.setVisible(true);
}
}


















