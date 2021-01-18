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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.UserCommentDAO;
import model.UsercommentBean;

@WebServlet("/user_Comment.do")
public class Shop_User_Comment_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_User_Comment_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("session_id") == null) {
			request.setAttribute("msg", "로그인후 이용할수있습니다.");
			request.setAttribute("url", "/");
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String path = "C:\\jsp\\portfolio2\\WebContent\\shopUpload\\usercomment";
		int size = 10*1024*1024;
		
		MultipartRequest mti = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
		
		String img_1 = "";
		if(mti.getFile("img_1") != null) {
			img_1 = mti.getFilesystemName("img_1");//시스템으로 인해 변경된 파일명			
		}
		UserCommentDAO dao = new UserCommentDAO();
		int fid = dao.showCommentFid();
		fid++;
		UsercommentBean bean = new UsercommentBean();
		bean.setShopuid(Integer.parseInt(mti.getParameter("shopuid")));
		bean.setUsercomment(mti.getParameter("usercomment"));
		bean.setUser((String)session.getAttribute("session_id"));
		bean.setSigndate(sdf.format(date));
		bean.setImg_1(img_1);
		bean.setFid(fid);
		
		dao.userComment(bean);
		
		String url = "/shop_View_Page.go?shopuid="+Integer.parseInt(mti.getParameter("shopuid"));
		request.setAttribute("msg", "댓글 작성완료!");
		request.setAttribute("url", url);
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
		
	}

}
