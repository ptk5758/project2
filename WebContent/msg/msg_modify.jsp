<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<form action="/msg/updatesend.do" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td><input type="hidden" name="msg_uid" value="${bean.msg_uid}"></td>
	</tr>
	<tr>
		<td>받는사람:<input name="recv_user" value="${bean.recv_user}"></td>
	</tr>
	<tr>
		<td>이미지를 다시 첨부하세요</td>
	</tr>
	<tr>
		<td><input type="file" name="img_1"></td>
	</tr>
	<tr>
		<td><textarea style="width:250px; height:250px;" name="contents">${bean.contents}</textarea></td>
	</tr>
	<tr>
		<td><input type="submit" value="보내기"></td>
	</tr>
</table>
</form>

<%@ include file="/include/footer.jsp" %>