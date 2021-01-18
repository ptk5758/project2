<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<script>
	function postshow(book_uid) {
		location.href = "/book/book_postshow.do?postshow="+book_uid+"";
	}
	function bookmarkdelete(book_uid) {
		location.href = "/book/book_mark_delete.do?bookmarkdelete="+book_uid+"";
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
		<c:forEach var="list" items="${v }">
		<table class="bookborder" style="width:80%; cursor:pointer;" align="left" onclick="postshow('${list.book_uid}')"> 
			<tr>				
				<td colspan=3 class="bookborder">즐겨찾기 한 책 제목</td>
				<td colspan=3 class="bookborder">즐겨찾기 한 날짜</td>
			</tr>
			<tr>				
				<td colspan=3 class="bookborder">${list.bm_subject}</td>
				<td colspan=3 class="bookborder">${list.bm_signdate}</td>
			</tr>
		</table>
		<table style="width:20%" align="right">
			<tr>
				<td onclick="bookmarkdelete('${list.book_uid}')">삭제</td>
			</tr>
		</table>
		</c:forEach>
	</div>
</div>
<%@ include file="/include/footer.jsp" %>