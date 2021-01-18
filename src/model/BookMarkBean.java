package model;

public class BookMarkBean {
	private int bm_uid;
	private int book_uid;
	private String bm_subject;
	private String bm_signdate;
	private String bm_user;
	 
	public int getBm_uid() {
		return bm_uid;
	}
	public void setBm_uid(int bm_uid) {
		this.bm_uid = bm_uid;
	}
	public int getBook_uid() {
		return book_uid;
	}
	public void setBook_uid(int book_uid) {
		this.book_uid = book_uid;
	}
	public String getBm_subject() {
		return bm_subject;
	}
	public void setBm_subject(String bm_subject) {
		this.bm_subject = bm_subject;
	}
	public String getBm_signdate() {
		return bm_signdate;
	}
	public void setBm_signdate(String bm_signdate) {
		this.bm_signdate = bm_signdate;
	}
	public String getBm_user() {
		return bm_user;
	}
	public void setBm_user(String bm_user) {
		this.bm_user = bm_user;
	}
}
