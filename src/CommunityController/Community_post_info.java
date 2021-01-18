package CommunityController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommunityDao;
import model.CommunityBean;

@WebServlet("/community/community_post_info.do")
public class Community_post_info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Community_post_info() {
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
		String communityuid = request.getParameter("uid");
		
		CommunityBean bean  = new CommunityBean();
		CommunityDao dao = new CommunityDao();
		
		bean = dao.info(communityuid);
		
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/community/community_post_modify.jsp");
		dis.forward(request, response);
	}

}
