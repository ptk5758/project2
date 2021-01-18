package adminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AdminDAO;
import model.ShopBean;

@WebServlet("/admin_Shop.go")
public class Admin_Shop_Page_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Shop_Page_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminDAO dao = new AdminDAO();
		ArrayList<ShopBean> v = new ArrayList<ShopBean>();
		v = dao.showShopList();
		
		int likeCount[] = new int[v.size()];
		String active[] = new String[v.size()];
		for(int i=0; i<likeCount.length; i++) {
			likeCount[i] = dao.likeCheck(v.get(i).getShopuid());
			active[i] = v.get(i).getActive();
		}
		
		request.setAttribute("active", active);
		request.setAttribute("likeCount", likeCount);
		request.setAttribute("v", v);
		RequestDispatcher dis = request.getRequestDispatcher("/admin/shop/admin_ShopPage.jsp");
		dis.forward(request, response);
	}

}
