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
import DAO.UserDAO;
import model.ShopBean;
import model.UserBean;

@WebServlet("/admin_UserInfo.go")
public class Admin_User_Info_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_User_Info_Servlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String useruid = request.getParameter("useruid");
		AdminDAO admindao = new AdminDAO();
		UserBean userbean = new UserBean();
		userbean = admindao.userInfoShow(useruid);
		String userid = userbean.getUserid();
		
		ArrayList<ShopBean> shop_v = new ArrayList<ShopBean>();
		shop_v = admindao.userActiveInfo(userid);
		
		int[] likeStack = new int[shop_v.size()];
		for(int i=0; i<likeStack.length; i++) {
			likeStack[i] = admindao.likeCheck(shop_v.get(i).getShopuid());
		}
		
		int shop_count = shop_v.size();
		request.setAttribute("likeStack", likeStack);	
		request.setAttribute("shop_v", shop_v);
		request.setAttribute("shop_count", shop_count);
		request.setAttribute("userbean", userbean);
		RequestDispatcher dis = request.getRequestDispatcher("/admin/user/admin_User_Info.jsp");
		dis.forward(request, response);
	}

}
