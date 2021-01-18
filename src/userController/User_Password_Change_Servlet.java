package userController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;

@WebServlet("/password_Change.do")
public class User_Password_Change_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public User_Password_Change_Servlet() {
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
		String userid = request.getParameter("userid");
		String newPassword = request.getParameter("newPassword1");
		UserDAO dao = new UserDAO();
		dao.passwordChange(userid, newPassword);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>alert('비밀번호가 정상적으로 변경되었습니다.');location.href='/';</script>");
		
	}

}
