<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String cate = request.getParameter("type");
%>
<select name="category2" onchange="b(this.value)">
	<option>=<%=cate %>=</option>
	<option value="스킨">스킨</option>
	<option value="로션">로션</option>
</select>
<h1>ㅎㅇㅎㅇ</h1>
<input type="color">