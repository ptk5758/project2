package BookController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import DAO.EpisodeDAO;
import model.EpisodeBean;

@WebServlet("/book/book_writing.do")
@MultipartConfig(
		fileSizeThreshold=0,
		location="C:\\JSP\\portfolio2\\WebContent\\bookUpload"
)
public class Book_writing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Book_writing() {
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
		int uid = Integer.parseInt(request.getParameter("uid"));
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		
		HttpSession session = request.getSession();
		String session_name = (String)session.getAttribute("name");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String signdate = sdf.format(date);
		
		String img1="";
		String img2="";
		String img3="";
		for(Part part:request.getParts())
		{
			
			  Date today = new Date(); 
			  SimpleDateFormat timer = new SimpleDateFormat("yyyyMMddHHmmss"); 
			  String clock = timer.format(today);

			if((part.getName().equals("img_1") || part.getName().equals("img_2") || part.getName().equals("img_3"))&&part.getSize()>0) {
				String contentDisposition = part.getHeader("content-disposition");
				String fileName = clock+"_"+getUploadFileName(contentDisposition);
				part.write(fileName);
				
				if(part.getName().equals("img_1")) {
					img1 = fileName;
				}
				if(part.getName().equals("img_2")) {
					img2 = fileName;
				}
				if(part.getName().equals("img_3")) {
					img3 = fileName;
				}
			}
		}
		EpisodeBean bean = new EpisodeBean();
		bean.setBook_uid(uid);
		bean.setEp_subject(subject);
		bean.setEp_contents(contents);
		bean.setEp_signdate(signdate);
		bean.setEp_img1(img1);
		bean.setEp_img2(img2);
		bean.setEp_img3(img3);
		bean.setEp_writer(session_name);
		
		EpisodeDAO dao = new EpisodeDAO();
		dao.book_writing(bean);
		response.sendRedirect("/book/book_postshow.do?postshow="+uid+"");
	}
	
	private String getUploadFileName(String contentDisposition) {
		String uploadFileName = null;
		String[] contentSplitStr = contentDisposition.split(";");
		int firstQutosIndex = contentSplitStr[2].indexOf("\"");
		int lastQutosIndex = contentSplitStr[2].lastIndexOf("\"");
		uploadFileName=contentSplitStr[2].substring(firstQutosIndex+1,lastQutosIndex);

		return uploadFileName;
	}

}
