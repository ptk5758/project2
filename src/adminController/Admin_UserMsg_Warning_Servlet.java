package adminController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AdminDAO;

@WebServlet("/user_Warning_Msg.do")
public class Admin_UserMsg_Warning_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_UserMsg_Warning_Servlet() {
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
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String send_signdate = sdf.format(date);
		
		//체크박스 값받는 포문
		//String user[0~n]까지저장
		for(String user: request.getParameterValues("userid")) {
			AdminDAO dao = new AdminDAO();
			dao.warningSend(user, send_signdate);
		}
		
		request.setAttribute("msg", "경고 전송완료");
		request.setAttribute("url", "/admin_User.go");
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}
