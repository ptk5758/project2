package shopController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ShopDAO;

@WebServlet("/shopList_Delete.do")
public class Shop_List_Delete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_List_Delete_Servlet() {
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
		String shopuid = request.getParameter("shopuid");
		ShopDAO dao = new ShopDAO();
		dao.shopListDelete(shopuid);
		
		request.setAttribute("msg", "삭제완료");
		request.setAttribute("url", "/myShopList.go");
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}
