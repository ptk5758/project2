<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp" %>
<form action = "/book/book_update.do?uid=${param.bookupdate }" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td colspan=2>
			<select name="title">
				<option value="webnv">연재 웹소설</option>		
				<option value="webtoon">연재 웹툰</option>		
				<option value="endwebnv">완결 웹소설</option>		
				<option value="endwebtoon">완결 웹툰</option>			
			</select>
		</td>
	</tr>
	<tr>
		<td colspan=2>
			<select name="genre">
				<option value="fantasy">판타지</option>		
				<option value="무협">무협</option>		
				<option value="thriller">스릴러</option>		
				<option value="추리">추리</option>		
				<option value="romance">로맨스</option>		
				<option value="SF">공상과학</option>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan=2>제목:<input name = "subject" value="${bean.book_subject}"></td>
	</tr>
	<tr>
		<td colspan=2><textarea name="contents" placeholder="내용">${bean.book_contents}</textarea><td>
	</tr>
	<tr>
		<td colspan=2>파일을 다시 올려주세요.</td>
	</tr>
	<tr>
		<td colspan=2>파일1:<input type="file" name="img_1"></td>
	</tr>
	<tr>
		<td colspan=2>파일2:<input type="file" name="img_2"></td>
	</tr>
	<tr>
		<td colspan=2>파일3:<input type="file" name="img_3"></td>
	</tr>
	<tr>
		<td colspan=2><input type="submit" value="등록하기"></td>
	</tr>
</table>
</form>
<%@ include file = "/include/footer.jsp" %>