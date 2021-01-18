<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp" %>
<form action="/msg/send.do" method="post" enctype="multipart/form-data">
<div>
<table width="100%">
	<tr>
		<td>받는 사람:<input name="recv_user"></td>
	</tr>
	<tr>	
		<td><textarea style="width:250px; height:250px;" name="contents" placeholder="메세지를 입력하세요."></textarea></td>
		<td><input type="file" name="img_1"></td>
		<td><input type="submit" value="보내기"></td>
	</tr>
</table>
</div>
</form>
<%@ include file = "/include/footer.jsp" %>