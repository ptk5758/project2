<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp" %>
<form action="/community/posting.do" method="post" enctype="multipart/form-data">
<table style="width:50px; position: relative; right:900px; ">
	<tr>
		<td colspan=2>
			<select name="title">
				<option value="notice">공지사항</option>		
				<option value="free">자유게시판</option>		
				<option value="fun">유머게시판</option>		
				<option value="qna">Q&#38;A</option>		
			</select>
		</td>
	</tr>
	<tr>
		<td colspan=2><input name = "subject" placeholder="제목"></td>
	</tr>
	<tr>
		<td><input type="radio" name="secret" value="secret">비밀글</td>
		<td><input type="radio" name="secret" value="normal" checked>일반글</td>
	</tr>
	<tr>
		<td colspan=2><textarea name="contents" placeholder="내용"></textarea><td>
	</tr>
	<tr>
		<td colspan=2><input type="file" name="file_1"></td>
	</tr>
	<tr>
		<td colspan=2><input type="file" name="file_2"></td>
	</tr>
	<tr>
		<td colspan=2><input type="file" name="file_3"></td>
	</tr>
	<tr>
		<td colspan=2><input type="submit" value="등록하기"></td>
	</tr>
</table>
</form>
<%@ include file = "/include/footer.jsp" %>
