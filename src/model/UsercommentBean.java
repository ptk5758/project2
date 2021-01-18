package model;

public class UsercommentBean {
	private int commentuid;
	private int shopuid;
	private int communityuid;
	private String user;
	private String usercomment;
	private String signdate;
	private String img_1;
	private int fid;
	private char thread;
	private String padding;
	private int reply;
	
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public char getThread() {
		return thread;
	}
	public void setThread(char thread) {
		this.thread = thread;
	}
	public String getPadding() {
		return padding;
	}
	public void setPadding(String padding) {
		this.padding = padding;
	}
	public int getCommentuid() {
		return commentuid;
	}
	public void setCommentuid(int commentuid) {
		this.commentuid = commentuid;
	}
	public int getShopuid() {
		return shopuid;
	}
	public void setShopuid(int shopuid) {
		this.shopuid = shopuid;
	}
	public int getCommunityuid() {
		return communityuid;
	}
	public void setCommunityuid(int communityuid) {
		this.communityuid = communityuid;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUsercomment() {
		return usercomment;
	}
	public void setUsercomment(String usercomment) {
		this.usercomment = usercomment;
	}
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public String getImg_1() {
		return img_1;
	}
	public void setImg_1(String img_1) {
		this.img_1 = img_1;
	}
}
