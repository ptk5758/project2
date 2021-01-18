package MSGController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MSGDAO;

@WebServlet("/msg/sendDelete.do")
public class MSG_SendDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MSG_SendDelete() {
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
		MSGDAO dao = new MSGDAO();
		dao.msgsendDelete(msg_uid);
		request.setAttribute("msg", "메일이 삭제되었습니다.");
		request.setAttribute("url", "/");
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}
