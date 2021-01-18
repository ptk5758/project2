package userController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ShopDAO;
import model.ShopBean;

@WebServlet("/myShopList.go")
public class MyPage_MyShop_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPage_MyShop_List_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("session_id");
		ArrayList<ShopBean> v = new ArrayList<ShopBean>();
		ShopDAO dao = new ShopDAO();
		v = dao.myPageShopList(session_id);
		
		int total_shop_list = v.size();
		request.setAttribute("total_shop_list", total_shop_list);
		request.setAttribute("v", v);
		RequestDispatcher dis = request.getRequestDispatcher("/user/mypage_ShopList.jsp");
		dis.forward(request, response);
		
	}

}
