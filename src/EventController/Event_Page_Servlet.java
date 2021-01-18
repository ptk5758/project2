package EventController;

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

@WebServlet("/eventPage.go")
public class Event_Page_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Event_Page_Servlet() {
        super();
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
		v = dao.getEventList();
		
		request.setAttribute("v", v);
		RequestDispatcher dis = request.getRequestDispatcher("/event/eventPage.jsp");
		dis.forward(request, response);
	}

}
