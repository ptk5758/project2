package CommunityController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserCommentDAO;
import model.UsercommentBean;

@WebServlet("/community/usercomment_reply.do")
public class Community_comment_reply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Community_comment_reply() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Date date = new Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
		String signdate = cal.format(date);
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("id");
		
		if(session.getAttribute("id") == null) { 
			request.setAttribute("msg", "로그인 후 이용할 수 있습니다."); 
			request.setAttribute("url", "/"); 
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp"); 
			dis.forward(request, response);
		  }
		 
		
		int communityuid = Integer.parseInt(request.getParameter("communityuid"));
		int commentuid = Integer.parseInt(request.getParameter("commentuid"));
		int fid = Integer.parseInt(request.getParameter("fid"));
		UserCommentDAO dao = new UserCommentDAO();
		char thread = dao.showThread(fid);
		thread++;
		UsercommentBean bean = new UsercommentBean();
		bean.setFid(fid);
		bean.setCommentuid(commentuid);
		bean.setCommunityuid(communityuid);
		bean.setUser(session_id);
		
		bean.setUsercomment(request.getParameter("usercomment"));
		bean.setSigndate(signdate);
		bean.setReply(1);
		
		dao.replyComment(bean, thread);
		             
		String url = "/community/community_postshow.do?postshow="+Integer.parseInt(request.getParameter("communityuid"));
		request.setAttribute("msg", "답글 작성완료!");
		request.setAttribute("url", url);
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
		
	}
}
