package BookController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDAO;
import DAO.EpisodeDAO;

@WebServlet("/book/book_delete.do")
public class Book_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Book_delete() {
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
		int book_uid = Integer.parseInt(request.getParameter("bookdelete"));
		BookDAO dao = new BookDAO();
		EpisodeDAO dao1 = new EpisodeDAO();
		dao.book_delete(book_uid);
		dao1.episode_plus_delete(book_uid);
		response.sendRedirect("/book/book_page.jsp");
	}

}
