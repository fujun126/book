package com.book.borrow.pojo;



import com.book.book.pojo.Bookinfo;
import com.book.reader.pojo.Reader;

public class BorrowBack {

	private int id;
	private Reader reader;
	private Bookinfo book;
	private String borrowTime;
	private String backTime;
	private String realTime;
	private int operator;
	private int ifback;
	private int timeType;
	private String startDate;
	private String endDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	public Bookinfo getBook() {
		return book;
	}
	public void setBook(Bookinfo book) {
		this.book = book;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	public String getBackTime() {
		return backTime;
	}
	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}
	public String getRealTime() {
		return realTime;
	}
	public void setRealTime(String realTime) {
		this.realTime = realTime;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public int getIfback() {
		return ifback;
	}
	public void setIfback(int ifback) {
		this.ifback = ifback;
	}
	public int getTimeType() {
		return timeType;
	}
	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
