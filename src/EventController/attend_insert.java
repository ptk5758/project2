package EventController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.EventDAO;

@WebServlet("/attend.do")
public class attend_insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public attend_insert() {
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
		String comment = request.getParameter("attend_comment");
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("session_id");
		String nickname = (String)session.getAttribute("session_nickname");
		Date date = new Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String signdate = cal.format(date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String signdate2 = sdf.format(date);
		EventDAO dao = new EventDAO();
		int answer = dao.attend_check(signdate2, user);
		if(answer != 1) {		
			dao.insert_attend(comment, user, signdate, nickname);
			response.sendRedirect("/attend.view");
		}else {
			request.setAttribute("msg", "출석체크를 이미 하였습니다.");
			request.setAttribute("url", "/attend.view");
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
	}

}
