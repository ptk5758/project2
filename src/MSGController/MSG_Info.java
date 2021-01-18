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

@WebServlet("/msg/msg_info.do")
public class MSG_Info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MSG_Info() {
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
		int msg_uid = Integer.parseInt(request.getParameter("msg_uid"));
		MSGClientBean bean = new MSGClientBean();
		MSGDAO dao = new MSGDAO();
		bean = dao.msgInfo(msg_uid);
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/msg/msg_modify.jsp");
		dis.forward(request, response);
	}

}
