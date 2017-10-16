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

public class AddReaderTypeUi extends  JFrame {

	private   JLabel      jname=new JLabel("����");
	private   JTextField  tname=new JTextField(10);
	private   JButton     save=new JButton("ȷ��");
	private   JButton     esc=new JButton("ȡ��");
	private   JPanel     jp1=new JPanel();
	private   JPanel     jp2=new JPanel();
	
	//����һ���������ڵĶ���  ������������ˢ�±��ķ���
	private  ReaderTypeUi  rtu;
	
	//ʹ���в����Ĺ��췽��  ����������Ķ���ֵ
	public AddReaderTypeUi(ReaderTypeUi rtu){
		this.rtu=rtu;
		this.setTitle("�������");
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		
		// �������ķ���
		addcomponent();
		
		//����¼��ķ���
		addActionListener();
		
		
		this.setVisible(true);
	}
	
	
	//�������ķ���
	
	public  void  addcomponent(){
		
		//�ı䴰��Ĳ���
		this.setLayout(new GridLayout(2, 1));
		
		jp1.add(jname);
		jp1.add(tname);
		
		jp2.add(save);
		jp2.add(esc);
		
		this.add(jp1);
		this.add(jp2);
		
		
	}
	
	
	
	//����¼��ķ��� 
	public  void addActionListener(){
		//��Ӱ�ť���¼�
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//���û��������Ϣ��ӵ� ���ݿ� 
				//1 ��ȡ���û��������Ϣ
				
				String name=tname.getText().trim();
				
				if(name.length()<=1 || name.equals("")){
					
				 JOptionPane.showMessageDialog(null,"�������������");	
					return;
				}
				
				
				ReaderTypeDao  rd=new ReaderTypeDao();
				
				
				ReaderType  rt=new ReaderType();
				rt.setName(name);
				
				if(rd.addReaderType(rt)){
					
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
					// ��ӳɹ���  ���ҳ��Ҫ����  
					AddReaderTypeUi.this.setVisible(false);
					
					// ˢ�±��ķ���
					rtu.loadtable();
					
					
				}else{
					JOptionPane.showMessageDialog(null, "���ʧ��");
				}
				
				
				
				
				
				
			}
		});
		
		
	}
	
	
}
