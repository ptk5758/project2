<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String title = request.getParameter("title");
%>
<select name="itemcategory">
		<%if(title.equals("accessories")){%>
		<option value="seal">스티커</option>
		<%}else if(title.equals("clothes")){ %>
		<option value="hood">후드티</option>
		<option value="2">신발</option>
		<option value="2">맨투맨</option>
		<option value="hat">모자</option>
		<%}else if(title.equals("household")){%>
		<option value="cup">머그컵</option>
		<option value="mousepad">마우스패드</option>
		<option value="2">가방</option>
		<%}else{ %>
		<option value="2">기타</option>
		<%} %>
</select>
