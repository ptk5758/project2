package adminController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ShopDAO;

@WebServlet("/admin_ShopList_Delete.do")
public class Admin_Shop_Delete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Shop_Delete_Servlet() {
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
		String shopuid[] = request.getParameterValues("shopuid");
		ShopDAO dao = new ShopDAO();
		for(String shopvalue:shopuid) {
			dao.shopListDelete(shopvalue);
		}
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}
