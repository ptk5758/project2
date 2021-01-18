package userController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ShopDAO;
import DAO.UserDAO;
import model.UserBean;

@WebServlet("/myPage.go")
public class MyPage_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPage_Servlet() {
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
		if(session_id == null) {
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("url", "/");
			RequestDispatcher dis = request.getRequestDispatcher("/user/mypage.jsp");
			dis.forward(request, response);
		}
		UserDAO userdao = new UserDAO();
		ShopDAO shopdao = new ShopDAO();
		UserBean userbean = new UserBean();
		userbean = userdao.myPageShow(session_id);
		
		int total_shoplist = shopdao.countList(session_id);
		
		request.setAttribute("total_shoplist", total_shoplist);
		request.setAttribute("userbean", userbean);
		RequestDispatcher dis = request.getRequestDispatcher("/user/mypage.jsp");
		dis.forward(request, response);
	}

}
