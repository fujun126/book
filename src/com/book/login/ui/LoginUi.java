package com.book.login.ui;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.book.Main.ui.MainUi;
import com.libsys.manager.dao.ManagerDao;
import com.libsys.manager.pojo.Manager;

public class LoginUi extends JFrame{
	private  JLabel  jname=new JLabel("用户帐号：");
	private  JLabel  jpwd=new JLabel("用户密码：");
	
	private  JTextField     tname=new JTextField(18);  
	private  JPasswordField tpwd=new JPasswordField(18);  
	
	private  JButton   login1=new JButton("登陆");
	private  JButton   esc   =new JButton("取消");
	private  JLabel   pic=new JLabel(new ImageIcon("file/login.jpg"));
	private  JPanel   jp1=new JPanel();
	private  JPanel   jp2=new JPanel();
	public  LoginUi(){
		this.setTitle("登录界面");
		this.setSize(310,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		// 把标签的大小位置设置为图片刚好填充整个面板  
		pic.setBounds(0, 0, this.getWidth(), this.getHeight());  
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);  
        // 把背景图片添加到分层窗格的最底层作为背景  
        this.getLayeredPane().add(pic, new Integer(Integer.MIN_VALUE));  
		jp1.setOpaque(false);
		//this.setResizable(false);
		
		
		jp1.add(jname);
		jp1.add(tname);
		jp1.add(jpwd);
		jp1.add(tpwd);
		jp1.add(login1);
		jp1.add(esc);
		this.add(jp1);
		//添加事件的方法
		addActionListener();
		this.setVisible(true);
	}
	
    public  void  addActionListener(){
    	login1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 login();
			}
		});
    	esc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null,"退出成功！");
				System.exit(0);
			}
		});
    	tpwd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			   login();	
			}
		});
    	
    	tname.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//   
				tpwd.requestFocus(); 
			}
		});
    	
    	tname.addKeyListener(new KeyAdapter () {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			
			}
		});
    	
    }	
	public  void  login(){
		String name=tname.getText().trim();
		if(name.equals("")){
			
			JOptionPane.showMessageDialog(null, "请输入帐号名");
			return ;
		}
		String pwd=tpwd.getText().trim();
		if(pwd.equals("")){
			
			JOptionPane.showMessageDialog(null, "请输入密码");
			return ;
		}
		
		ManagerDao  md=new ManagerDao();
		
		Manager  m=md.getManager(name);
		
		if(m==null){ // 如果对象为null  说明数据库中没有该用户名的数据
			JOptionPane.showMessageDialog(null, "你输入的用户名不存在");
		 return;
		}else{
			if(m.getPwd().equals(pwd)){
				
				JOptionPane.showMessageDialog(null, name+"：恭喜你登录成功");
				
				//创建主界面对象  并且传入用户对象
				new MainUi(m);
				//让登录界面消失  
				LoginUi.this.setVisible(false);
				
			}else{
				JOptionPane.showMessageDialog(null, "你输入的密码不正确");
			}
			
		}
	}
}
