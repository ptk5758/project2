package shopController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ShopDAO;
import model.ShopBean;

@WebServlet("/shoplist_Update.do")
public class Shop_List_Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_List_Update_Servlet() {
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
		ShopDAO dao = new ShopDAO();
		ShopBean bean = new ShopBean();
		bean = dao.itemUpdate(shopuid);
		
		request.setAttribute("shopuid", shopuid);
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/shop/shop_List_Update.jsp");
		dis.forward(request, response);
	}

}
