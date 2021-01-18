package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.AttendBean;
import model.EventBean;


public class EventDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	DAO dao = new DAO();
	String sql;
	
	public void insert_attend(String comment, String user, String signdate, String nickname) {
		dao.getCon();
		try {
			sql = "insert into attend(attend_comment,user_id,attend_signdate,user_nickname) values(?,?,?,?)";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, comment);
			pstmt.setString(2, user);
			pstmt.setString(3, signdate);
			pstmt.setString(4, nickname);
			pstmt.executeUpdate();
		}catch (Exception e) {
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
	
	public ArrayList<AttendBean> select_attend(){
		dao.getCon();
		ArrayList<AttendBean> v = new ArrayList<AttendBean>();
		try {
			sql = "select * from attend";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AttendBean bean = new AttendBean();
				bean.setAttend_uid(rs.getInt("attend_uid"));
				bean.setAttend_user_id(rs.getString("user_id"));
				bean.setAttend_signdate(rs.getString("attend_signdate"));
				bean.setAttend_comment(rs.getString("attend_comment"));
				bean.setAttend_count(rs.getInt("attend_count"));
				v.add(bean);
			}
		}catch (Exception e) {
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
	
	public int attend_check(String signdate2, String user) {
		dao.getCon();
		int answer = 0;
		try {
			sql = "select * from attend where left(attend_signdate,10)=? and user_id=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, signdate2);
			pstmt.setString(2, user);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				answer = 1;
			}
		}catch(Exception e) {
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
		return answer;
	}
	
	//특정날짜 출석체크 확인하기 -태광
	public int showAttend(String calendarDay) {
		dao.getCon();
		int count = 0;
		try {
			String sql = "select count(*) from attend where left(attend_signdate,10)=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, calendarDay);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
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
		return count;
	}
	
	//오늘 출석체크한사람들 -태광
	public ArrayList<AttendBean> todayAttend(String signdate){
		dao.getCon();
		ArrayList<AttendBean> v = new ArrayList<AttendBean>();
		try {
			String sql = "select * from attend where left(attend_signdate,10)=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, signdate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				AttendBean bean = new AttendBean();
				bean.setAttend_uid(rs.getInt("attend_uid"));
				bean.setAttend_comment(rs.getString("attend_comment"));
				bean.setAttend_signdate(rs.getString("attend_signdate"));
				bean.setAttend_user_id(rs.getString("user_id"));
				bean.setUser_nickname(rs.getString("user_nickname"));
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
	//출석 체크 업데이트 -태광
	public void attendCommentUpdate(String attenduid, String newcommnet) {
		dao.getCon();
		try {
			String sql = "update attend set attend_comment=? where attend_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, newcommnet);
			pstmt.setString(2, attenduid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//이벤트 게시판 업로드 -태광
	public void eventPosting(EventBean bean){
		dao.getCon();
		try {
			String sql = "insert into event (subject,contents,font_Color,font_Family,font_size,file_B,file_1,file_2,file_3,user,signdate,period1,period2) values (?,?,?,?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getSubject());
			pstmt.setString(2, bean.getContents());
			pstmt.setString(3, bean.getFont_Color());
			pstmt.setString(4, bean.getFont_Family());
			pstmt.setInt(5, bean.getFont_size());
			pstmt.setString(6, bean.getFile_B());
			pstmt.setString(7, bean.getFile_1());
			pstmt.setString(8, bean.getFile_2());
			pstmt.setString(9, bean.getFile_3());
			pstmt.setString(10, bean.getUser());
			pstmt.setString(11, bean.getPeriod1());
			pstmt.setString(12, bean.getPeriod2());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//진행중인 이벤트 페이지의 객채를 담는 메서드 -태광
	public ArrayList<EventBean> getEventList() {
		dao.getCon();
		ArrayList<EventBean> v = new ArrayList<EventBean>();
		try {
			
			String sql = "select * from event order by thread desc";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EventBean bean = new EventBean();
				bean.setEventuid(rs.getInt("eventuid"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setFile_B(rs.getString("file_B"));
				bean.setSubject(rs.getString("subject"));
				bean.setPeriod1(rs.getString("period1"));
				bean.setPeriod2(rs.getString("period2"));
				bean.setActive(rs.getString("active"));
				v.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//어드민페이지 진행중인 이벤트 객체 담는 메서드 -태광
	public ArrayList<EventBean> getAdminEventList() {
		dao.getCon();
		ArrayList<EventBean> v = new ArrayList<EventBean>();
		try {
			
			String sql = "select * from event order by thread desc";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EventBean bean = new EventBean();
				bean.setEventuid(rs.getInt("eventuid"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setSubject(rs.getString("subject"));
				bean.setPeriod1(rs.getString("period1"));
				bean.setPeriod2(rs.getString("period2"));
				bean.setThread(rs.getString("thread"));
				bean.setRef(rs.getInt("ref"));
				bean.setActive(rs.getString("active"));
				v.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	//어드민페이지 이벤트 노출도 상승 메서드 -태광
	public void threadUP(String eventuid) {
		dao.getCon();
		try {
			
			String sql = "update event set thread=thread+1 where eventuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, eventuid);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//어드민페이지 이벤트 노출도 감소 메서드 -태광
	public void threadDown(String eventuid) {
		dao.getCon();
		try {
			
			String sql = "update event set thread=thread-1  where eventuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, eventuid);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//이벤트 상세페이지 빈겍체 넘겨주는 메서드 -태광
	public EventBean getEventInfo(String eventuid) {
		dao.getCon();
		EventBean bean = new EventBean();
		try {
			String sql = "select * from event where eventuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, eventuid);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				bean.setContents(rs.getString("contents"));
				bean.setSubject(rs.getString("subject"));
				bean.setPeriod1(rs.getString("period1"));
				bean.setPeriod2(rs.getString("period2"));
				bean.setFile_B(rs.getString("file_B"));
				bean.setFile_1(rs.getString("file_1"));
				bean.setFile_2(rs.getString("file_2"));
				bean.setFile_3(rs.getString("file_3"));
				bean.setFont_Color(rs.getString("font_Color"));
				bean.setFont_Family(rs.getString("font_Family"));
				bean.setFont_size(rs.getInt("font_size"));
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
		return bean;
	}
	//이벤트 종료하기 메서드 - 태광
	public void eventEnd(String eventuid) {
		dao.getCon();
		System.out.println(eventuid);
		try {
			String sql = "update event set active=0,thread=0 where eventuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, eventuid);
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
	//이벤트 딜리트 메서드 -태광
	public void eventDelete(String eventuid) {
		dao.getCon();
		try {
			String sql = "delete from event where eventuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, eventuid);
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
	//이벤트 게시판 수정하기 이미지 제외 -태광
	public void eventUpdate(EventBean bean, String eventuid) {
		dao.getCon();
		try {
			String sql = "update event set subject=?, contents=?, period1=?, period2=?, font_Color=?, font_Family=?, font_size=? where eventuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getSubject());
			pstmt.setString(2, bean.getContents());
			pstmt.setString(3, bean.getPeriod1());
			pstmt.setString(4, bean.getPeriod2());
			pstmt.setString(5, bean.getFont_Color());
			pstmt.setString(6, bean.getFont_Family());
			pstmt.setInt(7, bean.getFont_size());
			pstmt.setString(8, eventuid);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//이벤트 게시판 조회수 -태광
	public void refUP(String eventuid) {
		dao.getCon();
		try {
			String sql = "update event set ref=ref+1 where eventuid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, eventuid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
