package model;

public class LikeBean {
	private int like_shopuid;
	private int like_communityuid; 
	private String like_user;
	private String like_signdate;
	public int getLike_shopuid() {
		return like_shopuid;
	}
	public void setLike_shopuid(int like_shopuid) {
		this.like_shopuid = like_shopuid;
	}
	public int getLike_communityuid() {
		return like_communityuid;
	}
	public void setLike_communityuid(int like_communityuid) {
		this.like_communityuid = like_communityuid;
	}
	public String getLike_user() {
		return like_user;
	}
	public void setLike_user(String like_user) {
		this.like_user = like_user;
	}
	public String getLike_signdate() {
		return like_signdate;
	}
	public void setLike_signdate(String like_signdate) {
		this.like_signdate = like_signdate;
	}
	
}
