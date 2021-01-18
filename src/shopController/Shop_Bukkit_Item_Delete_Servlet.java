package shopController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BukkitDAO;

@WebServlet("/bukkit_item_delete.do")
public class Shop_Bukkit_Item_Delete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_Bukkit_Item_Delete_Servlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bukkituid = request.getParameter("bukkituid");
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("session_id");
		
		BukkitDAO dao = new BukkitDAO();		
		dao.itemDelete(bukkituid, session_id);
		response.sendRedirect("/shop_Show_Bukkit.go");
	}

}
