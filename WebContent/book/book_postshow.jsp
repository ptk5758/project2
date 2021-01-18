<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp" %>
<script>
	function bookupdate(book_uid){
		location.href = "/book/book_updateinfo.go?bookupdate="+book_uid+"";
	}
	function bookdelete(book_uid){
		
		location.href = "/book/book_delete.do?bookdelete="+book_uid+"";
	}
	function posting(book_uid){
		location.href = "/book/book_writing_page.jsp?posting="+book_uid+"";
	}
	function episodeshow(ep_uid){
		location.href = "/book/book_episodeshow.do?episodeshow="+ep_uid+"";
	}
	function booklike(book_uid) {
		location.href = "/book/book_like.do?booklike="+book_uid+"";
	}
	function bookmark(book_uid) {
		location.href = "/book/book_mark.go?bookmark="+book_uid+"";
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
		<table class="bookborder" style="width:80%" align="center">
			<tr>
				<td colspan=6><h3>${bean.book_subject}</h3></td>
			</tr>
			<tr>
				<td colspan=6><b>등록자:</b>${bean.book_writer}</td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${bean.book_img1 != ''}">
						<td class="bookborder" rowspan=5><img src = "/bookUpload/${bean.book_img1}" width="252" height="230"></td>
					</c:when>
					<c:otherwise>
						<td class="bookborder" rowspan=5 width="282" height="230" align="center" style="color:#6a6b63;">첨부 이미지가 없습니다.</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td class="bookborder" colspan=2><b>장르:</b>${bean.book_genre}</td>
				<td class="bookborder" colspan=3><b>등록날짜:</b>${bean.book_signdate}</td>
			</tr>
			<tr>
				<td class="bookborder" colspan=2><b>조회수:</b>${bean.book_ref}</td>
				<td class="bookborder" colspan=2>추천수:${likestack }</td>
				<td class="bookborder" onclick="booklike('${bean.book_uid}')">좋아요!</td>
			</tr>
			<tr>
				<td class="bookborder" colspan=5><b>작품내용:</b>${bean.book_contents}</td>
			</tr>
		</table>
		<table style="width:15%" align="right">
			<tr>
			<c:if test="${bean.book_writer == sessionScope.id || sessionScope.level == 10}">
				<td onclick ="bookupdate('${bean.book_uid}')">글수정</td>
				<td onclick ="bookdelete('${bean.book_uid}')">글삭제</td>
			</c:if>
				<td onclick ="bookmark('${bean.book_uid}')">즐겨찾기</td>
			</tr>
		</table>
	</div>
	<br><br>
	<div>
		<c:set var="count" value="${total_count}"/>
		<c:forEach var="list" items="${v }">
		<table class="bookborder" style="width:50%; cursor:pointer;" align="center" onclick="episodeshow('${list.ep_uid}')">
			<tr>
				<td colspan=5>회차:${count}</td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${list.ep_img1 != ''}">
						<td class="bookborder" rowspan=3><img src = "/bookUpload/${list.ep_img1}" width="230" height="230"></td>
					</c:when>
					<c:otherwise>
						<td class="bookborder" rowspan=3 width="282" height="230" align="center" style="color:#6a6b63;">첨부 이미지가 없습니다.</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>				
				<td colspan=4 class="bookborder"><h4>${list.ep_subject}</h4></td>
			</tr>
			<tr>
				<td colspan=2 class="bookborder"><b>조회수:</b>${list.ep_ref}</td>
				<td colspan=2 class="bookborder"><b>등록날짜:</b>${list.ep_signdate}</td>
			</tr>
		</table>
		<c:set var="count" value="${count-1}"/>
		</c:forEach>
		<table style="width:15%" align="right">
			<tr>
			<c:if test="${bean.book_writer == sessionScope.id || sessionScope.level == 10}">
				<td onclick ="posting('${bean.book_uid}')">글쓰기</td>
			</c:if>
			</tr>
		</table>
	</div>
</div>
<%@ include file = "/include/footer.jsp" %>