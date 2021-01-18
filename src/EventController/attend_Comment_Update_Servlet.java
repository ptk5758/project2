package EventController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EventDAO;

@WebServlet("/attend_Comment_Update.do")
public class attend_Comment_Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public attend_Comment_Update_Servlet() {
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
		String attenduid = request.getParameter("attenduid");
		String newcommnet = request.getParameter("newComment");
		
		EventDAO dao = new EventDAO();
		
		dao.attendCommentUpdate(attenduid, newcommnet);
		
		request.setAttribute("url", "/attend.view");
		request.setAttribute("msg", "수정완료");
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}
