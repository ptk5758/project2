package userController;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import model.UserBean;

@WebServlet("/user/SignUp.do")
public class User_SignUp_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public User_SignUp_Servlet() {
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
		bean.setUserpassword(request.getParameter("userpassword"));
		bean.setUsername(request.getParameter("username"));
		bean.setUsernickname(request.getParameter("usernickname"));
		bean.setUserphone(request.getParameter("userphone"));
		bean.setUserage(request.getParameter("userage"));
		bean.setUsergender(request.getParameter("usergender"));
		bean.setUseremail(request.getParameter("useremail"));
		bean.setUseraddress(request.getParameter("useraddress"));
		bean.setUseraddress_1(request.getParameter("useraddress_1"));
		bean.setUseraddress_2(request.getParameter("useraddress_2"));
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String signdate = sdf.format(today);
		
		UserDAO dao = new UserDAO();
		dao.signup(bean, signdate);
		
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('회원가입성공')");
		out.print("</script>");
		
		response.sendRedirect("/");
	}

}
