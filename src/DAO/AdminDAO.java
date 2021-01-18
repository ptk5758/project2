package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.ShopBean;
import model.UserBean;

public class AdminDAO {
	DAO dao = new DAO();
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	//관리자 유저관리 메인페이지 유저리스트
	public ArrayList<UserBean> showUserList(){
		dao.getCon();
		ArrayList<UserBean> v = new ArrayList<UserBean>();
		try {
			String sql = "select * from user order by userlevel desc";
			pstmt=dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserBean bean = new UserBean();
				bean.setUsername(rs.getString("username"));
				bean.setUseruid(rs.getInt("useruid"));
				bean.setUsernickname(rs.getString("usernickname"));
				bean.setUserid(rs.getString("userid"));
				bean.setUserlevel(rs.getString("userlevel"));
				bean.setSigndate(rs.getString("signdate"));
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
	//유저 정보 보기 메서드
	public UserBean userInfoShow(String useruid) {
		dao.getCon();
		UserBean bean = new UserBean();
		try {
			String sql = "select * from user where useruid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, useruid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setUseruid(rs.getInt("useruid"));
				bean.setUsername(rs.getString("username"));
				bean.setUsernickname(rs.getString("usernickname"));
				bean.setUserid(rs.getString("userid"));
				bean.setUserphone(rs.getString("userphone"));
				bean.setUserage(rs.getString("userage"));
				bean.setUsergender(rs.getString("usergender"));
				bean.setUseremail(rs.getString("useremail"));
				bean.setUseraddress(rs.getString("useraddress"));
				bean.setUseraddress_1(rs.getString("useraddress_1"));
				bean.setUserlevel(rs.getString("userlevel"));
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
		return bean;
	}
	
	//유저 레벨 변경하기
	public void userLevelChange(String useruid, String userlevel) {
		dao.getCon();
		try {
			String sql = "update user set userlevel=? where useruid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, userlevel);
			pstmt.setString(2, useruid);
			pstmt.executeUpdate();
		} catch (Exception e) {
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
	//유저 굿즈샵 리스트 가져오기
	public ArrayList<ShopBean> userActiveInfo(String userid) {
		dao.getCon();
		ArrayList<ShopBean> v = new ArrayList<ShopBean>();
		try {
			String sql = "select * from shop where user=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ShopBean bean = new ShopBean();
				bean.setShopuid(rs.getInt("shopuid"));
				bean.setItemsubject(rs.getString("itemsubject"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setRef(rs.getInt("ref"));
				bean.setItemlike(rs.getInt("itemlike"));
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
	//좋아요 갯수 새기
	public int likeCheck(int shopuid) {
		dao.getCon();
		int likeStack = 0;
		try {
			String sql = "select count(*) from shop JOIN likestack ON shop.shopuid = likestack.like_shopuid where shopuid=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setInt(1, shopuid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				likeStack = rs.getInt("count(*)");
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
		return likeStack;
	}
	
	//유저들 경고 횟수 올리기
	public void warningSend(String userid, String send_signdate) {
		dao.getCon();
		try {
			String sql = "insert into usermsg (userid,contents,send_signdate,warning) values (?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, "부적절한 컨탠츠");
			pstmt.setString(3, send_signdate);
			pstmt.setInt(4, 1);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("DB실패");
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
	//경고 회수 새기
	public int warningCount(String userid) {
		dao.getCon();
		int warningCount = 0;
		try {
			String sql = "select count(*) from usermsg where userid=? and warning=1";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				warningCount = rs.getInt("count(*)");
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
		return warningCount;
	}
	
	//어드민 샵 리스트 v 받기
	public ArrayList<ShopBean> showShopList(){
		dao.getCon();
		ArrayList<ShopBean> v = new ArrayList<ShopBean>();
		try {
			String sql = "select * from shop";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ShopBean bean = new ShopBean();
				bean.setShopuid(rs.getInt("shopuid"));
				bean.setImg_1(rs.getString("img_1"));
				bean.setItemname(rs.getString("itemname"));
				bean.setUser(rs.getString("user"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setRef(rs.getInt("ref"));
				bean.setItemprice(rs.getString("itemprice"));
				bean.setActive(rs.getString("active"));
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
	
	//상품검색
	public ArrayList<ShopBean> searchShopList(String searchSelect, String searchInput){
		dao.getCon();
		ArrayList<ShopBean> v = new ArrayList<ShopBean>();
		try {
			String sql = "select * from shop where "+searchSelect+" LIKE '%"+searchInput+"%'";
			stmt = dao.conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ShopBean bean = new ShopBean();
				bean.setShopuid(rs.getInt("shopuid"));
				bean.setImg_1(rs.getString("img_1"));
				bean.setItemname(rs.getString("itemname"));
				bean.setUser(rs.getString("user"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setRef(rs.getInt("ref"));
				bean.setItemprice(rs.getString("itemprice"));
				bean.setActive(rs.getString("active"));
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
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return v; 
	}
	//날짜로 검색하기
	public ArrayList<ShopBean> dateSelect(String signdate1, String signdate2){
		dao.getCon();
		ArrayList<ShopBean> v = new ArrayList<ShopBean>();
		try {
			String sql = "select * from shop where left(signdate,10) > ? and left(signdate,10) < ?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, signdate1);
			pstmt.setString(2, signdate2);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ShopBean bean = new ShopBean();
				bean.setShopuid(rs.getInt("shopuid"));
				bean.setImg_1(rs.getString("img_1"));
				bean.setItemname(rs.getString("itemname"));
				bean.setUser(rs.getString("user"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setRef(rs.getInt("ref"));
				bean.setItemprice(rs.getString("itemprice"));
				bean.setActive(rs.getString("active"));
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
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return v; 
	}
	//블라인드 처리하느 메서드
	public void listBlind(String shopuid) {
		dao.getCon();
		try {
			String sql = "update shop set active=2 where shopuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, shopuid);
			pstmt.executeUpdate();
		} catch (Exception e) {
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
	//블라인드취소
	public void cancelBlind(String shopuid) {
		dao.getCon();
		try {
			String sql = "update shop set active=1 where shopuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, shopuid);
			pstmt.executeUpdate();
		} catch (Exception e) {
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

	
	//테스트
	public int testMethod() {
		return 1;
	}

}
