package DAO;

import java.sql.*;
import java.util.ArrayList;

import model.BookMarkBean;

public class BookMarkDAO {
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	String sql;
	DAO dao = new DAO();
	
	public ArrayList<BookMarkBean> showfavorit(String session_name) {
		dao.getCon();
		ArrayList<BookMarkBean> v = new ArrayList<BookMarkBean>();
		try {
			sql = "select * from bookmark where bm_user=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, session_name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookMarkBean bean = new BookMarkBean();
				bean.setBook_uid(rs.getInt("book_uid"));
				bean.setBm_subject(rs.getString("bm_subject"));
				bean.setBm_signdate(rs.getString("bm_signdate"));
				bean.setBm_user(rs.getString("bm_user"));
				bean.setBm_uid(rs.getInt("bm_uid"));
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return v;
	}
	
	public ArrayList<BookMarkBean> headerfavorit(String session_name) {
		dao.getCon();
		ArrayList<BookMarkBean> v = new ArrayList<BookMarkBean>();
		try {
			sql = "select * from bookmark where bm_user=? order by bm_signdate desc limit 5";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, session_name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookMarkBean bean = new BookMarkBean();
				bean.setBook_uid(rs.getInt("book_uid"));
				bean.setBm_subject(rs.getString("bm_subject"));
				bean.setBm_signdate(rs.getString("bm_signdate"));
				bean.setBm_user(rs.getString("bm_user"));
				bean.setBm_uid(rs.getInt("bm_uid"));
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return v;
	}
	
	
	public int addfavorite(int book_uid, String session_name) {
		dao.getCon();
		int check=0;
		try {
			sql = "select * from bookmark where book_uid=? and bm_user=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			pstmt.setString(2, session_name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}return check;
	}
	
	public void bminsert(BookMarkBean bean) {
		dao.getCon();
		try {
			sql = "insert into bookmark (book_uid, bm_subject, bm_user, bm_signdate) values (?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getBook_uid());
			pstmt.setString(2, bean.getBm_subject());
			pstmt.setString(3, bean.getBm_user());
			pstmt.setString(4, bean.getBm_signdate());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deletebookmark(int book_uid, String session_name) {
		dao.getCon();
		try {
			sql = "delete from bookmark where book_uid=? and bm_user=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			pstmt.setString(2, session_name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
