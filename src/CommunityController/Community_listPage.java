package CommunityController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommunityDao;
import model.CommunityBean;


@WebServlet("/community/community_list.go")
public class Community_listPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Community_listPage() {
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
		String search_title = request.getParameter("search_title");
		String search_contents = request.getParameter("search_contents");
		int list_size = 2;
		int page_num = 1; if(request.getParameter("page_num") != null) { page_num =
		Integer.parseInt(request.getParameter("page_num")); }
		  
		int count = 0;
		 
		
		CommunityDao dao = new CommunityDao();
		count = dao.allcount(title);
		int number = count-(page_num-1)*list_size;
		CommunityBean bean = new CommunityBean();
		ArrayList<CommunityBean> v = new ArrayList<CommunityBean>();
		if(search_contents == null) {
			v = dao.showlist(title);
		}else {
			v = dao.search_listshow(title, search_title, search_contents);
		}
		
		request.setAttribute("v", v);
		request.setAttribute("title", title); 
		request.setAttribute("list_size", list_size); 
		request.setAttribute("page_num", page_num);
		request.setAttribute("count", count); 
		request.setAttribute("number", number); 
		RequestDispatcher dis = request.getRequestDispatcher("/community/community_list.jsp");
		dis.forward(request, response);
	}

}
