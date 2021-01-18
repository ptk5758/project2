package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MSGClientBean;

public class MSGDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	DAO dao = new DAO();
	
	public int idOk(String recv) {
		dao.getCon();
		int answer=0;
		try {
			sql = "select * from user where userid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, recv);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				answer = 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return answer;
	}
	
	
	public void send(String send, String recv, String contents, String img_1, String signdate) {
		dao.getCon();
		try {
			sql = "insert into client(send_user,recv_user,contents,img_1,send_signdate) values(?,?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, send);
			pstmt.setString(2, recv);
			pstmt.setString(3, contents);
			pstmt.setString(4, img_1);
			pstmt.setString(5, signdate);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatesend(MSGClientBean bean) {
		dao.getCon();
		try {
			sql = "update client set send_user=?,recv_user=?,contents=?,img_1=?,send_signdate=? where msg_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getSend_user());
			pstmt.setString(2, bean.getRecv_user());
			pstmt.setString(3, bean.getContents());
			pstmt.setString(4, bean.getImg_1());
			pstmt.setString(5, bean.getSend_signdate());
			pstmt.setInt(6, bean.getMsg_uid());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void msgDelete(int msg_uid) {
		dao.getCon();
		try {
			sql = "update client set active=0 where msg_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, msg_uid);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MSGClientBean> showMsgList(String recv){
		dao.getCon();
		ArrayList<MSGClientBean> v = new ArrayList<MSGClientBean>();
		try {
			sql = "select * from client where recv_user='"+recv+"'";
			stmt = dao.conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MSGClientBean bean = new MSGClientBean();
				bean.setMsg_uid(rs.getInt("msg_uid"));
				bean.setSend_user(rs.getString("send_user"));
				bean.setContents(rs.getString("contents"));
				bean.setImg_1(rs.getString("img_1"));
				bean.setSend_signdate(rs.getString("send_signdate"));
				bean.setActive(rs.getString("active"));
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return v;
	}
	
	public void readcheck(int msg_uid) {
		dao.getCon();
		try {
			sql = "update client set read_check=read_check+1 where msg_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, msg_uid);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MSGClientBean msgPostShow(int msg_uid) {
		dao.getCon();
		MSGClientBean bean = new MSGClientBean();
		try {
			sql = "select * from client where msg_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, msg_uid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 bean.setMsg_uid(rs.getInt("msg_uid"));
				 bean.setSend_user(rs.getString("send_user"));
				 bean.setRecv_user(rs.getString("recv_user"));
				 bean.setContents(rs.getString("contents"));
				 bean.setSend_signdate(rs.getString("send_signdate"));
				 bean.setImg_1(rs.getString("img_1"));
				 bean.setActive(rs.getString("active"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}return bean;
	}
	
	public void msgsendDelete(int msg_uid) {
		dao.getCon();
		try {
			sql = "update client set active_2=0 where msg_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, msg_uid);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MSGClientBean> showSendList(String send){
		dao.getCon();
		ArrayList<MSGClientBean> v = new ArrayList<MSGClientBean>();
		try {
			sql = "select * from client where send_user='"+send+"'";
			stmt = dao.conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MSGClientBean bean = new MSGClientBean();
				bean.setMsg_uid(rs.getInt("msg_uid"));
				bean.setRecv_user(rs.getString("send_user"));
				bean.setRecv_user(rs.getString("recv_user"));
				bean.setContents(rs.getString("contents"));
				bean.setImg_1(rs.getString("img_1"));
				bean.setSend_signdate(rs.getString("send_signdate"));
				bean.setActive_2(rs.getString("active_2"));
				bean.setRead_check(rs.getString("read_check"));
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return v;
	}
	
	public MSGClientBean msgInfo(int msg_uid) {
		dao.getCon();
		MSGClientBean bean = new MSGClientBean();
		try {
			sql = "select * from client where msg_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, msg_uid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 bean.setMsg_uid(rs.getInt("msg_uid"));
				 bean.setRecv_user(rs.getString("Recv_user"));
				 bean.setContents(rs.getString("contents"));
				 bean.setImg_1(rs.getString("img_1"));
				 bean.setActive(rs.getString("active"));
				 bean.setActive_2(rs.getString("active_2"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}return bean;
	}
}
