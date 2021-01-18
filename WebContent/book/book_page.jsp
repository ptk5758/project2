<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<script>
	function postshow(book_uid) {
		location.href = "/book/book_postshow.do?postshow="+book_uid+"";
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
		<c:forEach var="list" items="${v}">
		<table class="bookborder" style="width:50%; cursor: pointer" align="center" onclick="postshow('${list.book_uid}')">
			<tr>
				<td colspan=8>${list.book_title}</td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${list.book_thumb != ''}">
						<td class="bookborder" rowspan=5><img src = "/bookUpload/${list.book_thumb}"></td>
					</c:when>
					<c:otherwise>
						<td class="bookborder" rowspan=3 width="282" height="230" align="center" style="color:#6a6b63;">첨부 이미지가 없습니다.</td>
					</c:otherwise>
				</c:choose>
				<td class="bookborder" colspan=4>제목:${list.book_subject}</td>
				<td class="bookborder" colspan=2>작가이름:${list.book_writer}</td>
			</tr>
			<tr>
				<td class="bookborder" colspan=6>장르:${list.book_genre}</td>
			</tr>
			<tr>
				<td class="bookborder" colspan=6>등록날짜:${list.book_signdate}</td>
			</tr>
			<tr>
				<td class="bookborder" colspan=6>내용:${list.book_contents}</td>
			</tr>
			<tr>
				<td class="bookborder" colspan=6>조회수:${list.book_ref}</td>
			</tr>
		</table>
		</c:forEach>
		<table style="width:5%" align="right">
			<tr>
				<td align="right" colspan="5"><a href="/book/book_posting_page.jsp">책 등록</a></td>
			</tr>
		</table>
	</div>
</div>
<%@ include file="/include/footer.jsp" %>