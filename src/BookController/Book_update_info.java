package BookController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDAO;
import model.BookBean;


@WebServlet("/book/book_updateinfo.go")
public class Book_update_info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Book_update_info() {
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
		int book_uid = Integer.parseInt(request.getParameter("bookupdate"));
		BookBean bean = new BookBean();
		BookDAO dao = new BookDAO();
		bean = dao.updateinfo(book_uid);
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/book/book_update_page.jsp");
		dis.forward(request, response);
	}

}
