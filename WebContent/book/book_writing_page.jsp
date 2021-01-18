<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp" %>
<form action="/book/book_writing.do?uid=${param.posting }" method="post" enctype="multipart/form-data">

<table>
	<tr>
		<td colspan=2>제목:<input name = "subject" placeholder="제목"></td>
	</tr>
	<tr>
		<td colspan=2><textarea name="contents" placeholder="내용"></textarea><td>
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