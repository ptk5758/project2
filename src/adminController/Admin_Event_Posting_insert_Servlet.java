package adminController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.EventDAO;
import model.EventBean;


@WebServlet("/eventPostingInsert.do")
@MultipartConfig(fileSizeThreshold=0,location="C:\\jsp\\portfolio2\\WebContent\\admin\\event\\img")
public class Admin_Event_Posting_insert_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Event_Posting_insert_Servlet() {
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
		if(!session.getAttribute("session_level").equals("9")) {
			request.setAttribute("msg", "관리자만 접근가능");
			request.setAttribute("url", "/");
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		String img_B ="";
		String img_1 ="";
		String img_2 ="";
		String img_3 ="";
		
		for(Part part: request.getParts()) {
			if(part.getSize() > 0 && (part.getName().equals("file_B") || part.getName().equals("file_1") || part.getName().equals("file_2") || part.getName().equals("file_3"))) {
				String contentDisposition = part.getHeader("content-disposition");
				String fileName = getUploadFileName(contentDisposition);
				part.write(fileName);
				
				if(part.getName().equals("file_B")) {
					img_B = fileName;
				}
				if(part.getName().equals("file_1")) {
					img_1 = fileName;
				}
				if(part.getName().equals("file_2")) {
					img_2 = fileName;
				}
				if(part.getName().equals("file_3")) {
					img_3 = fileName;
				}
			}
		}
		
		
		String user = (String)session.getAttribute("session_nickname");
		EventBean bean = new EventBean();
		bean.setSubject(request.getParameter("subject"));
		bean.setContents(request.getParameter("contents"));
		bean.setFont_size(Integer.parseInt(request.getParameter("font_Size")));
		bean.setFont_Family(request.getParameter("font_Family"));
		bean.setFont_Color(request.getParameter("font_Color"));
		bean.setUser(user);
		bean.setFile_B(img_B);
		bean.setFile_1(img_1);
		bean.setFile_2(img_2);
		bean.setFile_3(img_3);
		bean.setPeriod1(request.getParameter("period1"));
		bean.setPeriod2(request.getParameter("period2"));
		EventDAO dao = new EventDAO();
		
		dao.eventPosting(bean);
		
		request.setAttribute("msg", "업로드 완료!");
		request.setAttribute("url", "/admin_EventPage.go");
		RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
		dis.forward(request, response);
	}
	
	private String getUploadFileName(String contentDisposition) {
		
		String uploadFileName = null;

		String[] contentSplitStr = contentDisposition.split(";");

		int firstQutosIndex = contentSplitStr[2].indexOf("\"");

		int lastQutosIndex = contentSplitStr[2].lastIndexOf("\"");

		uploadFileName = contentSplitStr[2].substring(firstQutosIndex + 1, lastQutosIndex);

		return uploadFileName;
	}

}
