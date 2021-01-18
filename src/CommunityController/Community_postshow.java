package CommunityController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommunityDao;
import DAO.UserCommentDAO;
import model.CommunityBean;
import model.UsercommentBean;


@WebServlet("/community/community_postshow.do")
public class Community_postshow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   public Community_postshow() {
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
		String communityuid = request.getParameter("postshow");
				
		CommunityBean bean = new CommunityBean();
		ArrayList<UsercommentBean> v = new ArrayList<UsercommentBean>();
		CommunityDao dao = new CommunityDao();
		UserCommentDAO dao1 = new UserCommentDAO();
		dao.refcnt(communityuid);
		bean = dao.postview(communityuid);
		
		v = dao1.showUserCommentCommunity(communityuid);
		System.out.println(v.size());
		request.setAttribute("bean", bean);
		request.setAttribute("v", v);
		RequestDispatcher dis = request.getRequestDispatcher("/community/community_postview.jsp");
		dis.forward(request, response);
		
	}

}
