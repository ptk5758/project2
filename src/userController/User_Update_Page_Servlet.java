package userController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import model.UserBean;

@WebServlet("/userUpdate.do")
public class User_Update_Page_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public User_Update_Page_Servlet() {
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
		UserBean bean = new UserBean();
		UserDAO dao = new UserDAO();
		bean = dao.myPageShow(session_id);
		
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/user/user_Updatepage.jsp");
		dis.forward(request, response);
	}

}
