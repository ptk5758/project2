package model;

public class MSGClientBean {
	private int msg_uid;
	private String send_user;
	private String recv_user;
	private String send_signdate;
	private String contents;
	private String img_1;
	private String active;
	private String active_2;
	private String read_check;
	
	public int getMsg_uid() {
		return msg_uid;
	}
	public void setMsg_uid(int msg_uid) {
		this.msg_uid = msg_uid;
	}
	public String getSend_user() {
		return send_user;
	}
	public void setSend_user(String send_user) {
		this.send_user = send_user;
	}
	public String getRecv_user() {
		return recv_user;
	}
	public void setRecv_user(String recv_user) {
		this.recv_user = recv_user;
	}
	public String getSend_signdate() {
		return send_signdate;
	}
	public void setSend_signdate(String send_signdate) {
		this.send_signdate = send_signdate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImg_1() {
		return img_1;
	}
	public void setImg_1(String img_1) {
		this.img_1 = img_1;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getActive_2() {
		return active_2;
	}
	public void setActive_2(String active_2) {
		this.active_2 = active_2;
	}
	public String getRead_check() {
		return read_check;
	}
	public void setRead_check(String read_check) {
		this.read_check = read_check;
	}
}
