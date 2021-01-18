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

@WebServlet("/msg/send.do")
public class MSG_send extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MSG_send() {
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
		String recv = multi.getParameter("recv_user");
		String contents = multi.getParameter("contents");
		
		String img_1 = "";
		if(multi.getFile("img_1") != null) {
			img_1 = multi.getFilesystemName("img_1");//시스템으로 인해 변경된 파일명			
		}
		
		
		HttpSession session = request.getSession();
		String send = (String)session.getAttribute("session_id");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String signdate = sdf.format(date);
		
		MSGDAO dao = new MSGDAO();
		int answer = dao.idOk(recv);
		if(answer == 1) {
			dao.send(send, recv, contents, img_1, signdate);
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
