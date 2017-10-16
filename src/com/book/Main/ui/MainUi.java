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

public JMenu jm1 = new JMenu("���߹���");
JMenu jm2 = new JMenu("ͼ�����");
JMenu jm3 = new JMenu("ͼ��軹");
JMenu jm4 = new JMenu("ϵͳ��ѯ");
JMenu jm5 = new JMenu("���а�");
JMenu jm6 = new JMenu("ϵͳ����");
JMenu jm7 =new JMenu("�û���½");

JMenuItem jmi11 =new JMenuItem("�������͹���");
JMenuItem jmi12 =new JMenuItem("���ߵ�������");
JMenuItem jmi21 =new JMenuItem("ͼ�����͹���");
JMenuItem jmi22 =new JMenuItem("ͼ�鵵������");
JMenuItem jmi31 =new JMenuItem("ͼ�����");
JMenuItem jmi32 =new JMenuItem("ͼ��黹");
JMenu jmi41 =new JMenu("ͼ����Ĳ�ѯ");
JMenuItem jmi42 =new JMenuItem("ͼ�鵵����ѯ");
JMenuItem jmi51 =new JMenuItem("ͼ���������");
JMenuItem jmi52 =new JMenuItem("���߽�������");
JMenuItem jmi61 =new JMenuItem("ͼ�����Ϣ");
JMenuItem jmi62 =new JMenuItem("�û�����");

JMenuItem jmi7 =new JMenuItem("�û�ע��");
JMenuItem jmi8 =new JMenuItem("��������");
JMenuItem jmi9 =new JMenuItem("�˳�ϵͳ");
JMenuItem jmi411 =new JMenuItem("���Ĳ�ѯ");
JMenuItem jmi412 =new JMenuItem("���ڲ�ѯ");
 JMenuItem jmi00 =new JMenuItem("��������");
 JMenuItem jmi01 =new JMenuItem("�޸�����");
 JMenuItem jmi02 =new JMenuItem("ɾ������");
 JMenuItem jmi03 =new JMenuItem("��ѯ����");

 
 
 JLabel pic = new JLabel(new ImageIcon("file/bg.jpg"));
 
 
//���������±�չʾ ��¼���û����Ǹ�
	
	private  JPanel jp2=new JPanel();
	private  JLabel  jl1=new JLabel();
	private  JLabel  jl2=new JLabel("��ӭʹ��ͼ�����ϵͳ");
	private  JLabel  jl3=new JLabel();
 
 
 //����һ��ȫ�ֵ��û��������  �������������Ǹ��û���¼��
 private  Manager  manager;
 public MainUi(Manager  manager){
	 this();
	 System.out.println(12312);
	 this.manager=manager;
	 jp2.setLayout(new GridLayout(1, 3));
	 jp2.add(jl2);
	 jp2.add(jl1);
	 jp2.add(jl3);
	 jl1.setText("����Ա:"+this.manager.getUsername());
	 this.add(jp2,BorderLayout.SOUTH);
     
	 this.admin();
 }
 
public MainUi(){
	this.setTitle("ͼ�����ϵͳ");
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
   			new ReaderTypeUi();  //�������߹���Ľ���
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
	
	//�޸�����
	jmi8.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
	        new  ChangePwdUI(manager);
		}
	});
	
	
	//�˳�ϵͳ
	jmi9.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int a=JOptionPane.showConfirmDialog(null, "ȷ���˳�ϵͳ��", "ϵͳ�˳�", 2);
		   if(a==0) {
			   System.exit(0);
		   }
		}
	});
	
	//ͼ��������а�
	jmi51.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new BookRankingListUI();
		}
	});
	
	//���߽������а�
	jmi52.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new ReaderRankingListUI();
		}
	});
	
	//ͼ�����Ϣ
	jmi61.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		   new  LibraryUI();
		}
	});
	
	//�û�����
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


















