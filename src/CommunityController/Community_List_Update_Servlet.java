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
import model.ShopBean;

@WebServlet("/communitylist_Update.do")
public class Community_List_Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Community_List_Update_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String communityuid = request.getParameter("communityuid");
		CommunityDao dao = new CommunityDao();
		CommunityBean bean = new CommunityBean();
		bean = dao.info(communityuid);
		
		request.setAttribute("shopuid", communityuid);
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/shop/shop_List_Update.jsp");
		dis.forward(request, response);
	}

}
