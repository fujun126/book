package com.book.query.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.book.book.pojo.Bookinfo;
import com.book.borrow.dao.BorrowBackDao;
import com.book.borrow.pojo.BorrowBack;
import com.book.reader.pojo.Reader;
import com.book.util.ConstantsUtil;
import com.book.util.MyCalendar;
import com.book.util.MyTable;

public class BookBorrowBackUIQuery extends JFrame{

	private JPanel jp = new JPanel();
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JLabel jl1 = new JLabel("查询方式：");
	private JLabel jl2 = new JLabel("读者姓名：");
	private JLabel jl3 = new JLabel("图书名称：");
	private JComboBox<String[]> jc1 = new JComboBox<String[]>();
	private JTextField jt2 = new JTextField(10);
	private JTextField jt3 = new JTextField(10);
	private JComboBox<String[]> jc4 = new JComboBox<String[]>();
	private JTextField jt5 = new JTextField(10);
	private JTextField jt6 = new JTextField(10);
	private JButton query = new JButton("查询");
	private JButton clear = new JButton("清空");
	private JTable table = new MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	
	public BookBorrowBackUIQuery(){
		this.setTitle("图书借阅查询");
		this.setSize(860, 360);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		
		this.setVisible(true);
	}
	public void addComponent(){
		jp.setLayout(new GridLayout(2, 1));
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp1.add(jl1);
		jc1.setModel(new DefaultComboBoxModel(ConstantsUtil.ifback));
		jp1.add(jc1);
		jp1.add(jl2);
		jp1.add(jt2);
		jp1.add(jl3);
		jp1.add(jt3);
		jc4.setModel(new DefaultComboBoxModel(ConstantsUtil.timeType));
		jp2.add(jc4);
		MyCalendar.getInstance().register(jt5);
		jt5.setEditable(false);
		jt5.setBackground(Color.WHITE);
		jp2.add(jt5);
		MyCalendar.getInstance().register(jt6);
		jt6.setEditable(false);
		jt6.setBackground(Color.WHITE);
		jp2.add(jt6);
		jp2.add(query);
		jp2.add(clear);
		jp.add(jp1);
		jp.add(jp2);
		this.add(jp, BorderLayout.NORTH);
		
		head.add("借还编号");
		head.add("读者姓名");
		head.add("图书名称");
		head.add("借阅时间");
		head.add("应还时间");
		head.add("归还时间");
		head.add("操作员");
		head.add("是否归还");
		this.add(jsp);
	}
	public void addEvent(){
		clear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				jc1.setSelectedIndex(0);
				jt2.setText("");
				jt3.setText("");
				jc4.setSelectedIndex(0);
				jt5.setText("");
				jt6.setText("");
			}
		});
		query.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int ifback = jc1.getSelectedIndex();
				String readername = jt2.getText().trim();
				String bookname = jt3.getText().trim();
				int timeType = jc4.getSelectedIndex();
				String startDate = jt5.getText().trim();
				String endDate = jt6.getText().trim();
				BorrowBack bb = new BorrowBack();
				Reader reader = new Reader();
				reader.setName(readername);
				bb.setReader(reader);
				Bookinfo book = new Bookinfo();
				book.setBookname(bookname);
				bb.setBook(book);
				bb.setIfback(ifback);
				bb.setTimeType(timeType);
				bb.setStartDate(startDate);
				bb.setEndDate(endDate);
				
				Vector<Vector<Object>> body = new Vector<Vector<Object>>();
				BorrowBackDao dao = new BorrowBackDao();
				List<BorrowBack> list = dao.queryBorrowBack(bb);
				for (BorrowBack bean : list) {
					Vector<Object> v = new Vector<Object>();
					v.add(bean.getId());
					v.add(bean.getReader().getName());
					v.add(bean.getBook().getBookname());
					v.add(bean.getBorrowTime());
					v.add(bean.getBackTime());
					v.add(bean.getRealTime());
					v.add(bean.getOperator());
					v.add(ConstantsUtil.ifback[bean.getIfback()]);
					body.add(v);
				}
				DefaultTableModel model = new DefaultTableModel(body, head);
				table.setModel(model);
			}
		});
	}
}
