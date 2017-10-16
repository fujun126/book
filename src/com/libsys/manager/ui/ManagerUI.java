package com.libsys.manager.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.libsys.manager.dao.ManagerDao;
import com.libsys.manager.pojo.Manager;


public class ManagerUI extends JFrame{

	private JLabel jl = new JLabel("�û���Ϣ", JLabel.CENTER);
	private JTable table = new JTable();//MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	private JPanel jp = new JPanel();
	private JButton save = new JButton("����");
	private JButton edit = new JButton("�޸�");
	private JButton del = new JButton("ɾ��");
	
	public ManagerUI(){
		this.setTitle("��ѯ�û���Ϣ");
		this.setSize(460, 360);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		setTableData();
		
		tableOpaque();
		 
	        //���ÿɼ�
		this.setVisible(true);
	}
	public void addComponent(){
		jl.setFont(new Font("����_GB2312",Font.BOLD,28));
		this.add(jl, BorderLayout.NORTH);
		
		head.add("�û����");
		head.add("�û�����");
		head.add("����");
		head.add("Ȩ��");
		this.add(jsp);
		
		jp.add(save);
		jp.add(edit);
		jp.add(del);
		this.add(jp, BorderLayout.SOUTH);
	}
	public void addEvent(){
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerUISave(ManagerUI.this);
			}
		});
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(null, "��ѡ��һ��Ҫ�޸ĵ�����", "������ʾ", JOptionPane.WARNING_MESSAGE);
				}else{
					int id = (Integer)table.getValueAt(row, 0);
					new ManagerUIEdit(ManagerUI.this, id);
				}
			}
		});
		del.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(null, "��ѡ��һ��Ҫɾ��������", "������ʾ", JOptionPane.WARNING_MESSAGE);
				}else{
					int i = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ������������?", "ɾ����ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(i == JOptionPane.YES_OPTION){
						//�ӱ����л�ȡָ������ָ���е�����
						int id = (Integer)table.getValueAt(row, 0);
						ManagerDao dao = new ManagerDao();
						boolean flag = dao.delManager(id);
						if(flag){
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
							setTableData();
						}else{
							JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}
	public void setTableData(){
		Vector<Vector<Object>> body = new Vector<Vector<Object>>();
		//�����ݿ�������ȡ�������뵽��������ļ�����
		ManagerDao dao = new ManagerDao();
		List<Manager> list = dao.queryManager();
		//��List�е����ݸ��Ƶ����������Vector�м���
		
		
		for (int i=0;i<list.size();i++) {
			Manager manager=list.get(i);
			//����СVector���󣬲���POJO���������ݷ���
			Vector<Object> v = new Vector<Object>();
			v.add(manager.getId());
			v.add(manager.getUsername());
			v.add(manager.getPwd());
			v.add(manager.getRole());
			
			//СV�����V��
			body.add(v);
			
		}
		
	
		DefaultTableModel model = new DefaultTableModel(body, head);
		
		table.setModel(model);
	}
	
	
	public  void   tableOpaque(){
		    String path = "file/user.jpg";  
	        // ����ͼƬ  
	        ImageIcon background = new ImageIcon(path);  
	        // �ѱ���ͼƬ��ʾ��һ����ǩ����  
	        JLabel label = new JLabel(background);  
	        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
	        label.setBounds(0, 0, this.getWidth(), this.getHeight());  
	        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
	        JPanel imagePanel = (JPanel) this.getContentPane();  
	        imagePanel.setOpaque(false);  
	        // �ѱ���ͼƬ���ӵ��ֲ㴰�����ײ���Ϊ����  
	        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  
	        
	        
	      
	        jsp.setOpaque(false);
	        jsp.getViewport().setOpaque(false); 
	        
	        jp.setOpaque(false);
	        table.setOpaque(false);
		
		
	        /*
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
		renderer.setOpaque(false);//render��Ԫ�������  
		//���������������У�������Ⱦ������Ϊrenderer  
		table.setDefaultRenderer(Object.class, renderer);  
		*/
		
		
	}
	
}