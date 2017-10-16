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
 * @author ����
 *
 * @date 2017-9-25����10:07:57
 */

public class ReaderTypeUi extends JFrame{
	

// ������ť 
JButton save =new JButton("����");
JButton edit =new JButton("�޸�");
JButton del =new JButton("ɾ��");

// һ�����
JTable jtable = new MyTable();

//������
JScrollPane  jsp=new JScrollPane(jtable);
//һ�����
JPanel jp = new JPanel();

public ReaderTypeUi(){
	this.setTitle("��������");
	this.setSize(300,300);
	this.setLocationRelativeTo(null);
    jp.add(save);
	jp.add(edit);
	jp.add(del);
	this.add(jp,BorderLayout.SOUTH);
	this.add(jsp);
	
	
	addActionLiserter();
	
	loadtable(); // ������������
	
	this.setVisible(true);
}




// ����ť����¼��ķ���
public  void  addActionLiserter(){
	//��������ݵİ�ť����¼�
	save.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		  new AddReaderTypeUi(ReaderTypeUi.this);
		}
	});

    //���޸ĵİ�ť����¼� 
	edit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			  // ���ж��û�ѡ������������ 
		  
			 //�����û�������ǵڼ�����¼ 
		     int index=jtable.getSelectedRow();
			
			  if(index==-1){
				//���ֵΪ-1˵��û��ѡ��  
				  JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ�����");
				  return;  //��������
			  }
		     
			 
			  //��ȡ�û�ѡ�м�¼�ı�� 
			  
			String  sid=  (String) jtable.getValueAt(index, 0);
			
			//���ַ���ת����  int����
			int id=Integer.parseInt(sid);
			
			 //����һ���޸Ĵ���Ķ���
			new EditReaderTypeUi(ReaderTypeUi.this,id);
		}
	});
	
	//��ɾ���İ�ť����¼�
	 del.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		//�ж��û��Ƿ�ѡ�񵽼�¼  
			
        int index=jtable.getSelectedRow();
			
        if(index==-1){
        	//���ֵΪ-1˵��û��ѡ��  
			  JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ�����");
			  return;  //��������
		 	
        }
			
	 int  a= JOptionPane.showConfirmDialog(null, "����������Ҫɾ�������¼�� ����");
           
	 if(a==0){   //a��ֵ��0 ˵���û���ȷ��ɾ��
		 
		  //���û�ɾ����id
		 
		String sid= (String) jtable.getValueAt(index, 0);
		 
		//��idת���� int����  
		int id=Integer.parseInt(sid);
		 
		//����dao�� �����ݿ���ɾ������  
		ReaderTypeDao  rd=new ReaderTypeDao();
		
		if(rd.delReaderType(id)){
			  JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
			  
		}else{
			  JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
			  
		}
		
		//ˢ�±�� 
		loadtable();
		 
	 }
        
        
			
			
		}
	});


}


// ������������  

  public  void   loadtable(){
//�����ݿ��в�ѯ�����е�����  
	  ReaderTypeDao  rd=new ReaderTypeDao();
	  List<ReaderType> list=rd.queryAll();
	  //��list���ݷ��õ�һ��Vertor������
	  Vector<Vector<String>>  vdata=new Vector<Vector<String>>(); 
	  for(ReaderType rt:list){
		  
		  Vector<String>  v1=new Vector<String>();
		  //�������е�ÿһ�����󶼷��õ��µļ�����
		  v1.add(String.valueOf(rt.getId()));
		  v1.add(rt.getName());
		  vdata.add(v1);
	  }
	  
	  Vector<String> head=new Vector<String>();	  
	  head.add("���");
	  head.add("����");
	  
	  //����һ�����ģ��   
	  TableModel  tm=new DefaultTableModel(vdata, head);
	  
	  //��ģ���е��������õ������  
	  
	  jtable.setModel(tm);
	  
  }




}
