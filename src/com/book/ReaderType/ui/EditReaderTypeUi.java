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

	private   JLabel      jid=new JLabel("���");
	private   JTextField  tid=new JTextField(10);
	private   JLabel      jname=new JLabel("����");
	private   JTextField  tname=new JTextField(10);
	private   JButton     save=new JButton("ȷ��");
	private   JButton     esc=new JButton("����");
	private   JPanel     jp1=new JPanel();
	private   JPanel     jp2=new JPanel();
	private   JPanel     jp3=new JPanel();
	
	//����һ���������ڵĶ���  ������������ˢ�±��ķ���
	private  ReaderTypeUi  rtu;
	
	//����һ�� int id ���������û����ݹ���ѡ�е�id
	
	private int id;
	
	//ʹ���в����Ĺ��췽��  ����������Ķ���ֵ
	public EditReaderTypeUi(ReaderTypeUi rtu,int id){
		this.rtu=rtu;
		this.id=id;
		this.setTitle("�޸�����");
		this.setSize(200, 200);
		this.setLocationRelativeTo(null);
		
		// �������ķ���
		addcomponent();
		
		//����¼��ķ���
		addActionListener();
		
		// ������ݵķ���
		load();
		this.setVisible(true);
	}
	
	
	//�������ķ���
	
	public  void  addcomponent(){
		
		//�ı䴰��Ĳ���
		this.setLayout(new GridLayout(3, 1));
		jp3.add(jid);
		jp3.add(tid);
		
		//���ñ�Ų����޸�  
		tid.setEditable(false);
		
		jp1.add(jname);
		jp1.add(tname);
		
		jp2.add(save);
		jp2.add(esc);
		
		this.add(jp3);
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
				rt.setId(id);
				if(rd.updateReaderType(rt)){
					
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					// ��ӳɹ���  ���ҳ��Ҫ����  
					EditReaderTypeUi.this.setVisible(false);
					
					// ˢ�±��ķ���
					rtu.loadtable();
					
					
				}else{
					JOptionPane.showMessageDialog(null, "���ʧ��");
				}
				
				
				
				
				
				
			}
		});
		
		 //���������һ�� �¼� 
		
		esc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			   load();	
			}
		});
		
		
	}
	
	//ҳ��������ݵķ���   
	
	public  void   load(){
		//ͨ��id ȥ���ݿ��ѯ�������� 
		ReaderTypeDao  rd=new ReaderTypeDao();
		ReaderType  rt=rd.getReaderTypeById(id);
		
		//����ѯ������ ���õ� ������  
		tid.setText(String.valueOf(rt.getId()));
		tname.setText(rt.getName());
		
		
		
		
		
	}
	
	
	
}
