package BookController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EpisodeDAO;
import model.EpisodeBean;

@WebServlet("/book/book_episodeshow.do")
public class Book_episode_show extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Book_episode_show() {
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
		int ep_uid = Integer.parseInt(request.getParameter("episodeshow"));
		EpisodeBean bean = new EpisodeBean();
		EpisodeDAO dao = new EpisodeDAO();
		dao.refcnt(ep_uid);
		bean = dao.story_view(ep_uid);
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/book/book_storyshow.jsp");
		dis.forward(request, response);
	}

}
