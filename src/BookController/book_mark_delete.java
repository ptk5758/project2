package BookController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookMarkDAO;

@WebServlet("/book/book_mark_delete.do")
public class book_mark_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public book_mark_delete() {
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
		
		int book_uid = Integer.parseInt(request.getParameter("bookmarkdelete"));
		HttpSession session = request.getSession();
		String session_name = (String)session.getAttribute("name");
		
		BookMarkDAO dao = new BookMarkDAO();
		dao.deletebookmark(book_uid, session_name);
		response.sendRedirect("/showbookmark.go");
	}
}
