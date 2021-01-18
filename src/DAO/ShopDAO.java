package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.ShopBean;

public class ShopDAO {
	DAO dao = new DAO();
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	
	//굿즈샵 상품등록하기 메서드
	public void shopPosting(ShopBean bean) {
		dao.getCon();
		try {
			/* ---------------------------------1----------2------------3---------4---------5----------6--------7----8------9-----10--11-----12--------------1-2-3-4-5-6-7-8-9-10-11-12------- */
			String sql = "insert into shop (itemsubject,itemtitle,itemcategory,itemname,itemprice,itemcomment,img_1,img_2,img_3,img_s,user,signdate) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getItemsubject());
			pstmt.setString(2, bean.getItemtitle());
			pstmt.setString(3, bean.getItemcategory());
			
			pstmt.setString(4, bean.getItemname());
			pstmt.setString(5, bean.getItemprice());
			pstmt.setString(6, bean.getItemcomment());
			
			pstmt.setString(7, bean.getImg_1());
			pstmt.setString(8, bean.getImg_2());
			pstmt.setString(9, bean.getImg_3());
			
			pstmt.setString(10, bean.getImg_s());
			pstmt.setString(11, bean.getUser());
			pstmt.setString(12, bean.getSigndate());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//굿즈샵 메인 리스트 메서드
	public ArrayList<ShopBean> showShopList() {
		dao.getCon();
		ArrayList<ShopBean> v = new ArrayList<ShopBean>();
		try {
			
			String sql = "select * from shop";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ShopBean bean = new ShopBean();
				bean.setShopuid(rs.getInt("shopuid"));
				bean.setImg_s(rs.getString("img_s"));
				bean.setItemsubject(rs.getString("itemsubject"));
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return v;
	}
	//굿즈샵 메인 리스트 메서드 상품 타이틀 받아서
	public ArrayList<ShopBean> showShopList_title(String itemtitle) {
		dao.getCon();
		ArrayList<ShopBean> v = new ArrayList<ShopBean>();
		try {
			
			String sql = "select * from shop where itemtitle=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, itemtitle);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ShopBean bean = new ShopBean();
				bean.setShopuid(rs.getInt("shopuid"));
				bean.setImg_s(rs.getString("img_s"));
				bean.setItemsubject(rs.getString("itemsubject"));
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return v;
	}
	//굿즈샵 상세페이지 드갈때 조회수 업
	public void refUp(String shopuid) {
		dao.getCon();
		try {
			String sql = "update shop set ref=ref+1 where shopuid=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, shopuid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//굿즈샵 상품 상세페이지
	public ShopBean viewPage(String shopuid) {
		dao.getCon();
		ShopBean bean = new ShopBean();
		try {
			
			String sql = "select * from shop where shopuid="+shopuid+"";
			stmt = dao.conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				bean.setShopuid(rs.getInt("shopuid"));
				bean.setImg_1(rs.getString("img_1"));
				bean.setImg_2(rs.getString("img_2"));
				bean.setImg_3(rs.getString("img_3"));
				bean.setImg_s(rs.getString("img_s"));
				bean.setItemcategory(rs.getString("itemcategory"));
				bean.setItemcomment(rs.getString("itemcomment"));
				bean.setItemlike(rs.getInt("itemlike"));
				bean.setItemname(rs.getString("itemname"));
				bean.setItemprice(rs.getString("itemprice"));
				bean.setItemscore(rs.getInt("itemscore"));
				bean.setItemscore_count(rs.getInt("itemscore_count"));
				bean.setItemsubject(rs.getString("itemsubject"));
				bean.setItemtitle(rs.getString("itemtitle"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setUser(rs.getString("user"));
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
	
	//굿즈샵 상품 보고 빈에 값넣어주는 메서드
	public ShopBean bukkit_item_bean(String shopuid) {
		dao.getCon();
		ShopBean bean = new ShopBean();
		try {
			String sql = "select * from shop where shopuid=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, shopuid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setShopuid(rs.getInt("shopuid"));
				bean.setImg_1(rs.getString("img_1"));
				bean.setImg_s(rs.getString("img_s"));
				bean.setItemname(rs.getString("itemname"));
				bean.setItemprice(rs.getString("itemprice"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setUser(rs.getString("user"));
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
	
	//마이페이지 총 상품수 카운트
	public int countList(String session_id) {
		dao.getCon();
		int total_shoplist = 0;
		try {
			String sql = "select count(*) from shop where user=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, session_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total_shoplist = rs.getInt("count(*)");
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
		return total_shoplist;
	}
	
	//굿즈샵 상품관리 페이지
	public ArrayList<ShopBean> myPageShopList(String session_id) {
		dao.getCon();
		ArrayList<ShopBean> v = new ArrayList<ShopBean>();
		try {
			String sql = "select * from shop where user=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, session_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ShopBean bean = new ShopBean();
				bean.setShopuid(rs.getInt("shopuid"));
				bean.setImg_s(rs.getString("img_s"));
				bean.setItemname(rs.getString("itemname"));
				bean.setItemprice(rs.getString("itemprice"));
				bean.setItemlike(rs.getInt("itemlike"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setRef(rs.getInt("ref"));
				bean.setItemtitle(rs.getString("itemtitle"));
				bean.setItemcategory(rs.getString("itemcategory"));
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
	//상품리스트 삭제 -태광
	public void shopListDelete(String shopuid) {
		dao.getCon();
		try {
			String sql = "delete from shop where shopuid=?";
			pstmt=dao.conn.prepareStatement(sql);
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
	//상품리스트 수정페이지로 넘어가기 - 태광
	public ShopBean itemUpdate(String shopuid) {
		dao.getCon();
		ShopBean bean = new ShopBean();
		try {
			String sql = "select * from shop where shopuid="+shopuid+"";
			stmt = dao.conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				bean.setShopuid(rs.getInt("shopuid"));
				bean.setImg_1(rs.getString("img_1"));
				bean.setImg_2(rs.getString("img_2"));
				bean.setImg_3(rs.getString("img_3"));
				bean.setImg_s(rs.getString("img_s"));
				bean.setItemcategory(rs.getString("itemcategory"));
				bean.setItemcomment(rs.getString("itemcomment"));
				bean.setItemlike(rs.getInt("itemlike"));
				bean.setItemname(rs.getString("itemname"));
				bean.setItemprice(rs.getString("itemprice"));
				bean.setItemscore(rs.getInt("itemscore"));
				bean.setItemscore_count(rs.getInt("itemscore_count"));
				bean.setItemsubject(rs.getString("itemsubject"));
				bean.setItemtitle(rs.getString("itemtitle"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setUser(rs.getString("user"));
			}
		} catch (Exception e) {
			System.out.println("실패");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				dao.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
	public void shopListUpdate(ShopBean bean) {
		dao.getCon();
		int shopuid = bean.getShopuid();
		try {
			System.out.println(bean.getImg_1());
			System.out.println(shopuid);
			String sql = "update shop set itemsubject=?, itemname=?, itemprice=?, itemcomment=?, img_1=?, img_2=?, img_3=?, img_s=? where shopuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getItemsubject());
			pstmt.setString(2, bean.getItemname());
			pstmt.setString(3, bean.getItemprice());
			pstmt.setString(4, bean.getItemcomment());
			pstmt.setString(5, bean.getImg_1());
			pstmt.setString(6, bean.getImg_2());
			pstmt.setString(7, bean.getImg_3());
			pstmt.setString(8, bean.getImg_s());
			pstmt.setInt(9, shopuid);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("실패");
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//좋아요 메서드!!
	public void itemLike(String shopuid, String session_id, String like_signdate) {
		dao.getCon();
		try {
			String sql = "insert into likestack (like_shopuid,like_user,like_signdate) values (?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, shopuid);
			pstmt.setString(2, session_id);
			pstmt.setString(3, like_signdate);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("실패");
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//좋아요 갯수 돌려주기
	public int likeStack(String shopuid) {
		dao.getCon();
		int likestack = 0;
		try {
			String sql = "select count(*) from shop JOIN likestack ON shop.shopuid = likestack.like_shopuid and shopuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, shopuid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				likestack = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return likestack;
	}
	
	//좋아요 돼있는지 체크
	public int chackLike(String shopuid, String session_id) {
		dao.getCon();
		int answer = 0;
		try {
			String sql = "select * from likestack where like_shopuid=? and like_user=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, shopuid);
			pstmt.setString(2, session_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				answer = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	//좋아요 빠꾸메소드
	
	public void itemLikeBack(String shopuid, String session_id) {
		dao.getCon();
		try {
			String sql = "delete from likestack where like_shopuid=? and like_user=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, shopuid);
			pstmt.setString(2, session_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("실패");
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

