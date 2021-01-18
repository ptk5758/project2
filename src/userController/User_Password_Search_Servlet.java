package userController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import model.UserBean;

@WebServlet("/password_Search.do")
public class User_Password_Search_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public User_Password_Search_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String userid = request.getParameter("userid");
		String useremail = request.getParameter("useremail");
		
		int answer = 0;
		UserDAO dao = new UserDAO();
		answer = dao.userPasswordSearch(username, userid, useremail);
		
		if(answer == 1) {
			request.setAttribute("userid", userid);
			RequestDispatcher dis = request.getRequestDispatcher("/user/userPassword_change.jsp");
			dis.forward(request, response);
			
		}else {
			
			request.setAttribute("msg", "해당정보의 유저를 찾을수없습니다.");
			request.setAttribute("url", "/");
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		
	}

}
