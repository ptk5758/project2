<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<script>
	function password_Change(){
		if(newPassword1.value != newPassword2.value){
			alert("비밀번호가 일치하지 않습니다.");
			return 0;
		}
		passwordChange.submit();
	}
</script>
	<div class="user_id_search">
		<h1>비밀번호변경하기</h1>
		<div class="user_id_search_border">
			<form name="passwordChange" method="post" action="/password_Change.do">
				<div class="user_id_search_input"><input id="newPassword1" name="newPassword1" placeholder="변경할 비밀번호"></div>
				<div class="user_id_search_input"><input id="newPassword2" name="newPassword2" placeholder="변경할 비밀번호확인"></div>
				<input type="hidden" name="userid" value="${userid }">
			</form>
			<div><input type="button" value="비밀번호변경하기" onclick="password_Change()"></div>
		</div>
	</div>
<%@ include file="/include/footer.jsp" %>