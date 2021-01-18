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

@WebServlet("/eventUpdate.do")
public class Admin_Event_Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Event_Update_Servlet() {
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
		request.setCharacterEncoding("UTF-8");
		String eventuid = request.getParameter("eventuid");
		EventBean bean = new EventBean();
		bean.setContents(request.getParameter("contents"));
		bean.setSubject(request.getParameter("subject"));
		bean.setPeriod2(request.getParameter("period1"));
		bean.setPeriod1(request.getParameter("period2"));
		bean.setFont_Color(request.getParameter("font_Color"));
		bean.setFont_Family(request.getParameter("font_Family"));
		bean.setFont_size(Integer.parseInt(request.getParameter("font_Size")));
		
		EventDAO dao = new EventDAO();
		dao.eventUpdate(bean, eventuid);
		
		request.setAttribute("msg", "수정완료");
		request.setAttribute("url", "/admin_EventPage.go");
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}
