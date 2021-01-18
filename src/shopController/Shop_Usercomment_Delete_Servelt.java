package shopController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserCommentDAO;

@WebServlet("/usercomment_Delete.do")
public class Shop_Usercomment_Delete_Servelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_Usercomment_Delete_Servelt() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentuid = request.getParameter("commentuid");
		String shopuid = request.getParameter("shopuid");
		UserCommentDAO dao = new UserCommentDAO();
		dao.usercommentDelete(commentuid);
		
		request.setAttribute("msg", "댓글삭제 완료");
		request.setAttribute("url", "/shop_View_Page.go?shopuid="+shopuid);
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}

}
