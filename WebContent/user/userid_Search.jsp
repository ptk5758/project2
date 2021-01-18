<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<script>
	function id_search(){
		idsearch.submit();
	}
</script>
	<div class="user_id_search">
		<h1>아이디 찾기</h1>
		<div class="user_id_search_border">
			<form name="idsearch" method="post" action="/id_Search.do">
				<div class="user_id_search_input"><input name="username" placeholder="이름"></div>
				<div class="user_id_search_input"><input name="useremail" placeholder="이메일주소"></div>
			</form>
			<div><input type="button" value="아이디검색" onclick="id_search()"></div>
		</div>
	</div>
<%@ include file="/include/footer.jsp" %>