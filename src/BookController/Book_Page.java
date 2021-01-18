package BookController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDAO;
import model.BookBean;

@WebServlet("/book/book_page.go")
public class Book_Page extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Book_Page() {
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
		String title = request.getParameter("title");
		ArrayList<BookBean> v = new ArrayList<BookBean>();
		BookDAO dao = new BookDAO();
		v = dao.getContentsList(title);
		
		request.setAttribute("v", v);
		RequestDispatcher dis = request.getRequestDispatcher("/book/book_page.jsp");
		dis.forward(request, response);
	}
}
