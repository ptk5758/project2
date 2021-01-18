package model;

public class AttendBean {
	private int attend_uid;
	private String attend_user_id;
	private String attend_signdate;
	private String attend_comment;
	private int attend_count;
	private int attend_continue;
	private String signdate_option;
	private String user_nickname;
	
	
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getSigndate_option() {
		return signdate_option;
	}
	public void setSigndate_option(String signdate_option) {
		this.signdate_option = signdate_option;
	}
	public int getAttend_uid() {
		return attend_uid;
	}
	public void setAttend_uid(int attend_uid) {
		this.attend_uid = attend_uid;
	}
	public String getAttend_user_id() {
		return attend_user_id;
	}
	public void setAttend_user_id(String attend_user_id) {
		this.attend_user_id = attend_user_id;
	}
	public String getAttend_signdate() {
		return attend_signdate;
	}
	public void setAttend_signdate(String attend_signdate) {
		this.attend_signdate = attend_signdate;
	}
	public String getAttend_comment() {
		return attend_comment;
	}
	public void setAttend_comment(String attend_comment) {
		this.attend_comment = attend_comment;
	}
	public int getAttend_count() {
		return attend_count;
	}
	public void setAttend_count(int attend_count) {
		this.attend_count = attend_count;
	}
	public int getAttend_continue() {
		return attend_continue;
	}
	public void setAttend_continue(int attend_continue) {
		this.attend_continue = attend_continue;
	}
}
