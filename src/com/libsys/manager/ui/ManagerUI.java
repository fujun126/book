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

	private JLabel jl = new JLabel("用户信息", JLabel.CENTER);
	private JTable table = new JTable();//MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	private JPanel jp = new JPanel();
	private JButton save = new JButton("新增");
	private JButton edit = new JButton("修改");
	private JButton del = new JButton("删除");
	
	public ManagerUI(){
		this.setTitle("查询用户信息");
		this.setSize(460, 360);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		setTableData();
		
		tableOpaque();
		 
	        //设置可见
		this.setVisible(true);
	}
	public void addComponent(){
		jl.setFont(new Font("楷体_GB2312",Font.BOLD,28));
		this.add(jl, BorderLayout.NORTH);
		
		head.add("用户编号");
		head.add("用户名称");
		head.add("密码");
		head.add("权限");
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
					JOptionPane.showMessageDialog(null, "请选择一条要修改的数据", "警告提示", JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "请选择一条要删除的数据", "警告提示", JOptionPane.WARNING_MESSAGE);
				}else{
					int i = JOptionPane.showConfirmDialog(null, "你确定要删除该条数据吗?", "删除提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(i == JOptionPane.YES_OPTION){
						//从表格中获取指定行与指定列的数据
						int id = (Integer)table.getValueAt(row, 0);
						ManagerDao dao = new ManagerDao();
						boolean flag = dao.delManager(id);
						if(flag){
							JOptionPane.showMessageDialog(null, "删除成功！", "信息提示", JOptionPane.INFORMATION_MESSAGE);
							setTableData();
						}else{
							JOptionPane.showMessageDialog(null, "删除失败！", "错误提示", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}
	public void setTableData(){
		Vector<Vector<Object>> body = new Vector<Vector<Object>>();
		//将数据库中数据取出，放入到表格所需的集合中
		ManagerDao dao = new ManagerDao();
		List<Manager> list = dao.queryManager();
		//将List中的数据复制到表格所需的Vector中即可
		
		
		for (int i=0;i<list.size();i++) {
			Manager manager=list.get(i);
			//创建小Vector对象，并把POJO对象中数据放入
			Vector<Object> v = new Vector<Object>();
			v.add(manager.getId());
			v.add(manager.getUsername());
			v.add(manager.getPwd());
			v.add(manager.getRole());
			
			//小V放入大V中
			body.add(v);
			
		}
		
	
		DefaultTableModel model = new DefaultTableModel(body, head);
		
		table.setModel(model);
	}
	
	
	public  void   tableOpaque(){
		    String path = "file/user.jpg";  
	        // 背景图片  
	        ImageIcon background = new ImageIcon(path);  
	        // 把背景图片显示在一个标签里面  
	        JLabel label = new JLabel(background);  
	        // 把标签的大小位置设置为图片刚好填充整个面板  
	        label.setBounds(0, 0, this.getWidth(), this.getHeight());  
	        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
	        JPanel imagePanel = (JPanel) this.getContentPane();  
	        imagePanel.setOpaque(false);  
	        // 把背景图片添加到分层窗格的最底层作为背景  
	        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  
	        
	        
	      
	        jsp.setOpaque(false);
	        jsp.getViewport().setOpaque(false); 
	        
	        jp.setOpaque(false);
	        table.setOpaque(false);
		
		
	        /*
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
		renderer.setOpaque(false);//render单元格的属性  
		//遍历表格中所有列，将其渲染器设置为renderer  
		table.setDefaultRenderer(Object.class, renderer);  
		*/
		
		
	}
	
}
