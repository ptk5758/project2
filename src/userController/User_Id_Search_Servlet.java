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

@WebServlet("/id_Search.do")
public class User_Id_Search_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public User_Id_Search_Servlet() {
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
		String useremail = request.getParameter("useremail");
		
		int answer = 0;
		UserDAO dao = new UserDAO();
		answer = dao.userIdSearch(username, useremail);
		
		if(answer == 1) {
			UserBean bean = new UserBean();
			bean = dao.useridShow(username, useremail);
			
			request.setAttribute("bean", bean);
			RequestDispatcher dis = request.getRequestDispatcher("/user/user_id_ShowPage.jsp");
			dis.forward(request, response);
			
		}else {
			
			request.setAttribute("msg", "아이디또는 패스워드가 일치 하지 않습니다.");
			request.setAttribute("url", "/user/userid_Search.jsp");
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		
	}

}
