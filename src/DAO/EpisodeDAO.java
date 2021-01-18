package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import model.EpisodeBean;

public class EpisodeDAO {
	Statement stmt;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	DAO dao = new DAO();
	
	public ArrayList<EpisodeBean> getEpisodeList(int book_uid) {
		dao.getCon();
		ArrayList<EpisodeBean> v = new ArrayList<EpisodeBean>();
		try {
			sql = "select * from bookepisode where book_uid=? order by ep_signdate desc";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EpisodeBean bean = new EpisodeBean();
				bean.setBook_uid(rs.getInt("book_uid"));
				bean.setEp_uid(rs.getInt("ep_uid"));
				bean.setEp_subject(rs.getString("ep_subject"));
				bean.setEp_contents(rs.getString("ep_contents"));
				bean.setEp_img1(rs.getString("ep_img1"));
				bean.setEp_img2(rs.getString("ep_img2"));
				bean.setEp_img3(rs.getString("ep_img3"));
				bean.setEp_signdate(rs.getString("ep_signdate"));
				bean.setEp_totalcount(rs.getInt("ep_totalcount"));
				bean.setEp_ref(rs.getInt("ep_ref"));
				bean.setEp_writer(rs.getString("ep_writer"));
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return v;
	}
	
	public void episode_plus() {
		dao.getCon();
		try {
			sql = "update bookepisode set ep_episode=ep_episode+1 order by ep_signdate desc";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void book_writing(EpisodeBean bean) {
		dao.getCon();
		try {
			sql = "insert into bookepisode(ep_subject, ep_contents, ep_img1, ep_img2, ep_img3, ep_signdate, book_uid, ep_writer) values(?,?,?,?,?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getEp_subject());
			pstmt.setString(2, bean.getEp_contents());
			pstmt.setString(3, bean.getEp_img1());
			pstmt.setString(4, bean.getEp_img2());
			pstmt.setString(5, bean.getEp_img3());
			pstmt.setString(6, bean.getEp_signdate());
			pstmt.setInt(7, bean.getBook_uid());
			pstmt.setString(8, bean.getEp_writer());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refcnt(int ep_uid) {
		dao.getCon();
		try {
			sql = "update bookepisode set ep_ref=ep_ref+1 where ep_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, ep_uid);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public EpisodeBean story_view(int ep_uid) {
		dao.getCon();
		EpisodeBean bean = new EpisodeBean();
		try {
			sql = "select * from bookepisode where ep_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, ep_uid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bean.setEp_uid(rs.getInt("ep_uid"));
				bean.setEp_subject(rs.getString("ep_subject"));
				bean.setEp_contents(rs.getString("ep_contents"));
				bean.setEp_img1(rs.getString("ep_img1"));
				bean.setEp_img2(rs.getString("ep_img2"));
				bean.setEp_img3(rs.getString("ep_img3"));
				bean.setEp_signdate(rs.getString("ep_signdate"));
				bean.setEp_totalcount(rs.getInt("ep_totalcount"));
				bean.setEp_ref(rs.getInt("ep_ref"));
				bean.setEp_writer(rs.getString("ep_writer"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}return bean;
	}
	
	public EpisodeBean epupdateinfo(int ep_uid) {
		dao.getCon();
		EpisodeBean bean = new EpisodeBean();
		try {
			sql = "select * from bookepisode where ep_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, ep_uid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bean.setEp_uid(rs.getInt("ep_uid"));
				bean.setEp_subject(rs.getString("ep_subject"));
				bean.setEp_contents(rs.getString("ep_contents"));
				bean.setEp_img1(rs.getString("ep_img1"));
				bean.setEp_img2(rs.getString("ep_img2"));
				bean.setEp_img3(rs.getString("ep_img3"));
				bean.setEp_signdate(rs.getString("ep_signdate"));
				bean.setEp_totalcount(rs.getInt("ep_totalcount"));
				bean.setEp_ref(rs.getInt("ep_ref"));
				bean.setEp_writer(rs.getString("ep_writer"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return bean;
	}
	
	public void episode_update(EpisodeBean bean) {
		dao.getCon();
		try {
			sql = "update bookepisode set ep_subject=?,ep_contents=?,"
					+ "ep_img1=?, ep_img2=?, ep_img3=? where ep_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getEp_subject());
			pstmt.setString(2, bean.getEp_contents());
			pstmt.setString(3, bean.getEp_img1());
			pstmt.setString(4, bean.getEp_img2());
			pstmt.setString(5, bean.getEp_img3());
			pstmt.setInt(6, bean.getEp_uid());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void episode_plus_delete(int book_uid) {
		dao.getCon();
		try {
			sql = "delete from bookepisode where book_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, book_uid);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void episode_delete(int ep_uid) {
		dao.getCon();
		try {
			sql = "delete from bookepisode where ep_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, ep_uid);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
