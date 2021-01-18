package MSGController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MSGDAO;
import model.MSGClientBean;

@WebServlet("/msg/sendbox.go")
public class MSG_Sendboxshow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public MSG_Sendboxshow() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<MSGClientBean> v = new ArrayList<MSGClientBean>();
		HttpSession session = request.getSession();
		String send = (String)session.getAttribute("session_id");
		MSGDAO dao = new MSGDAO();
		v = dao.showSendList(send);
		request.setAttribute("v", v);
		RequestDispatcher dis = request.getRequestDispatcher("/msg/sendbox.jsp");
		dis.forward(request, response);
	}
}
