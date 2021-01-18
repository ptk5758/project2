package adminController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AdminDAO;

@WebServlet("/admin_UserLevel_Change.do")
public class Admin_UserLevel_Change_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_UserLevel_Change_Servlet() {
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
		String userlevel = request.getParameter("userlevel");
		AdminDAO dao = new AdminDAO();
		dao.userLevelChange(useruid, userlevel);
		
		request.setAttribute("msg", "조정완료");
		request.setAttribute("url", "/admin_UserInfo.go?useruid="+useruid);
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}
