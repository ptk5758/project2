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

import DAO.BukkitDAO;
import DAO.ShopDAO;
import model.BukkitBean;
import model.ShopBean;

@WebServlet("/bukkit_Add.do")
public class Shop_Bukkit_Add_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_Bukkit_Add_Servlet() {
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
		String url = request.getParameter("url");
		int itemcount = Integer.parseInt(request.getParameter("itemcount"));
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("session_id");
		
		int answer = 0;
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:dd");
		
		if(session_id == null) {
			request.setAttribute("msg", "회원가입후 이용하실수 있습니다.");
			request.setAttribute("url", "/");
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}else {
			ShopBean shopbean = new ShopBean();
			ShopDAO shopdao = new ShopDAO();
			BukkitDAO bukkitdao = new BukkitDAO();
			
			answer = bukkitdao.checkItem(shopuid, session_id);
			if(answer == 1) {
				bukkitdao.plusItem(shopuid, session_id, itemcount);
			}else {
				
				shopbean = shopdao.bukkit_item_bean(shopuid);
				
				BukkitBean bukkitbean = new BukkitBean();
				
				bukkitbean.setShopuid(shopbean.getShopuid());
				bukkitbean.setImg_1(shopbean.getImg_1());
				bukkitbean.setImg_s(shopbean.getImg_s());
				
				bukkitbean.setItemname(shopbean.getItemname());
				bukkitbean.setItemprice(shopbean.getItemprice());
				bukkitbean.setPostinguser(shopbean.getUser());
				
				bukkitbean.setPostingsigndate(shopbean.getSigndate());
				bukkitbean.setUser(session_id);
				bukkitbean.setSigndate(sdf.format(date));
				
				bukkitbean.setItemcount(itemcount);
				bukkitdao.bukkitAdd(bukkitbean);
				
			}
			
			
			String url2 = url+"?shopuid="+shopuid;
			request.setAttribute("msg", "장바구니에 추가되었습니다.");
			request.setAttribute("url", url2);
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
			
		}
	}

}
