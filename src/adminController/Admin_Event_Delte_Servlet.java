package adminController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EventDAO;

@WebServlet("/event_Delete.do")
public class Admin_Event_Delte_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Event_Delte_Servlet() {
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
		String eventuid[] = request.getParameterValues("eventuid");
		EventDAO dao = new EventDAO();
		for(String eventvalue : eventuid) {
			dao.eventDelete(eventvalue);
		}
		request.setAttribute("url", "/admin_EventPage.go");
		request.setAttribute("msg", "이벤트 삭제완료");
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}
