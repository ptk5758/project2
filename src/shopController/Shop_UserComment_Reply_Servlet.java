package shopController;

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

@WebServlet("/usercomment_reply.do")
public class Shop_UserComment_Reply_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_UserComment_Reply_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String signdate = sdf.format(date);
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("session_id");
		
		if(session.getAttribute("session_id") == null) {
			request.setAttribute("msg", "로그인후 이용할수있습니다.");
			request.setAttribute("url", "/");
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		
		int shopuid = Integer.parseInt(request.getParameter("shopuid"));
		int commentuid = Integer.parseInt(request.getParameter("commentuid"));
		int fid = Integer.parseInt(request.getParameter("fid"));
		UserCommentDAO dao = new UserCommentDAO();
		char thread = dao.showThread(fid);
		thread++;
		UsercommentBean bean = new UsercommentBean();
		bean.setFid(fid);
		bean.setCommentuid(commentuid);
		bean.setShopuid(shopuid);
		bean.setUser(session_id);
		bean.setUsercomment(request.getParameter("comment"));
		bean.setSigndate(signdate);
		bean.setReply(1);
		
		
		dao.replyComment(bean, thread);
		
		String url = "/shop_View_Page.go?shopuid="+Integer.parseInt(request.getParameter("shopuid"));
		request.setAttribute("msg", "답글 작성완료!");
		request.setAttribute("url", url);
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}
