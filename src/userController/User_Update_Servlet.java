package userController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import model.UserBean;

@WebServlet("/user_Update.do")
public class User_Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public User_Update_Servlet() {
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
		UserBean bean = new UserBean();
		
		bean.setUserid(request.getParameter("userid"));
		bean.setUsername(request.getParameter("username"));
		bean.setUsernickname(request.getParameter("usernickname"));
		bean.setUseremail(request.getParameter("useremail"));
		bean.setUserphone(request.getParameter("userphone"));
		bean.setUseraddress(request.getParameter("useraddress"));
		bean.setUseraddress_1(request.getParameter("useraddress_1"));
		bean.setUseraddress_2(request.getParameter("useraddress_2"));
		
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("session_id");
		
		UserDAO dao = new UserDAO();
		dao.userUpdate(bean, session_id);
		
		session.invalidate();
		response.sendRedirect("/");
	}

}
