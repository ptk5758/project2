<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file = "/include/header.jsp" %>
<% pageContext.setAttribute("replaceChar","\n");%>
<script>
	function posting(book_uid){
		location.href = "/book/book_writing_page.jsp?posting="+book_uid+"";
	}
	function episodeshow(ep_uid){
		location.href = "/book/book_episodeshow.do?episodeshow="+ep_uid+"";
	}
	function episodeupdate(ep_uid){
		location.href = "/book/book_episodeupdateinfo.go?episodeupdate="+ep_uid+"";
	}
	function episodedelete(ep_uid){
		location.href = "/book/book_episodedelete.do?episodedelete="+ep_uid+"";
	}
</script>
<style>
	.parents{
		width:100%
	}
	.bookborder{
		border: 1px solid #f0f0f0;
		border-collapse: collapse;
	}
</style>
<div class="parents">
	<div>
		<table table class="bookborder" style="width:80%" align="center">
			<tr>
				<td class="bookborder">${bean.ep_subject}</td>	
			</tr>
			<tr>
				<td class="bookborder">${bean.ep_signdate}</td>
			</tr>
			<tr>
				<c:if test="${bean.ep_img1 != ''}">
					<td class="bookborder"><img src="/bookUpload/${bean.ep_img1}"></td>
				</c:if>
			</tr>
			<tr>
				<td class="bookborder">${fn:replace(bean.ep_contents,replaceChar,"<br/>")}</td>
			</tr>
		</table>
		<table style="width:15%" align="right">
			<tr>
			<c:if test="${bean.ep_writer == sessionScope.id || sessionScope.level == 10}">
				<td onclick ="episodeupdate('${bean.ep_uid}')">글수정</td>
				<td onclick ="episodedelete('${bean.ep_uid}')">글삭제</td>
			</c:if>
			</tr>
		</table>
	</div>
</div>
<%@ include file = "/include/footer.jsp" %>
