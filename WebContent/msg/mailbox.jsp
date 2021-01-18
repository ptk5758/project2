<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	function msgpostshow(msguid){
		location.href = "/msg/msg_postshow.do?msgpostshow="+msguid+"";
	}
</script>
<table width="80%">
  	<tr>
    	<td width="10%">보낸사람</td>
    	<td width="40%">내용</td>
    	<td width="10%">파일</td>
    	<td width="20%">날짜</td>
  	</tr>
  	<c:forEach var="list" items="${v}">	
		<c:choose>
			<c:when test="${list.active == 1}">
				<tr style="cursor: pointer" onclick="msgpostshow('${list.msg_uid}')">
  					<td>${list.send_user}</td>
  					<td>${list.contents}</td>
  					<td>${list.img_1}</td>
  					<td>${list.send_signdate}</td>
  					<form action="/msg/delete.do" method="post">
  						<td><input type="hidden" name="msg_uid" value="${list.msg_uid}">
  						<td><input type="submit" value="삭제"></td>
  					</form>
  				</tr>
  			</c:when>
  			<c:otherwise>
  				<tr id="hidden" style="display:none;">
  					<td>${list.send_user}</td>
  					<td>${list.contents}</td>
  					<td>${list.img_1}</td>
  					<td>${list.send_signdate}</td>
  				</tr>
  			</c:otherwise>
  		</c:choose>
  	</c:forEach>
</table>
<table width="20%">
	<tr>
		<form action="/msg/mail.go" method="post">
		<td><input type="submit" value="쪽지쓰기"></td>
		</form>
	</tr>
	<tr>
		<form action="/msg/sendbox.go" method="post">
		<td><input type="submit" value="보낸편지함">
		</form>
	</tr>
</table>

<%@ include file = "/include/footer.jsp" %>