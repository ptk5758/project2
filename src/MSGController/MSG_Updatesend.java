package MSGController;

import java.io.IOException;
import java.io.PrintWriter;
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

import DAO.MSGDAO;
import model.MSGClientBean;


@WebServlet("/msg/updatesend.do")
public class MSG_Updatesend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MSG_Updatesend() {
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
		
		String path = "C:\\JSP\\portfolio2\\WebContent\\msgUpload";
		int size = 10*1024*1024;
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
		int msg_uid = Integer.parseInt(multi.getParameter("msg_uid"));
		String recv = multi.getParameter("recv_user");
		String contents = multi.getParameter("contents");
		String img_1 = multi.getParameter("img_1");
		img_1 = multi.getFilesystemName("img_1");
		
		HttpSession session = request.getSession();
		String send = (String)session.getAttribute("session_id");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String signdate = sdf.format(date);
		
		MSGDAO dao = new MSGDAO();
		int answer = dao.idOk(recv);
		if(answer == 1) {
			MSGClientBean bean = new MSGClientBean();
			bean.setMsg_uid(msg_uid);
			bean.setSend_user(send);
			bean.setRecv_user(recv);
			bean.setContents(contents);
			bean.setImg_1(img_1);
			bean.setSend_signdate(signdate);
			dao.updatesend(bean);
			request.setAttribute("msg", "쪽지를 성공적으로 보냈습니다.");
			request.setAttribute("url", "/");
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('존재하지 않는 아이디입니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
			
 
	}

}
