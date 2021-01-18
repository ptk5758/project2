package shopController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BukkitDAO;
import model.BukkitBean;

@WebServlet("/shop_Show_Bukkit.go")
public class Shop_Show_Bukkit_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_Show_Bukkit_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("session_id");
		
		ArrayList<BukkitBean> v = new ArrayList<BukkitBean>();
		BukkitDAO dao = new BukkitDAO();
		v = dao.showBukkit(session_id);
		int total_list = v.size();
		request.setAttribute("total_list", total_list);
		request.setAttribute("v", v);
		RequestDispatcher dis = request.getRequestDispatcher("/shop/shop_Bukkit.jsp");		
		dis.forward(request, response);
	}

}
