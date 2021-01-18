package CommunityController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommunityDao;

@WebServlet("/community/community_delete.do")
public class Community_post_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Community_post_delete() {
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
		int communityuid = Integer.parseInt(request.getParameter("communityuid"));
		CommunityDao dao = new CommunityDao();
		dao.postdelete(communityuid);
		dao.realignment();
		response.sendRedirect("/community/community_list.jsp");
	}

}
