<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<table>
	<tr>
		<td><input type="hidden" name="msg_uid" value="${bean.msg_uid}"></td>
	</tr>
	<tr>
		<td>받는사람: ${bean.recv_user}</td>
	</tr>
	<tr>
	<c:if test="${bean.img_1 != ''}">
		<td><img src = "/msgUpload/${bean.img_1}"></td>
	</c:if>	
	</tr>
	<tr>
		<td>${bean.contents}</td>
	</tr>
</table>
</form>
<%@ include file="/include/footer.jsp" %>