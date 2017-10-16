package com.book.query.ui;

import java.awt.BorderLayout;
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

import com.book.book.dao.BookTypeDao;
import com.book.book.dao.BookinfoDao;
import com.book.book.pojo.BookType;
import com.book.book.pojo.Bookinfo;
import com.book.util.MyTable;



public class BookinfoUIQuery extends JFrame{

	private JPanel jp = new JPanel();
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JLabel jl1 = new JLabel("图书名称：");
	private JLabel jl2 = new JLabel("图书类型：");
	private JLabel jl3 = new JLabel("作        者：");
	private JLabel jl4 = new JLabel("出  版  社：");
	private JTextField jt1 = new JTextField(10);
	private Vector<BookType> data = new Vector<BookType>();
	private JComboBox<BookType> jc2 = new JComboBox<BookType>();
	private JTextField jt3 = new JTextField(10);
	private JTextField jt4 = new JTextField(10);
	private JButton query = new JButton("查询");
	private JTable table = new MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	
	public BookinfoUIQuery(){
		this.setTitle("图书档案查询");
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
		jp1.add(jt1);
		jp1.add(jl2);
		BookTypeDao dao = new BookTypeDao();
		data.add(null);
		data.addAll(dao.queryBookType());
		jc2.setModel(new DefaultComboBoxModel<BookType>(data));
		jp1.add(jc2);
		jp2.add(jl3);
		jp2.add(jt3);
		jp2.add(jl4);
		jp2.add(jt4);
		jp2.add(query);
		jp.add(jp1);
		jp.add(jp2);
		this.add(jp, BorderLayout.NORTH);
		
		head.add("图书编号");
		head.add("图书名称");
		head.add("图书类型");
		head.add("作者");
		head.add("出版社");
		head.add("价格");
		head.add("页数");
		head.add("录入时间");
		head.add("操作员");
		this.add(jsp);
	}
	public void addEvent(){
		query.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookname = jt1.getText().trim();
				BookType bt = (BookType) jc2.getSelectedItem();
				String author = jt3.getText().trim();
				String ISBN = jt4.getText().trim();
				Bookinfo book = new Bookinfo();
				book.setBookname(bookname);
				book.setBt(bt);
				book.setAuthor(author);
				book.setISBN(ISBN);
				
				Vector<Vector<Object>> body = new Vector<Vector<Object>>();
				BookinfoDao dao = new BookinfoDao();
				List<Bookinfo> list = dao.queryBookinfo(book);
				for (Bookinfo Bookinfo : list) {
					Vector<Object> v = new Vector<Object>();
					v.add(Bookinfo.getId());
					v.add(Bookinfo.getBookname());
					v.add(Bookinfo.getBt().getTypename());
					v.add(Bookinfo.getAuthor());
					v.add(Bookinfo.getISBN());
					v.add(Bookinfo.getPrice());
					v.add(Bookinfo.getPage());
					v.add(Bookinfo.getInTime());
					v.add(Bookinfo.getOperator());
					body.add(v);
				}
				DefaultTableModel model = new DefaultTableModel(body, head);
				table.setModel(model);
			}
		});
	}
	public static void main(String[] args) {
		new BookinfoUIQuery();
	}
}
