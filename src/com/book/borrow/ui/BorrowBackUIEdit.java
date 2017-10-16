package com.book.borrow.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.book.book.dao.BookinfoDao;
import com.book.book.pojo.Bookinfo;
import com.book.borrow.dao.BorrowBackDao;
import com.book.borrow.pojo.BorrowBack;
import com.book.reader.dao.ReaderDao;
import com.book.reader.pojo.Reader;


public class BorrowBackUIEdit extends JFrame{

	private JLabel jl1 = new JLabel("��������");
	private JLabel jl2 = new JLabel("ͼ������");
	private JLabel jl3 = new JLabel("Ӧ��ʱ��");
	private Vector<Reader> data1 = new Vector<Reader>();
	private JComboBox<Reader> jc1 = new JComboBox<Reader>();
	private Vector<Bookinfo> data2 = new Vector<Bookinfo>();
	private JComboBox<Bookinfo> jc2 = new JComboBox<Bookinfo>();
	private JTextField jt = new JTextField(10);
	private JButton ok = new JButton("ȷ��");
	private JButton cancel = new JButton("ȡ��");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private BorrowBackUI ui;
	private int id;
	
	public BorrowBackUIEdit(BorrowBackUI ui, int id){
		this.ui = ui;
		this.id = id;
		this.setTitle("�޸�ͼ��軹��Ϣ");
		this.setSize(280, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		showData();
		
		this.setVisible(true);
	}
	public void addComponent(){
		this.setLayout(new GridLayout(4, 1));
		ReaderDao dao1 = new ReaderDao();
		data1.addAll(dao1.queryReader());
		jc1.setModel(new DefaultComboBoxModel<Reader>(data1));
		BookinfoDao dao2 = new BookinfoDao();
		data2.addAll(dao2.queryBookinfo());
		jc2.setModel(new DefaultComboBoxModel<Bookinfo>(data2));
		jp1.add(jl1);
		jp1.add(jc1);
		jp2.add(jl2);
		jp2.add(jc2);
		jp3.add(jl3);
		jp3.add(jt);
		jp4.add(ok);
		jp4.add(cancel);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
	}
	public void addEvent(){
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.��ȡ�û��������¼������ݣ�������У��(У��Ϊѡ������)
				Reader reader = (Reader) jc1.getSelectedItem();
				Bookinfo book = (Bookinfo) jc2.getSelectedItem();
				String backTime = jt.getText().trim();
				/**
				 * �ǿ�У��
				 */
				if(backTime.equals("")){
					JOptionPane.showMessageDialog(null, "Ӧ��ʱ�䲻��Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				//2.�����ݱ��浽POJO������
				BorrowBack bb = new BorrowBack();
				bb.setId(id);
				bb.setReader(reader);
				bb.setBook(book);
				bb.setBackTime(backTime);
				//3.ͨ����װ��Dao�����ݱ��浽���ݿ���
				BorrowBackDao dao = new BorrowBackDao();
				boolean flag = dao.editBorrowBack(bb);
				if(flag){
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
					//�ر���Ӵ���
					dispose();
					ui.setTableData();
				}else{
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showData();
			}
		});
	}
	public void showData(){
		BorrowBackDao dao = new BorrowBackDao();
		BorrowBack bb = dao.getBorrowBack(id);
		jc1.setSelectedItem(bb.getReader());
		jc2.setSelectedItem(bb.getBook());
		jt.setText(bb.getBackTime());
	}
}
