package CommunityController;

import java.awt.Image;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/community/post_modify.do")
@MultipartConfig(
		fileSizeThreshold=0,
		location="C:\\JSP\\portfolio2\\WebContent\\communityupload"

)
public class Community_post_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Community_post_update() {
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
		int communityuid = Integer.parseInt(request.getParameter("communityuid"));
		String title = request.getParameter("title");
		String subject = request.getParameter("subject");
		String secret = request.getParameter("secret");
		String contents = request.getParameter("contents");
		
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("id");
		
		
		
		String file1="";
		String file2="";
		String file3="";
		String thumFile="";
		
		for(Part part:request.getParts()) {
			Date date = new Date();
			SimpleDateFormat cal = new SimpleDateFormat("yyyyMMddHHmmss");
			String signdate = cal.format(date);
			
			if((part.getName().equals("file_1") || part.getName().equals("file_2") || part.getName().equals("file_3"))&&part.getSize()>0) {
				String contentDisposition = part.getHeader("content-disposition");
				String fileName = signdate+"_"+getUploadFileName(contentDisposition);
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
				
				String filePath="C:\\JSP\\portfolio2\\WebContent\\communityupload";
				
				String orgImg = filePath+file1;
				thumFile = "thumb_"+file1;
				String thumbImg = filePath+thumFile;
				
				int thumbWidth = 180;
				int thumbHeight = 180;
				
				try {
					Image thumbnail = JimiUtils.getThumbnail(orgImg, thumbWidth, thumbHeight, Jimi.IN_MEMORY);
					Jimi.putImage(thumbnail, thumbImg);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		
		CommunityBean bean = new CommunityBean();
		bean.setCommunityuid(communityuid);
		bean.setUser(session_id);
		bean.setTitle(title);
		bean.setSubject(subject);
		bean.setSecret(secret);
		bean.setContents(contents);
		bean.setFile_1(file1);
		bean.setFile_2(file2);
		bean.setFile_3(file3);
		bean.setFile_s(thumFile);
		
		CommunityDao dao = new CommunityDao();
		dao.postchange(bean);
		
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/community/community_postview.jsp");
		dis.forward(request, response);
	}
	private String getUploadFileName(String contentDisposition) {
		String uploadFileName = null;
		String[] contentSplitStr = contentDisposition.split(";");
		//사용자 브라우저가 크롬계열일 경우
		int firstQutosIndex = contentSplitStr[2].indexOf("\"");
		int lastQutosIndex = contentSplitStr[2].lastIndexOf("\"");
		uploadFileName=contentSplitStr[2].substring(firstQutosIndex+1,lastQutosIndex);
		
		return uploadFileName;
	}

}
