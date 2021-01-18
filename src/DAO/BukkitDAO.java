package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.BukkitBean;
import model.ShopBean;

public class BukkitDAO {
	DAO dao = new DAO();
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	//장바구니 담기 메서드
	public void bukkitAdd(BukkitBean bean) {
		dao.getCon();
		try {
			/*-----------------------------------1-----2-----3-------4--------5----------6-----------7------------8-------9------10------------------------------------*/
			String sql = "insert into bukkit (shopuid,img_1,img_s,itemname,itemprice,postinguser,postingsigndate,user,signdate,itemcount) values (?,?,?,?,?,?,?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getShopuid());
			pstmt.setString(2, bean.getImg_1());
			pstmt.setString(3, bean.getImg_s());
			pstmt.setString(4, bean.getItemname());
			pstmt.setString(5, bean.getItemprice());
			pstmt.setString(6, bean.getPostinguser());
			pstmt.setString(7, bean.getPostingsigndate());
			pstmt.setString(8, bean.getUser());
			pstmt.setString(9, bean.getSigndate());
			pstmt.setInt(10, bean.getItemcount());
			pstmt.executeUpdate();
			
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
	}
	
	//객체 v 에 담아주는 메서드
	public ArrayList<BukkitBean> showBukkit(String session_id) {
		dao.getCon();
		ArrayList<BukkitBean> v = new ArrayList<BukkitBean>();
		try {
			String sql = "select * from bukkit where user=?";
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setString(1, session_id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BukkitBean bean = new BukkitBean();
				bean.setBukkituid(rs.getInt("bukkituid"));
				bean.setShopuid(rs.getInt("shopuid"));
				bean.setImg_s(rs.getString("img_s"));				
				bean.setItemname(rs.getString("itemname"));
				bean.setItemcount(rs.getInt("itemcount"));
				bean.setItemprice(rs.getString("itemprice"));
				bean.setPostinguser(rs.getString("postinguser"));
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
		return v ;
	}
	//장바구니 아이템 추가
	public int checkItem(String shopuid, String session_id) {
		dao.getCon();
		int answer = 0;
		try {
			
			String sql = "select * from bukkit where user=? and shopuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, session_id);
			pstmt.setString(2, shopuid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				answer = 1;
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
		return answer;
	}
	
	//장바구니에 이미 있는아이템이면 가산처리
	public void plusItem(String shopuid, String session_id, int itemcount) {
		dao.getCon();
		try {
			
			String sql = "update bukkit set itemcount=itemcount+? where shopuid=? and user=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, itemcount);
			pstmt.setString(2, shopuid);
			pstmt.setString(3, session_id);
			pstmt.executeUpdate();
			
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
	}
	
	// 상품 빼기
	public void itemDelete(String bukkituid, String session_id) {
		dao.getCon();
		try {
			
			String sql = "delete from bukkit where bukkituid=? and user=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bukkituid);
			pstmt.setString(2, session_id);
			pstmt.executeUpdate();
			
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
	}
}



















