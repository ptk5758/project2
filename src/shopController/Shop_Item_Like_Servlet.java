package shopController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ShopDAO;

@WebServlet("/shopItem_like.do")
public class Shop_Item_Like_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_Item_Like_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shopuid = request.getParameter("shopuid");
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("session_id");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String like_signdate = sdf.format(date);
		ShopDAO dao = new ShopDAO();
		if(session_id == null) {
			request.setAttribute("msg", "로그인후 이용하실수있습니다.");
			request.setAttribute("url", "/");
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		int answer = dao.chackLike(shopuid, session_id);
		if(answer == 1) {
			
			dao.itemLikeBack(shopuid, session_id);
			
			response.sendRedirect("/shop_View_Page.go?shopuid="+shopuid);
		}else {
			
			dao.itemLike(shopuid, session_id, like_signdate);
			
			response.sendRedirect("/shop_View_Page.go?shopuid="+shopuid);
		}
		
	}

}
