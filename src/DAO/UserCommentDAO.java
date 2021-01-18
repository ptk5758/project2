package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.UsercommentBean;

public class UserCommentDAO {
	DAO dao = new DAO();
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	
	//댓글 남기기 메서드
	public void userComment(UsercommentBean bean) {
		dao.getCon();
		try {
			
			String sql ="insert into usercomment (shopuid,user,usercomment,signdate,img_1,fid) values (?,?,?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getShopuid());
			pstmt.setString(2, bean.getUser());
			pstmt.setString(3, bean.getUsercomment());
			pstmt.setString(4, bean.getSigndate());
			pstmt.setString(5, bean.getImg_1());
			pstmt.setInt(6, bean.getFid());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	//일반게시판
	public void userCommentCommunity(UsercommentBean bean) {
		dao.getCon();
		try {
			
			String sql ="insert into usercomment (communityuid,user,usercomment,signdate,img_1,fid) values (?,?,?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getCommunityuid());
			pstmt.setString(2, bean.getUser());
			pstmt.setString(3, bean.getUsercomment());
			pstmt.setString(4, bean.getSigndate());
			pstmt.setString(5, bean.getImg_1());
			pstmt.setInt(6, bean.getFid());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	//댓글 보기 메서드
	public ArrayList<UsercommentBean> showUserComment(String shopuid) {
		dao.getCon();
		ArrayList<UsercommentBean> v = new ArrayList<UsercommentBean>();
		try {
			String sql = "select * from usercomment where shopuid=? order by fid ASC";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, shopuid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UsercommentBean bean = new UsercommentBean();
				bean.setUser(rs.getString("user"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setUsercomment(rs.getString("usercomment"));
				bean.setImg_1(rs.getString("img_1"));
				bean.setCommentuid(rs.getInt("commentuid"));
				bean.setReply(rs.getInt("reply"));
				bean.setFid(rs.getInt("fid"));
				v.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dao.conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return v;
	}
	//일반게시판 댓글보기
	public ArrayList<UsercommentBean> showUserCommentCommunity(String communityuid) {
		dao.getCon();
		ArrayList<UsercommentBean> v = new ArrayList<UsercommentBean>();
		try {
			String sql = "select * from usercomment where communityuid=? order by fid ASC";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, communityuid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UsercommentBean bean = new UsercommentBean();
				bean.setUser(rs.getString("user"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setUsercomment(rs.getString("usercomment"));
				bean.setImg_1(rs.getString("img_1"));
				bean.setCommentuid(rs.getInt("commentuid"));
				bean.setReply(rs.getInt("reply"));
				bean.setFid(rs.getInt("fid"));
				v.add(bean);
			}
		} catch (Exception e) {
			
			System.out.println("실패");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dao.conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return v;
	}
	
	//댓글 삭제 메서드
	public void usercommentDelete(String commentuid) {
		dao.getCon();
		try {
			
			String sql = "delete from usercomment where commentuid=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, commentuid);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	//댓글 남길때 fid 값 가져오기
	public int showCommentFid() {
		dao.getCon();
		int fid = 0;
		try {
			String sql = "select fid from usercomment order by fid desc limit 0,1";
			pstmt=dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fid = rs.getInt("fid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dao.conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return fid;
	}
	
	//댓글 답변 남기기 메서드
	public void replyComment(UsercommentBean bean ,char thread) {
		dao.getCon();
		try {
			String sql = "insert into usercomment (fid,shopuid,user,usercomment,signdate,reply,thread) values (?,?,?,?,?,?,'"+thread+"')";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getFid());
			pstmt.setInt(2, bean.getShopuid());
			pstmt.setString(3, bean.getUser());
			pstmt.setString(4, bean.getUsercomment());
			pstmt.setString(5, bean.getSigndate());
			pstmt.setInt(6, bean.getReply());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("실패");
			e.printStackTrace();			
		}
	}
	//쓰레드 값 가져오기
	public char showThread(int fid) {
		dao.getCon();
		char thread = 'A';
		try {
			String sql = "select thread from usercomment where fid=? order by thread desc limit 0,1";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, fid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				thread = rs.getString("thread").charAt(0);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return thread;
	}
	
	//-동건
	public UsercommentBean viewcommentuid(int communityuid) {
		dao.getCon();
		UsercommentBean bean = new UsercommentBean();
		try {
			String sql = "select * from usercomment where communityuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, communityuid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setCommentuid(rs.getInt("commentuid"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return bean;
	}
	//-동건
	public void usercommentdelete(int commentuid) {
		dao.getCon();
		try {
			String sql = "delete from usercomment where commentuid=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setInt(1, commentuid);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void epi_usercomment(UsercommentBean bean) {
		dao.getCon();
		try {
				String sql = "INSERT INTO usercomment (communityuid, usercomment, user, signdate, fid) values(?,?,?,?,?)";
				pstmt = dao.conn.prepareStatement(sql);
				pstmt.setInt(1, bean.getCommunityuid());
				pstmt.setString(2, bean.getUsercomment());
				pstmt.setString(3, bean.getUser());
				pstmt.setString(4, bean.getSigndate());
				pstmt.setInt(5, bean.getFid());
				
				pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally end bracket
	}//epi_usercomment end bracket

}
