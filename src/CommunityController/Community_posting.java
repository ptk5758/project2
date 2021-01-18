package CommunityController;

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

import DAO.CommunityDao;
import model.CommunityBean;

@WebServlet("/community/posting.do")
@MultipartConfig(
		fileSizeThreshold=0,
		location="C:\\JSP\\portfolio2\\WebContent\\communityupload"
)
public class Community_posting extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Community_posting() {
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
		String title = request.getParameter("title");
		String subject = request.getParameter("subject");
		String secret = request.getParameter("secret");
		String contents = request.getParameter("contents");
		
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("session_id");
		
		Date date = new Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
		String signdate = cal.format(date);
		
		String file1="";
		String file2="";
		String file3="";
		String thumFile="";
		for(Part part:request.getParts())
		{
			
			  Date today = new Date(); SimpleDateFormat timer = new
			  SimpleDateFormat("yyyyMMddHHmmss"); 
			  String clock = timer.format(today);

			if((part.getName().equals("file_1") || part.getName().equals("file_2") || part.getName().equals("file_3"))&&part.getSize()>0) {
				String contentDisposition = part.getHeader("content-disposition");
				String fileName = clock+"_"+getUploadFileName(contentDisposition);
				part.write(fileName);
				
				if(part.getName().equals("file_1")) {
					file1 = fileName;
				}
				if(part.getName().equals("file_2")) {
					file2 = fileName;
				}
				if(part.getName().equals("file_3")) {
					file3 = fileName;
				}
				
				String filePath = "C:\\JSP\\portfolio2\\WebContent\\communityupload\\";
				
				String orgImg = filePath+file1;
				thumFile = "thumb_"+file1;
				String thumbImg = filePath+thumFile;
				
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
		
		CommunityBean bean = new CommunityBean();
		bean.setTitle(title);
		bean.setSubject(subject);
		bean.setSecret(secret);
		bean.setContents(contents);
		bean.setUser(session_id);
		bean.setSigndate(signdate);
		bean.setFile_1(file1);
		bean.setFile_2(file2);
		bean.setFile_3(file3);
		bean.setFile_s(thumFile);
		CommunityDao dao = new CommunityDao();
		dao.posting(bean);
		response.sendRedirect("/community/community_list.go?title="+title+"");
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
