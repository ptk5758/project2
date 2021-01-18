<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
	<script>
		alert("${msg}");
		location.href="${url}";
	</script>
<%@ include file="/include/footer.jsp"%>