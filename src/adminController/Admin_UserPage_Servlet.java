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
import DAO.ShopDAO;
import model.UserBean;

@WebServlet("/admin_User.go")
public class Admin_UserPage_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_UserPage_Servlet() {
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
		
		AdminDAO admindao = new AdminDAO();
		ShopDAO shopdao = new ShopDAO();
		ArrayList<UserBean> v = new ArrayList<UserBean>();
		v = admindao.showUserList();
		
		int shopListCount[] = new int[v.size()];
		int warningCount[] = new int [v.size()];
		for(int i=0; i<v.size(); i++) {
			shopListCount[i] = shopdao.countList(v.get(i).getUserid());
			warningCount[i] = admindao.warningCount(v.get(i).getUserid());
		}
		
		request.setAttribute("warningCount", warningCount);
		request.setAttribute("shopListCount", shopListCount);
		request.setAttribute("count", v.size());
		request.setAttribute("v", v);
		RequestDispatcher dis = request.getRequestDispatcher("/admin/user/admin_UserPage.jsp");
		dis.forward(request, response);;
	}

}
