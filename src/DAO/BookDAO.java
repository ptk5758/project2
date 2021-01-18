package DAO;

import java.sql.*;
import java.util.ArrayList;

import model.BookBean;
import model.EpisodeBean;

public class BookDAO {
	Statement stmt;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	DAO dao = new DAO();
	
	public ArrayList<BookBean> getContentsList(String title) {
		dao.getCon();
		ArrayList<BookBean> v = new ArrayList<BookBean>();
		try {
			sql = "select * from bookcontents where book_title=? order by book_signdate desc";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookBean bean = new BookBean();
				bean.setBook_uid(rs.getInt("book_uid"));
				bean.setBook_title(rs.getString("book_title"));
				bean.setBook_genre(rs.getString("book_genre"));
				bean.setBook_subject(rs.getString("book_subject"));
				bean.setBook_contents(rs.getString("book_contents"));
				bean.setBook_writer(rs.getString("book_writer"));
				bean.setBook_ref(rs.getInt("book_ref"));
				bean.setBook_img1(rs.getString("book_img1"));
				bean.setBook_img2(rs.getString("book_img2"));
				bean.setBook_img3(rs.getString("book_img3"));
				bean.setBook_thumb(rs.getString("book_thumb"));
				bean.setBook_signdate(rs.getString("book_signdate"));
				bean.setBook_writer(rs.getString("book_writer"));
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return v;
	}
	
	public void book_posting(BookBean bean) {
		dao.getCon();
		try {
			sql = "insert into bookcontents (book_title, book_genre, book_subject,"
					+ "book_contents, book_writer, book_signdate,"
					+ "book_img1, book_thumb) values"
					+ "(?,?,?,?,?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getBook_title());
			pstmt.setString(2, bean.getBook_genre());
			pstmt.setString(3, bean.getBook_subject());
			pstmt.setString(4, bean.getBook_contents());
			pstmt.setString(5, bean.getBook_writer());
			pstmt.setString(6, bean.getBook_signdate());
			pstmt.setString(7, bean.getBook_img1());
			pstmt.setString(8, bean.getBook_thumb());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refcnt(int book_uid) {
		dao.getCon();
		try {
			sql = "update bookcontents set book_ref=book_ref+1 where book_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BookBean book_postshow(int book_uid) {
		dao.getCon();
		BookBean bean = new BookBean();
		try {
			sql = "select * from bookcontents where book_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bean.setBook_title(rs.getString("book_title"));
				bean.setBook_genre(rs.getString("book_genre"));
				bean.setBook_subject(rs.getString("book_subject"));
				bean.setBook_contents(rs.getString("book_contents"));
				bean.setBook_writer(rs.getString("book_writer"));
				bean.setBook_signdate(rs.getString("book_signdate"));
				bean.setBook_ref(rs.getInt("book_ref"));
				bean.setBook_img1(rs.getString("book_img1"));
				bean.setBook_img2(rs.getString("book_img2"));
				bean.setBook_img3(rs.getString("book_img3"));
				bean.setBook_img3(rs.getString("book_thumb"));
				bean.setBook_uid(rs.getInt("book_uid"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return bean;
	}
	
	public BookBean updateinfo(int book_uid) {
		dao.getCon();
		BookBean bean = new BookBean();
		try {
			sql = "select * from bookcontents where book_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bean.setBook_title(rs.getString("book_title"));
				bean.setBook_genre(rs.getString("book_genre"));
				bean.setBook_subject(rs.getString("book_subject"));
				bean.setBook_contents(rs.getString("book_contents"));
				bean.setBook_writer(rs.getString("book_writer"));
				bean.setBook_signdate(rs.getString("book_signdate"));
				bean.setBook_ref(rs.getInt("book_ref"));
				bean.setBook_img1(rs.getString("book_img1"));
				bean.setBook_img2(rs.getString("book_img2"));
				bean.setBook_img3(rs.getString("book_img3"));
				bean.setBook_img3(rs.getString("book_thumb"));
				bean.setBook_uid(rs.getInt("book_uid"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return bean;
	}
	
	public void book_update(BookBean bean) {
		dao.getCon();
		try {
			sql = "update bookcontents set book_writer=?,book_title=?,"
					+ "book_genre=?,book_subject=?,book_contents=?,"
					+ "book_img1=?,book_img2=?,book_img3=?,"
					+ "book_thumb=? where book_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getBook_writer());
			pstmt.setString(2, bean.getBook_title());
			pstmt.setString(3, bean.getBook_genre());
			pstmt.setString(4, bean.getBook_subject());
			pstmt.setString(5, bean.getBook_contents());
			pstmt.setString(6, bean.getBook_img1());
			pstmt.setString(7, bean.getBook_img2());
			pstmt.setString(8, bean.getBook_img3());
			pstmt.setString(9, bean.getBook_thumb());
			pstmt.setInt(10, bean.getBook_uid());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void book_delete(int book_uid) {
		dao.getCon();
		try {
			sql = "delete from bookcontents where book_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void itemLike(int book_uid, String session_id, String like_signdate) {
		dao.getCon();
		try {
			String sql = "insert into likestack (like_bookuid,like_user,like_signdate) values (?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			pstmt.setString(2, session_id);
			pstmt.setString(3, like_signdate);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int likeStack(int book_uid) {
		dao.getCon();
		int likestack = 0;
		try {
			String sql = "select count(*) from bookcontents JOIN likestack ON bookcontents.book_uid = likestack.like_bookuid and book_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				likestack = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}return likestack;
	}
		
	public int chackLike(int book_uid, String session_id) {
		dao.getCon();
		int answer = 0;
		try {
			String sql = "select * from likestack where like_bookuid=? and like_user=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			pstmt.setString(2, session_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				answer = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}return answer;
	}
		
	public void itemLikeBack(int book_uid, String session_id) {
		dao.getCon();
		try {
			String sql = "delete from likestack where like_bookuid=? and like_user=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			pstmt.setString(2, session_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BookBean addbean(int book_uid) {
		dao.getCon();
		BookBean bean = new BookBean();
		try {
			sql = "select * from bookcontents where book_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bean.setBook_uid(rs.getInt("book_uid"));
				bean.setBook_subject(rs.getString("book_subject"));
				bean.setBook_writer(rs.getString("book_writer"));
				bean.setBook_signdate(rs.getString("book_signdate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return bean;
	}
}		
