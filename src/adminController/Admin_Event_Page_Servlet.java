package adminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EventDAO;
import model.EventBean;

@WebServlet("/admin_EventPage.go")
public class Admin_Event_Page_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Event_Page_Servlet() {
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
		
		ArrayList<EventBean> v = new ArrayList<EventBean>();
		EventDAO dao = new EventDAO();
		
		v = dao.getAdminEventList();
		
		request.setAttribute("eventCount", v.size());
		request.setAttribute("v", v);
		RequestDispatcher dis = request.getRequestDispatcher("/admin/event/admin_EventPage.jsp");
		dis.forward(request, response);
	}

}
