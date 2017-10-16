package com.book.rankinglist.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.book.book.pojo.Bookinfo;
import com.book.borrow.dao.BorrowBackDao;
import com.book.borrow.pojo.BorrowBack;
import com.book.rankinglist.dao.RankingListDao;
import com.book.util.MyTable;



public class BookRankingListUI extends JFrame{
	private static final long serialVersionUID = -621181606924497520L;
	private JLabel jl = new JLabel("图书借阅排行", JLabel.CENTER);
	private JTable table = new MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	private JPanel jp = new JPanel();
	public BookRankingListUI(){
		this.setTitle("图书借阅排行信息");
		this.setSize(400, 360);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		setTableData();

	     table.getColumnModel().getColumn(0).setPreferredWidth(200);
	     table.getColumnModel().getColumn(1).setPreferredWidth(10);
		this.setVisible(true);
	}
	public void addComponent(){
		jl.setFont(new Font("楷体_GB2312",Font.BOLD,28));
		this.add(jl, BorderLayout.NORTH);
		
	
		head.add("图书名称");
		head.add("借阅次数");
		
		this.add(jsp);
		this.add(jp, BorderLayout.SOUTH);
	}
	public void addEvent(){}
	public void setTableData(){
		Vector<Vector<Object>> body = new Vector<Vector<Object>>();
		//将数据库中数据取出，放入到表格所需的集合中
		RankingListDao dao = new RankingListDao();
		List<Bookinfo> list = dao.bookList();
		//将List中的数据复制到表格所需的Vector中即可
		for (Bookinfo bb : list) {
			//创建小Vector对象，并把POJO对象中数据放入
			Vector<Object> v = new Vector<Object>();
		
			v.add(bb.getBookname());
			v.add(bb.getPage());
			
			
			//小V放入大V中
			body.add(v);
		}
		DefaultTableModel model = new DefaultTableModel(body, head);
		table.setModel(model);
	}
}
