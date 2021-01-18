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
import DAO.EpisodeDAO;
import model.BookBean;
import model.EpisodeBean;

@WebServlet("/book/book_postshow.do")
public class Book_post_show extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Book_post_show() {
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
		int book_uid = Integer.parseInt(request.getParameter("postshow"));
		BookBean bean = new BookBean();
		BookDAO dao = new BookDAO();
		dao.refcnt(book_uid);
		bean = dao.book_postshow(book_uid);
		ArrayList<EpisodeBean> v = new ArrayList<EpisodeBean>();
		
		EpisodeDAO dao1 = new EpisodeDAO();
		v = dao1.getEpisodeList(book_uid);
		int total_count = v.size();
		int likestack = dao.likeStack(book_uid);
		request.setAttribute("bean", bean);
		request.setAttribute("v", v);
		request.setAttribute("total_count", total_count);
		request.setAttribute("likestack", likestack);
		RequestDispatcher dis = request.getRequestDispatcher("/book/book_postshow.jsp");
		dis.forward(request, response);
	}
}
