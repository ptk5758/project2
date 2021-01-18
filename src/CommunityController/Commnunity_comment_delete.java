package CommunityController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserCommentDAO;
import model.UsercommentBean;

@WebServlet("/community/usercomment_delete.do")
public class Commnunity_comment_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Commnunity_comment_delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int communityuid = Integer.parseInt(request.getParameter("communityuid"));
		UsercommentBean bean = new UsercommentBean();
		UserCommentDAO dao = new UserCommentDAO();
		
		bean = dao.viewcommentuid(communityuid);
		int commentuid = bean.getCommentuid();
		
		dao.usercommentdelete(commentuid);
		
		System.out.println(commentuid);
		request.setAttribute("msg", "댓글삭제 완료");
		request.setAttribute("url", "/community/community_postview.jsp?communityuid="+communityuid);
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}