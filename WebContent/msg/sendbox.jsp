<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	function sendpostshow(msguid){
		location.href = "/msg/msg_sendshow.do?sendpostshow="+msguid+"";
	}
</script>
<table width="80%">
  	<tr>
    	<td width="10%">받는사람</td>
    	<td width="40%">내용</td>
    	<td width="10%">파일</td>
    	<td width="20%">날짜</td>
  	</tr>
  	<c:forEach var="list" items="${v}">
		<c:choose>
			<c:when test="${list.active_2 == 1}">
				<tr style="cursor: pointer" onclick="sendpostshow('${list.msg_uid}')">
		  			<td>${list.recv_user}</td>
		  			<td>${list.contents}</td>
		  			<c:if test="${list.img_1 != ''}">
		  				<td>${list.img_1}</td>
		  			</c:if>
		  			<td>${list.send_signdate}</td>
		  			<td>
		  				<c:choose>
		  					<c:when test="${list.read_check == 0}">
		  						안읽음
		  					</c:when>
		  					<c:otherwise>
		  						읽음
		  					</c:otherwise>
		  				</c:choose>
		  			</td>
		  			<c:if test="${list.read_check == 0}">
		  				<form action="/msg/msg_info.do" method="post">
		  					<td><input type="hidden" name="msg_uid" value="${list.msg_uid}"></td>
							<td><input type="submit" value="수정"></td>
						</form>
					</c:if>
					<form action="/msg/sendDelete.do" method="post">
		  				<td><input type="hidden" name="msg_uid" value="${list.msg_uid}">
		  				<td><input type="submit" value="삭제"></td>
		  			</form>
				</tr>
			</c:when>
			<c:otherwise>
  				<tr id="hidden" style="display:none;">
  					<td>${list.recv_user}</td>
  					<td>${list.contents}</td>
  					<td>${list.img_1}</td>
  					<td>${list.send_signdate}</td>
  				</tr>
  			</c:otherwise>
		</c:choose>
  	</c:forEach>
</table>
<%@ include file = "/include/footer.jsp" %>
