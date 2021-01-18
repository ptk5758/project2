package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.CommunityBean;

public class CommunityDao {
	DAO dao = new DAO();
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	String sql;
	
	public ArrayList<CommunityBean> showlist(String title) {
		dao.getCon();
		ArrayList<CommunityBean> v = new ArrayList<CommunityBean>();
		try {
			sql = "select * from community where title=? order by signdate desc";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommunityBean bean = new CommunityBean();
				bean.setCommunityuid(rs.getInt("communityuid"));
				bean.setTitle(rs.getString("title"));
				bean.setSubject(rs.getString("subject"));
				bean.setUser(rs.getString("user"));
				bean.setLike_count(rs.getInt("like_count"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setRef(rs.getInt("ref"));
				
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return v;
		
	}
	
	public ArrayList<CommunityBean> search_listshow(String title, String search_title, String search_contents){
		dao.getCon();
		ArrayList<CommunityBean> v = new ArrayList<CommunityBean>();
		try {
			sql = "select * from community where title='"+title+"' and "+search_title+" like '%"+search_contents+"%'";
			stmt = dao.conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				CommunityBean bean = new CommunityBean();
				bean.setCommunityuid(rs.getInt("communityuid"));
				bean.setTitle(rs.getString("title"));
				bean.setSubject(rs.getString("subject"));
				bean.setUser(rs.getString("user"));
				bean.setLike_count(rs.getInt("like_count"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setRef(rs.getInt("ref"));
				
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return v;	
	}
	
	
	
	
	
	//넘버링
	public int allcount(String title) {
		dao.getCon();
		int count = 0;
		try {
			sql = "select count(*) from community where title=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return count;
	}
	
	public void posting(CommunityBean bean) {
		dao.getCon();
		try {
			sql = "insert into community(title,subject,secret,contents,user,signdate,file_1,file_2,file_3,file_s) values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getSubject());
			pstmt.setString(3, bean.getSecret());
			pstmt.setString(4, bean.getContents());
			pstmt.setString(5, bean.getUser());
			pstmt.setString(6, bean.getSigndate());
			pstmt.setString(7, bean.getFile_1());
			pstmt.setString(8, bean.getFile_2());
			pstmt.setString(9, bean.getFile_3());
			pstmt.setString(10, bean.getFile_s());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refcnt(String communityuid) {
		dao.getCon();
		try {
			sql = "update community set ref=ref+1 where communityuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, communityuid);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public CommunityBean postview(String communityuid) {
		dao.getCon();
		CommunityBean bean = new CommunityBean();
		try {
			sql = "select * from community where communityuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, communityuid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bean.setCommunityuid(rs.getInt("communityuid"));
				bean.setTitle(rs.getString("title"));
				bean.setSubject(rs.getString("subject"));
				bean.setUser(rs.getString("user"));
				bean.setContents(rs.getString("contents"));
				bean.setFile_1(rs.getString("file_1"));
				bean.setFile_2(rs.getString("file_2"));
				bean.setFile_3(rs.getString("file_3"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}return bean;
	}
	
	public ArrayList<CommunityBean> myPageCommunityList(String session_id) {
		dao.getCon();
		ArrayList<CommunityBean> v = new ArrayList<CommunityBean>();
		try {
			String sql = "select * from community where user=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, session_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommunityBean bean = new CommunityBean();
				bean.setCommunityuid(rs.getInt("communityuid"));
				bean.setTitle(rs.getString("title"));
				bean.setSubject(rs.getString("subject"));
				bean.setUser(rs.getString("user"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setRef(rs.getInt("ref"));
				v.add(bean);
			}
		} catch (Exception e) {
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
		return v;
	}
	
	public CommunityBean info(String communityuid) {
		dao.getCon();
		CommunityBean bean = new CommunityBean();
		try {
			sql = "select * from community where communityuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, communityuid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bean.setCommunityuid(rs.getInt("communityuid"));
				bean.setSubject(rs.getString("subject"));
				bean.setContents(rs.getString("contents"));
				bean.setFile_1(rs.getString("file_1"));
				bean.setFile_2(rs.getString("file_2"));
				bean.setFile_3(rs.getString("file_3"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return bean;
	}
	
	public void postchange(CommunityBean bean) {
		dao.getCon();
		try {
	   			sql = "update community set user=?,title=?,subject=?,secret=?,contents=?,file_1=?,file_2=?,file_3=?,file_s=? where communityuid=?";
				pstmt = dao.conn.prepareStatement(sql);
				pstmt.setString(1, bean.getUser());
				pstmt.setString(2, bean.getTitle());
				pstmt.setString(3, bean.getSubject());
				pstmt.setString(4, bean.getSecret());
				pstmt.setString(5, bean.getContents());
				pstmt.setString(6, bean.getFile_1());
				pstmt.setString(7, bean.getFile_2());
				pstmt.setString(8, bean.getFile_3());
				pstmt.setString(9, bean.getFile_s());
				pstmt.setInt(10, bean.getCommunityuid());
				pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void postdelete(int communityuid) {
		dao.getCon();
		try {
			sql = "delete from community where communityuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, communityuid);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void realignment() {
		dao.getCon();
		try {
			String sql1 = "set @cnt=0";
			String sql2 = "update community set community.communityuid=@cnt:=@cnt+1";
			
			PreparedStatement pstmt1 = dao.conn.prepareStatement(sql1);
			PreparedStatement pstmt2 = dao.conn.prepareStatement(sql2);
			
			pstmt1.executeUpdate();
			pstmt2.executeUpdate();
 		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}	
	