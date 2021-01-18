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

@WebServlet("/book/book_episodeupdateinfo.go")
public class Book_episode_update_info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Book_episode_update_info() {
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
		int ep_uid = Integer.parseInt(request.getParameter("episodeupdate"));
		EpisodeBean bean = new EpisodeBean();
		EpisodeDAO dao = new EpisodeDAO();
		bean = dao.epupdateinfo(ep_uid);
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/book/book_episode_update_page.jsp");
		dis.forward(request, response);
	}
}
