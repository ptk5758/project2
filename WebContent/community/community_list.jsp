<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	function postshow(communityuid) {
		location.href = "/community/community_postshow.do?postshow="+communityuid+"";
	}
</script>
<style>
	.board{
		border-collapse: collapse;
		text-align:center;
	}
	.subboard{
		border-collapse: collapse;
		text-align:center;
		border-bottom: 1px solid #f8f8f8;
	}
	.topboard{
		border-collapse: collapse;
		text-align:center;
		background-color: #f0f0f0; 
	}
</style>
<div>
<table style="width:1300px" align="center" class="board">
	<tr>
		<td class="topboard"><b>number</b></td>
		<td class="topboard"><b>title</b></td>
		<td class="topboard"><b>subject</b></td>
		<td class="topboard"><b>user</b></td>
		<td class="topboard"><b>like_count</b></td>
		<td class="topboard"><b>date</b></td>
		<td class="topboard"><b>ref</b></td>
	</tr>
<c:set var="number" value="${number}"/>
<c:forEach var="list" items="${v}">
	<tr style="cursor: pointer" onclick="postshow('${list.communityuid}')">
		<td class="subboard">${number} </td>
		<td class="subboard">${list.title}</td>
		<td class="subboard">${list.subject}</td>
		<td class="subboard">${list.user}</td>
		<td class="subboard">${list.like_count}</td>
		<td class="subboard">${list.signdate}</td>
		<td class="subboard">${list.ref}</td>
	</tr>
<c:set var="number" value="${number-1}"/>
</c:forEach>
</table>
<table style="width:5%" align="right">
	<tr>
		<td align="right" colspan="5"><a href="/community/community_posting.jsp">글쓰기</a></td>
	</tr>
</table>
<form action="/community/community_list.go" method="post">
<table style="width:20%" align="center">
	<tr>
		<td><input type= "hidden" name="title" value="${title}"></td>
		<td>
			<select name="search_title">
				<option value="user">글쓴이</option>
				<option value="subject">제목</option>
				<option value="contents">내용</option>			
			</select>
		</td>
		<td><input name = "search_contents" placeholder="검색어를 입력하세요."></td>
	    <td><input type="submit" value="search"></td>
	</tr>
</table>
</form>
</div>
<%@ include file = "/include/footer.jsp" %>