package adminController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EventDAO;

@WebServlet("/event_threadUP.do")
public class Admin_Event_ThreadUP_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Event_ThreadUP_Servlet() {
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
		for(String list: eventuid) {
			dao.threadUP(list);
		}
		response.sendRedirect("/admin_EventPage.go");
	}

}
