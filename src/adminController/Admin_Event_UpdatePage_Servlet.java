package adminController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EventDAO;
import model.EventBean;

@WebServlet("/eventUpdatePage.go")
public class Admin_Event_UpdatePage_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Admin_Event_UpdatePage_Servlet() {
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
		String eventuid = request.getParameter("eventuid");
		
		EventBean bean = new EventBean();
		EventDAO dao = new EventDAO();
		
		bean = dao.getEventInfo(eventuid);
		
		request.setAttribute("bean", bean);
		request.setAttribute("eventuid", eventuid);
		RequestDispatcher dis = request.getRequestDispatcher("/admin/event/eventUpdatePage.jsp");
		dis.forward(request, response);
	}

}
