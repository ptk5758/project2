package userController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import model.UserBean;


@WebServlet("/User_Login.do")
public class User_Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    public User_Login_Servlet() {
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
		
		String uri = request.getParameter("uri");
		String userid = request.getParameter("userid");
		String userpassword = request.getParameter("userpassword");
				
		UserDAO dao = new UserDAO();
		int answer = dao.userSearch(userid, userpassword);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();		
		if(answer == 1) {
			
			UserBean bean = new UserBean();
			bean = dao.userLogin(userid);
			
			HttpSession session = request.getSession();
			session.setAttribute("session_id", bean.getUserid());
			session.setAttribute("session_name", bean.getUsername());
			session.setAttribute("session_nickname", bean.getUsernickname());
			session.setAttribute("session_level", bean.getUserlevel());
			
			out.print("<script>alert('로그인성공!');location.href='"+uri+"';</script>");
			
		}else {
			request.setAttribute("msg", "아이디또는 패스워드가 일치 하지 않습니다.");
			request.setAttribute("url", "main.jsp");
			
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		out.write("<script>location.href='main.jsp';</script>");
	}

}
