package shopController;

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

import DAO.ShopDAO;
import model.ShopBean;

@WebServlet("/shop_list_Update.do")
@MultipartConfig(fileSizeThreshold=0,location="C:\\jsp\\portfolio2\\WebContent\\shopUpload")
public class Shop_List_Update_do_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_List_Update_do_Servlet() {
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
		int shopuid = Integer.parseInt(request.getParameter("shopuid"));
		String img_1 = "";
		String img_2 = "";
		String img_3 = "";
		String img_s = "";
		
		for(Part part : request.getParts()) {
			if(part.getSize() > 0 && (part.getName().equals("img_1") || part.getName().equals("img_2") || part.getName().equals("img_3"))) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String contentDisposition = part.getHeader("content-disposition");
				String fileName = sdf.format(date)+getUploadFileName(contentDisposition);
				part.write(fileName);
				
				if(part.getName().equals("img_1")) {
					img_1 = fileName;
				}
				if(part.getName().equals("img_2")) {
					img_2 = fileName;
				}
				if(part.getName().equals("img_3")) {
					img_3 = fileName;
				}
				
				if(img_1 != "") {
					String thum_path = "C:\\jsp\\portfolio2\\WebContent\\shopUpload\\";
					String o_img = thum_path+img_1;
					img_s = "thum_"+img_1;
					String thumimg = thum_path+img_s;
					int thumwidth = 150;
					int thumheight = 150;
					
					try {
						Image thumbnail = JimiUtils.getThumbnail(o_img, thumwidth, thumheight,Jimi.IN_MEMORY);
						Jimi.putImage(thumbnail, thumimg);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		ShopBean bean = new ShopBean();
		
		bean.setItemsubject(request.getParameter("itemsubject"));
		bean.setItemname(request.getParameter("itemname"));		
		bean.setItemprice(request.getParameter("itemprice"));
		
		bean.setItemcomment(request.getParameter("itemcomment"));
		bean.setImg_1(img_1);		
		bean.setImg_2(img_2);
		
		bean.setImg_3(img_3);
		bean.setImg_s(img_s);
		bean.setShopuid(shopuid);
		
		ShopDAO dao = new ShopDAO();
		dao.shopListUpdate(bean);
		
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
