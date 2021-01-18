package adminController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AdminDAO;

@WebServlet("/admin_Shop_Blind.do")
public class Admin_Shop_Blind_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Shop_Blind_Servlet() {
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
		String values[] = request.getParameterValues("shopuid");
		AdminDAO dao = new AdminDAO();
		for(String shopuid : values) {
			dao.listBlind(shopuid);
		}
		request.setAttribute("msg", "블라인드처리완료");
		request.setAttribute("url", "/admin_Shop.go");
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}
