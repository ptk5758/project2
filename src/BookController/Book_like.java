package BookController;

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

import DAO.BookDAO;


@WebServlet("/book/book_like.do")
public class Book_like extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   public Book_like() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int book_uid = Integer.parseInt(request.getParameter("booklike"));
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("session_id");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String like_signdate = sdf.format(date);
		BookDAO dao = new BookDAO();
		if(session_id == null) {
			request.setAttribute("msg", "로그인후 이용하실수있습니다.");
			request.setAttribute("url", "/");
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		int answer = dao.chackLike(book_uid, session_id);
		if(answer == 1) {
			
			dao.itemLikeBack(book_uid, session_id);
			
			response.sendRedirect("/book/book_postshow.do?postshow="+book_uid+"");
		}else {
			
			dao.itemLike(book_uid, session_id, like_signdate);
			
			response.sendRedirect("/book/book_postshow.do?postshow="+book_uid+"");
		}
		
	}

}