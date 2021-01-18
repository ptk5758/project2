<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file = "/include/header.jsp" %>


<div>
<table style="width:1200px;">
	<tr>
		<td></td>
	</tr>
	<tr>
		<td><input type="hidden" name = "communityuid" value="${bean.communityuid}"></td>
	</tr>
	<tr>
		<td>분류:${bean.title}</td>
	</tr>
	<tr>
		<td>글제목:${bean.subject}</td>
	</tr>
	<tr>
		<td>작성자:${bean.user}</td>
	</tr>
	<tr>
	<c:if test="${bean.file_1 != ''}">
		<td><img src = "/communityupload/${bean.file_1}"></td>
	</c:if>
	</tr>
	<tr>
	<c:if test="${bean.file_2 != ''}">
		<td><img src = "/communityupload/${bean.file_2}"></td>
	</c:if>
	</tr>
	<tr>
	<c:if test="${bean.file_3 != ''}">
		<td><img src = "/communityupload/${bean.file_3}"></td>
	</c:if>
	</tr>	
	<tr>
		<td>${bean.contents}</td>
	</tr>
</table>

<form action="/community/community_post_info.do?uid=${bean.communityuid}" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<c:if test="${bean.user == session_id}">
			<td>
				<input type="submit" value="글수정">
			</td>
		</c:if>
	</tr>
</table>
</form>
<table align="left"> 
	<tr>
		<td><a href="/community/community_posting.jsp">글쓰기</a></td>
	</tr>
</table>
<table align="right">
	<tr>
		<td><a href="/community/community_list.go?title=${bean.title}">목록</a></td>
	</tr>
</table>
						

<form method="post" action="/community/user_comment.do" enctype="multipart/form-data">
<table>
	<tr><td><input type="hidden" name="communityuid" value="${bean.communityuid}"></td>
		<td>${id } 님</td>
	</tr>
	<tr>
		<td><textarea name="usercomment"></textarea></td>
	</tr>
	<tr>	
		<td><input type="file" name="img_1" value="파일첨부"></td>
		<td><input type="submit" value="글남기기"></td>
	</tr>
</table>
</form>

<c:forEach var="list" items="${v}">
<table>
	<tr>
		<td>${list.user} </td>
		<td>${list.commentuid}</td>
		<td>${list.usercomment}</td>
		<c:if test="${list.img_1 != ''}">
			<td><img src="/communityupload/usercomment/${list.img_1}"></td>
		</c:if>
		<td>${list.reply}</td>
		<td>${list.signdate}</td>
	</tr>
</table>

<form action="/community/usercomment_reply.do" method="post">
<table>
	<tr>
		<td><input type="hidden" name = "communityuid" value="${bean.communityuid}"></td>
		<td><input type="hidden" name = "fid" value="${list.fid}"></td>
		<td><input type="hidden" name = "commentuid" value="${list.commentuid}"></td>
		<c:if test="${list.thread == '65'}"> <!-- 쓰레드값이 아스키코드A(부모쓰레드)일때 답글창 활성화-->	
			<td><textarea name="usercomment" value="${list.usercomment}"></textarea></td>
			<td><input type="submit" value="답글달기"></td>
		</c:if>
	</tr>
</table>
</form>
</c:forEach>
</div>
<form action="/community/usercomment_delete.do?" method="post">
<table>
  <tr>
  	<td><input type="hidden" name = "communityuid" value="${bean.communityuid}"></td>
  	<td><input type="submit" value="댓글삭제"></td>
  </tr>
</table>
</form>
<form action="/community/community_delete.do" method="post">
<table>
  <tr>
    <td><input type="hidden" name = "communityuid" value="${bean.communityuid}"></td>
    <c:if test="${bean.user == session_id}">
    <td><input type="submit" value="글삭제"></td>
    </c:if>
  </tr>
</table>
</form>
<%@ include file = "/include/footer.jsp" %>