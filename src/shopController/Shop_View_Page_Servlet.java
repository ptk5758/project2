package shopController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ShopDAO;
import DAO.UserCommentDAO;
import model.ShopBean;
import model.UsercommentBean;

@WebServlet("/shop_View_Page.go")
public class Shop_View_Page_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_View_Page_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shopuid = request.getParameter("shopuid");
		ShopBean bean = new ShopBean();
		ShopDAO dao = new ShopDAO();
		dao.refUp(shopuid);
		bean = dao.viewPage(shopuid);
		
		UserCommentDAO commentdao = new UserCommentDAO();
		ArrayList<UsercommentBean> v = new ArrayList<UsercommentBean>();
		v = commentdao.showUserComment(shopuid);
		
		int likestack = dao.likeStack(shopuid);
		
		request.setAttribute("likestack", likestack);
		request.setAttribute("shopuid", shopuid);
		request.setAttribute("v", v);
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/shop/shop_View_Page.jsp");
		dis.forward(request, response);
		
	}

}
