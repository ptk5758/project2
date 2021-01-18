<%@page import="DAO.AdminDAO"%>
<%@page import="model.ShopBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String signdate1 = request.getParameter("signdate1");
	String signdate2 = request.getParameter("signdate2");
	
	AdminDAO dao = new AdminDAO();
	ArrayList<ShopBean> v = new ArrayList<>();
	v = dao.dateSelect(signdate1, signdate2);
	
	int likeCount[] = new int[v.size()];
	for(int i=0; i<likeCount.length; i++) {
		likeCount[i] = dao.likeCheck(v.get(i).getShopuid());
	}
	
	request.setAttribute("likeCount", likeCount);
	request.setAttribute("v", v);
%>
				<c:set var="i" value="0" />
				<c:forEach var="shopList" items="${v }">
					<div class="admin_ShopList">
						<div class="admin_Shop_Num">No.1</div>
						<div class="admin_Shop_Img">
								<c:if test="${shopList.active == '1'}"><img src="/shopUpload/${shopList.img_1 }"></c:if>
								<c:if test="${shopList.active == '2'}">
									<img style="filter: blur(3px)" src="/shopUpload/${shopList.img_1 }">
									<div class="blind">블라인드 처리됀 글입니다.</div>
								</c:if>
							</div>
						<div class="admin_Shop_Name">${shopList.itemname }</div>
						<div class="admin_Shop_User">${shopList.user }</div>
						<div class="admin_Shop_Option">-</div>
						<div class="admin_Shop_Price">${shopList.itemprice }</div>
						<div class="admin_Shop_Signdate">${fn:substring(shopList.signdate,0,10)}</div>
						<div class="admin_Shop_Ref"><b>${shopList.ref }</b></div>	
						<div class="admin_Shop_Like"><b>${likeCount[i] }</b></div>
						<div class="admin_Shop_Check"><input type="checkbox"></div>	
					</div>
					<c:set var="i" value="${i+1 }" />
				</c:forEach>