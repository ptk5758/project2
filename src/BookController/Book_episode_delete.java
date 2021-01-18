package BookController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EpisodeDAO;

@WebServlet("/book/book_episodedelete.do")
public class Book_episode_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Book_episode_delete() {
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
		int ep_uid = Integer.parseInt(request.getParameter("episodedelete"));
		EpisodeDAO dao = new EpisodeDAO();
		dao.episode_delete(ep_uid);
		response.sendRedirect("/book/book_page.jsp");
	}
}
