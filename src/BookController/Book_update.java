package BookController;

import java.awt.Image;
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

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiUtils;

import DAO.BookDAO;
import model.BookBean;

@WebServlet("/book/book_update.do")
@MultipartConfig(
		fileSizeThreshold=0,
		location="C:\\JSP\\portfolio2\\WebContent\\bookUpload"
)
public class Book_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Book_update() {
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
		int book_uid = Integer.parseInt(request.getParameter("uid"));
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		
		HttpSession session = request.getSession();
		String session_name = (String)session.getAttribute("name");
		
		String img1="";
		String img2="";
		String img3="";
		String thumImg="";
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
				
				String filePath = "C:\\JSP\\portfolio2\\WebContent\\bookUpload\\";
				
				String orgImg = filePath+img1;
				thumImg = "thumb_"+img1;
				String thumbImg = filePath+thumImg;
				
				int thumWidth = 180;
				int thumHeight = 180;
				
				try {
					Image thumbnail = JimiUtils.getThumbnail(orgImg, thumWidth, thumHeight, Jimi.IN_MEMORY);
					Jimi.putImage(thumbnail, thumbImg);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		BookBean bean = new BookBean();
		bean.setBook_writer(session_name);
		bean.setBook_title(title);
		bean.setBook_genre(genre);
		bean.setBook_subject(subject);
		bean.setBook_contents(contents);
		bean.setBook_img1(img1);
		bean.setBook_img2(img2);
		bean.setBook_img3(img3);
		bean.setBook_thumb(thumImg);
		bean.setBook_uid(book_uid);
		
		BookDAO dao = new BookDAO();
		dao.book_update(bean);
		response.sendRedirect("/book/book_page.go?title="+title+"");
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