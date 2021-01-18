package EventController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EventDAO;
import model.EventBean;

@WebServlet("/eventViewPage.go")
public class Event_ViewPage_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Event_ViewPage_Servlet() {
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
		dao.refUP(eventuid);
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/event/eventViewPage.jsp");
		dis.forward(request, response);
	}

}
