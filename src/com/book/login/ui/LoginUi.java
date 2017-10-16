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
	private  JLabel  jname=new JLabel("�û��ʺţ�");
	private  JLabel  jpwd=new JLabel("�û����룺");
	
	private  JTextField     tname=new JTextField(18);  
	private  JPasswordField tpwd=new JPasswordField(18);  
	
	private  JButton   login1=new JButton("��½");
	private  JButton   esc   =new JButton("ȡ��");
	private  JLabel   pic=new JLabel(new ImageIcon("file/login.jpg"));
	private  JPanel   jp1=new JPanel();
	private  JPanel   jp2=new JPanel();
	public  LoginUi(){
		this.setTitle("��¼����");
		this.setSize(310,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
		pic.setBounds(0, 0, this.getWidth(), this.getHeight());  
        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);  
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
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
		//����¼��ķ���
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
				//JOptionPane.showMessageDialog(null,"�˳��ɹ���");
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
			
			JOptionPane.showMessageDialog(null, "�������ʺ���");
			return ;
		}
		String pwd=tpwd.getText().trim();
		if(pwd.equals("")){
			
			JOptionPane.showMessageDialog(null, "����������");
			return ;
		}
		
		ManagerDao  md=new ManagerDao();
		
		Manager  m=md.getManager(name);
		
		if(m==null){ // �������Ϊnull  ˵�����ݿ���û�и��û���������
			JOptionPane.showMessageDialog(null, "��������û���������");
		 return;
		}else{
			if(m.getPwd().equals(pwd)){
				
				JOptionPane.showMessageDialog(null, name+"����ϲ���¼�ɹ�");
				
				//�������������  ���Ҵ����û�����
				new MainUi(m);
				//�õ�¼������ʧ  
				LoginUi.this.setVisible(false);
				
			}else{
				JOptionPane.showMessageDialog(null, "����������벻��ȷ");
			}
			
		}
	}
}
