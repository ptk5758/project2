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
import DAO.BookMarkDAO;
import DAO.DAO;
import model.BookBean;
import model.BookMarkBean;


@WebServlet("/book/book_mark.go")
public class Book_mark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Book_mark() {
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
		int book_uid = Integer.parseInt(request.getParameter("bookmark"));
		
		HttpSession session = request.getSession();
		String session_name = (String)session.getAttribute("session_name");
		
		int check = 0;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(session_name == null) {
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			request.setAttribute("url", "/");
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}else {
			BookBean bookbean = new BookBean();
			BookDAO bookdao = new BookDAO();
			BookMarkDAO bmdao = new BookMarkDAO();
			
			check = bmdao.addfavorite(book_uid, session_name);
			if(check == 1) {
				request.setAttribute("msg", "이미 즐겨찾기 한 책입니다.");
				request.setAttribute("url", "/");
				RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
				dis.forward(request, response);
			}else {
				bookbean = bookdao.addbean(book_uid);
				
				BookMarkBean bmbean = new BookMarkBean();
				
				bmbean.setBook_uid(bookbean.getBook_uid());
				bmbean.setBm_subject(bookbean.getBook_subject());
				bmbean.setBm_user(session_name);
				bmbean.setBm_signdate(sdf.format(date));
				
				bmdao.bminsert(bmbean);
				
				request.setAttribute("msg", "즐겨찾기에 추가되었습니다.");
				request.setAttribute("url", "/");
				RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
				dis.forward(request, response);
			}
		}
		
	}

}
