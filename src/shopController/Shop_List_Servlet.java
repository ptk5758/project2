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
import model.ShopBean;

@WebServlet("/shop_list.go")
public class Shop_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_List_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		ShopDAO dao = new ShopDAO();
		ArrayList<ShopBean> v = new ArrayList<ShopBean>();
		if(title == "") {
			v = dao.showShopList();
		}else {
			v = dao.showShopList_title(title);
		}
		request.setAttribute("title", title);
		request.setAttribute("v", v);
		RequestDispatcher dis = request.getRequestDispatcher("/shop/shop_List.jsp");
		dis.forward(request, response);
	}

}
