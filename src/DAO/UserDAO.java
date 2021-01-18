package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.UserBean;

public class UserDAO{
	
	DAO dao = new DAO();
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	//회원가입메서드  회원가입 이닷--------------
	public void signup(UserBean bean, String signdate) {
		dao.getCon();
		try {
			
			String sql = "insert into user (userid,userpassword,username,usernickname,userphone,userage,usergender,useremail,useraddress,useraddress_1,useraddress_2,signdate) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getUserid());
			pstmt.setString(2, bean.getUserpassword());
			pstmt.setString(3, bean.getUsername());
			pstmt.setString(4, bean.getUsernickname());
			pstmt.setString(5, bean.getUserphone());
			pstmt.setString(6, bean.getUserage());
			pstmt.setString(7, bean.getUsergender());
			pstmt.setString(8, bean.getUseremail());
			pstmt.setString(9, bean.getUseraddress());
			pstmt.setString(10, bean.getUseraddress_1());
			pstmt.setString(11, bean.getUseraddress_2());
			pstmt.setString(12, signdate);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//로그인할 유저가 존재하는지 아닌지 찾는 메서드 흠 이상무..-----------
	public int userSearch(String userid, String userpassword) {
		dao.getCon();
		int answer = 0;
		try {
			String sql = "select * from user where userid=? and userpassword=?";
			
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpassword);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				answer = 1;
			}else {
				answer = 0;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dao.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return answer;
	}
	
	//진짜 유저로그인 하는 메서드
	public UserBean userLogin(String userid) {
		dao.getCon();
		UserBean bean = new UserBean();
		try {
			
			String sql = "select * from user where userid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setUserid(rs.getString("userid"));
				bean.setUsername(rs.getString("username"));
				bean.setUsernickname(rs.getString("usernickname"));
				bean.setUserlevel(rs.getString("userlevel"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dao.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
	
	//마이페이지에 유저 정보 뿌려주는 메서드
	public UserBean myPageShow(String session_id) {
		dao.getCon();
		UserBean bean = new UserBean();
		try {
			String sql = "select * from user where userid=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, session_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setUserid(rs.getString("userid"));
				bean.setUsername(rs.getString("username"));
				bean.setUsernickname(rs.getString("usernickname"));
				bean.setUseremail(rs.getString("useremail"));
				bean.setUserphone(rs.getString("userphone"));
				bean.setUseraddress(rs.getString("useraddress"));
				bean.setUseraddress_1(rs.getString("useraddress_1"));
				bean.setUseraddress_2(rs.getString("useraddress_2"));
				bean.setUserlevel(rs.getString("userlevel"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dao.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
	//유저 회원정보 수정 업데이트 메서드
	public void userUpdate(UserBean bean, String session_id) {
		dao.getCon();
		try {
			String sql = "update user set userid=?, username=?, usernickname=?, useremail=?, userphone=?, useraddress=?, useraddress_1=?, useraddress_2=? where userid=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getUserid());
			pstmt.setString(2, bean.getUsername());
			pstmt.setString(3, bean.getUsernickname());
			pstmt.setString(4, bean.getUseremail());
			pstmt.setString(5, bean.getUserphone());
			pstmt.setString(6, bean.getUseraddress());
			pstmt.setString(7, bean.getUseraddress_1());
			pstmt.setString(8, bean.getUseraddress_2());
			pstmt.setString(9, session_id);
			
			pstmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dao.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	//아이디 검색 할때 유저가 있는지 없는지 판별하는 메서드
	public int userIdSearch(String username, String useremail) {
		dao.getCon();
		int answer = 0;
		try {
			
			String sql = "select * from user where username=? and useremail=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, useremail);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				answer = 1;
			}else {
				answer = 0;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dao.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return answer;
	}
	// 유저 검색결과를 빈에 넣어주는 메서드
	public UserBean useridShow(String username, String useremail) {
		dao.getCon();
		UserBean bean = new UserBean();
		try {
			
			String sql = "select * from user where username=? and useremail=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, useremail);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setUserid(rs.getString("userid"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	// 유저 패스워드 검색에서 유저가 있는지 없는지 확인하는 메서드
	public int userPasswordSearch(String username, String userid, String useremail) {
		dao.getCon();
		int answer = 0;
		try {
			
			String sql = "select * from user where username=? and userid=? and useremail=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, userid);
			pstmt.setString(3, useremail);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				answer = 1;
			}else {
				answer = 0;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dao.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return answer;
	}
	
	public void passwordChange(String userid, String newPassword) {
		dao.getCon();
		try {
			
			String sql = "update user set userpassword=? where userid=?";
			
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

















