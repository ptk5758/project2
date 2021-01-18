package BookController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookMarkDAO;
import model.BookMarkBean;

@WebServlet("/headerbookmark.do")
public class headerbookmark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public headerbookmark() {
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
		HttpSession session = request.getSession();
		String session_name = (String)session.getAttribute("name");
		
		ArrayList<BookMarkBean> v = new ArrayList<BookMarkBean>();
		BookMarkDAO dao = new BookMarkDAO();
		v = dao.headerfavorit(session_name);
		request.setAttribute("value", v);
		RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");		
		dis.forward(request, response);
	}
}
