package MSGController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MSGDAO;
import model.MSGClientBean;

@WebServlet("/msg/msg_sendshow.do")
public class MSG_Sendshow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MSG_Sendshow() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int msg_uid = Integer.parseInt(request.getParameter("sendpostshow"));
		MSGClientBean bean = new MSGClientBean();
		MSGDAO dao = new MSGDAO();
		bean = dao.msgPostShow(msg_uid);
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/msg/msg_sendview.jsp");
		dis.forward(request, response);
	}
}
