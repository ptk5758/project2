package adminController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AdminDAO;

@WebServlet("/admin_Cancel_Blind.do")
public class Admin_Blind_Cancel_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Blind_Cancel_Servlet() {
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
		AdminDAO dao = new AdminDAO();
		for(String shopuid : request.getParameterValues("shopuid")) {
			dao.cancelBlind(shopuid);
		}
		
		request.setAttribute("msg", "블라인드헤제완료");
		request.setAttribute("url", "/admin_Shop.go");
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
		
	}

}
