package CommunityController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommunityDao;

@WebServlet("/communityList_Delete.do")
public class Community_List_Delete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Community_List_Delete_Servlet() {
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
		CommunityDao dao = new CommunityDao();
		dao.postdelete(communityuid);
		
		request.setAttribute("msg", "삭제완료");
		request.setAttribute("url", "/myCommunityList.go");
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}
}
